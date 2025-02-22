package net.codetreats.sevdesk.model.update

import com.squareup.moshi.*
import net.codetreats.sevdesk.model.OrderStatus

sealed interface OrderUpdate
data class OrderUpdateDeliveryTerms(val deliveryTerms: String?) : OrderUpdate