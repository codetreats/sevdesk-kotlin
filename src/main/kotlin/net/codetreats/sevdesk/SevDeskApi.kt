package net.codetreats.sevdesk

import net.codetreats.sevdesk.api.*
import net.codetreats.sevdesk.model.*
import net.codetreats.sevdesk.model.StaticCountry
import net.codetreats.sevdesk.model.create.VoucherSaveContainer
import net.codetreats.sevdesk.util.asTimestampParam
import net.codetreats.sevdesk.util.unixTimestamp
import java.lang.IllegalArgumentException
import java.time.LocalDateTime


val NO_LIMIT = Pair("limit", "100000")

/**
 * Creates an instance of the API
 * @param client an instance of [SevDeskClient]
 */
class SevDeskApi(private val client: SevDeskClient) {
    val contacts = ContactApi(client)
    val invoices = InvoiceApi(client)
    val parts = PartApi(client)
    val static = StaticApi(client)
    val transactions = TransactionApi(client)
    val vouchers = VoucherApi(client)
    val orders = OrderApi(client)
}
