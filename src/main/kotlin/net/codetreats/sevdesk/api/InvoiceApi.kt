package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.Invoice
import net.codetreats.sevdesk.model.InvoiceObject
import net.codetreats.sevdesk.model.InvoicePos
import net.codetreats.sevdesk.model.SevDeskFile
import net.codetreats.sevdesk.util.unixTimestamp
import java.time.LocalDateTime

class InvoiceApi(private val client: SevDeskClient) {
    fun exists(customerInternalNote: String): Boolean =
        client.get<Invoice>("/Invoice", mapOf("customerInternalNote" to customerInternalNote)).isNotEmpty()

    fun all(limit: Int = 50): List<Invoice> =
        client.get<Invoice>("/Invoice", mapOf("limit" to "$limit"))

    fun pdf(invoiceId: String) : SevDeskFile =
        client.getElement<SevDeskFile>("/Invoice/$invoiceId/getPdf")

    fun from(startTime: LocalDateTime): List<Invoice> =
        client.get<Invoice>("/Invoice", mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}"))

    fun positions(invoiceId: String): List<InvoicePos> =
        client.get<InvoicePos>("/InvoicePos", InvoiceObject(invoiceId).asParam())

    fun nextNumber(): String =
        client.getElement<String>("/Invoice/Factory/getNextInvoiceNumber")

}