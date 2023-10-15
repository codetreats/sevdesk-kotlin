package net.codetreats.sevdesk

import net.codetreats.sevdesk.types.*
import net.codetreats.sevdesk.types.StaticCountry
import net.codetreats.sevdesk.util.asTimestampParam
import net.codetreats.sevdesk.util.unixTimestamp
import java.lang.IllegalArgumentException
import java.time.LocalDateTime


val NO_LIMIT = Pair("limit", "100000")

/**
 * Creates an instance of the API
 * @param logger a logger
 * @param client an instance of [SevDeskClient]
 * @param apiKey the SevDesk API key (can be found under extensions->API)
 */
class SevDeskApi(
    private val client: SevDeskClient
) {
    fun invoiceExists(customerInternalNote: String): Boolean =
        client.get<Invoice>("/Invoice", mapOf("customerInternalNote" to customerInternalNote)).isNotEmpty()

    fun invoices(limit: Int = 50): List<Invoice> =
        client.get<Invoice>("/Invoice", mapOf("limit" to "$limit"))

    fun invoicesFrom(startTime: LocalDateTime): List<Invoice> =
        client.get<Invoice>("/Invoice", mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}"))

    fun invoicePositionsOf(invoiceId: String): List<InvoicePos> =
        client.get<InvoicePos>("/InvoicePos", InvoiceObject(invoiceId).asParam())

    fun nextInvoiceNumber(): String =
        client.getElement<String>("/Invoice/Factory/getNextInvoiceNumber")

    fun customerByZip(zip: String) =
        client.get<Contact>("/Contact", mapOf(NO_LIMIT, "depth" to "1", "zip" to "zip"))

    fun hasEmail(customerId: String, email: String): Boolean {
        val allEmails = client.get<CommunicationWay>(
            "/CommunicationWay",
            ContactObject(customerId).asParam() + Pair("type", "EMAIL")
        )
        return allEmails.any { it.value.lowercase() == email.lowercase() }
    }

    fun transactions(accountId: String, start: LocalDateTime, end: LocalDateTime): List<CheckAccountTransaction> {
        val params = CheckAccountObject(accountId).asParam() +
                NO_LIMIT +
                start.asTimestampParam("startDate") +
                end.asTimestampParam("endDate")
        return client.get<CheckAccountTransaction>("/CheckAccountTransaction", params)
    }

    fun openTransactions(accountId: String? = null) : List<CheckAccountTransaction> {
        val params = mutableMapOf(NO_LIMIT)
        params["status"] = CheckAccountTransactionStatus.CREATED.value.toString()
        if (accountId != null) {
            params += CheckAccountObject(accountId).asParam()
        }
        return client.get<CheckAccountTransaction>("/CheckAccountTransaction", params)
    }

    fun part(partNumber: String, description: String? = null) : Part {
        val parts = client.get<Part>("/Part", mapOf("partNumber" to partNumber))
        if (parts.isEmpty()) {
            val descr = description?.let { "($it)" } ?: ""
            throw IllegalArgumentException("No Part found for $partNumber $descr")
        }
        return parts.first()
    }

    fun countries() : List<StaticCountry> =
        client.get<StaticCountry>("/StaticCountry", mapOf(NO_LIMIT))

    fun vouchers(status: VoucherStatus) : List<Voucher> =
        client.get<Voucher>("/Voucher", mapOf(NO_LIMIT, "status" to "${status.value}"))

    /**
     * @param itemNumber the chosen number, not the internal id
     */
    fun itemExists(itemNumber: String): Boolean =
        client.get<Part>("/Part", mapOf("partNumber" to itemNumber)).isNotEmpty()
}
