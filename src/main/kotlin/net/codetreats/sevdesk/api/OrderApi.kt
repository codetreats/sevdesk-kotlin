package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.*
import net.codetreats.sevdesk.model.create.OrderSaveContainer
import net.codetreats.sevdesk.model.update.OrderUpdateDeliveryTerms
import net.codetreats.sevdesk.util.unixTimestamp
import java.time.LocalDateTime

class OrderApi(private val client: SevDeskClient) {
    fun exists(customerInternalNote: String): Boolean =
        client.get<Order>("/Order", mapOf("customerInternalNote" to customerInternalNote)).isNotEmpty()

    fun get(headers: Map<String, String> = emptyMap()): List<Order> = client.get<Order>("/Order", headers)

    fun deliveryNotes(limit: Int = 50): List<Order> =
        client.get<Order>("/Order", mapOf("limit" to "$limit", "orderType" to OrderType.DELIVERY_NOTE.abbreviation))

    fun proposals(limit: Int = 50): List<Order> =
        client.get<Order>("/Order", mapOf("limit" to "$limit", "orderType" to OrderType.PROPOSAL.abbreviation))

    fun confirmations(limit: Int = 50): List<Order> = client.get<Order>(
        "/Order",
        mapOf("limit" to "$limit", "orderType" to OrderType.ORDER_CONFIRMATION.abbreviation),
    )

    fun from(startTime: LocalDateTime, type: OrderType? = null): List<Order> = if (type == null) {
        client.get<Order>("/Order", mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}"))
    } else {
        client.get<Order>(
            "/Order",
            mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}", "orderType" to type.abbreviation),
        )
    }

    fun add(order: OrderSaveContainer): OrderContainer = client.post("/Order/Factory/saveOrder", body = order)

    fun update(id: String, orderUpdate: OrderUpdateDeliveryTerms): Order {
        return client.put("/Order/$id", body = orderUpdate)
    }
}
