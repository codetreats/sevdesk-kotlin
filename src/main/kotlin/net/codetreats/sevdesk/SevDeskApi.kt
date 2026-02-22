package net.codetreats.sevdesk

import net.codetreats.sevdesk.api.*
import net.codetreats.sevdesk.model.*

val NO_LIMIT = Pair("limit", "1000000")

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
