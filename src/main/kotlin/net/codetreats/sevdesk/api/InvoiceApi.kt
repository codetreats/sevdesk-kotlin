package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.*
import net.codetreats.sevdesk.model.create.InvoiceSaveContainer
import net.codetreats.sevdesk.model.update.BookTransaction
import net.codetreats.sevdesk.model.update.BookTransactionResponse
import net.codetreats.sevdesk.util.unixTimestamp
import java.time.LocalDateTime

class InvoiceApi(private val client: SevDeskClient) {
    fun by(customerInternalNote: String): Invoice? =
        client.get<Invoice>("/Invoice", mapOf("customerInternalNote" to customerInternalNote)).firstOrNull()

    fun all(limit: Int = 50): List<Invoice> = client.get<Invoice>("/Invoice", mapOf("limit" to "$limit"))

    fun pdf(invoiceId: String): SevDeskFile = client.getElement<SevDeskFile>("/Invoice/$invoiceId/getPdf")

    fun from(startTime: LocalDateTime): List<Invoice> =
        client.get<Invoice>("/Invoice", mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}"))

    fun updatedAfter(updateAfter: LocalDateTime): List<Invoice> =
        client.get<Invoice>("/Invoice", mapOf(NO_LIMIT, "updateAfter" to "${updateAfter.unixTimestamp()}"))

    fun positions(invoiceId: String): List<InvoicePos> =
        client.get<InvoicePos>("/InvoicePos", InvoiceObject(invoiceId).asParam())

    fun positionsByPart(partId: String): List<InvoicePos> =
        client.get<InvoicePos>("/InvoicePos", mapOf(NO_LIMIT) + PartObject(partId).asParam())

    fun nextNumber(): String = client.getElement<String>("/Invoice/Factory/getNextInvoiceNumber")

    fun add(invoice: InvoiceSaveContainer): InvoiceContainer =
        client.post("/Invoice/Factory/saveInvoice", body = invoice)

    fun sendBy(invoiceId: String, sendBy: InvoiceSendBy): Invoice =
        client.put("/Invoice/$invoiceId/sendBy", body = sendBy)

    fun book(invoiceId: String, transaction: CheckAccountTransaction): BookTransactionResponse =
        client.put<BookTransaction, BookTransactionResponse>(
            "/Invoice/$invoiceId/bookAmount",
            body = BookTransaction.from(transaction),
        )
}
