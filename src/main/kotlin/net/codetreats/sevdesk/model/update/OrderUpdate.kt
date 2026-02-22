package net.codetreats.sevdesk.model.update

import com.squareup.moshi.*

sealed interface OrderUpdate
data class OrderUpdateDeliveryTerms(val deliveryTerms: String?) : OrderUpdate
