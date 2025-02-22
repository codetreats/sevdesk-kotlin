package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.*
import java.time.LocalDateTime

data class OrderSaveContainer(
    val order: OrderSave,
    val orderPosSave: List<OrderPosSave>
)

data class OrderSave(
    val orderNumber: String,
    val header: String,
    val customerInternalNote: String,
    val headText: String = "",
    val footText: String = "",
    val contact: ContactObject,
    val orderDate: LocalDateTime,
    val status: OrderStatus,
    val deliveryTerms: String? = null,
    val paymentTerms: String? = null,
    val smallSettlement: Int,
    val taxText: String,
    val taxType: String,
    val taxRate: String,
    val showNet: Int,
    val contactPerson: SevUserObject,
    val orderType: OrderType,
    val address: String,
    val addressCountry: StaticCountryObject,
    val currency: String,
    val mapAll: Boolean,
    val version: String = "0",
    val objectName: String = "Order"
)

data class OrderPosSave(
    val part: PartObject?,
    val quantity: Int,
    val price: Double,
    val name: String,
    val text: String?,
    val priority: Int,
    val unity: UnityObject,
    val positionNumber: Int,
    val discount: Double,
    val taxRate: String,
    val temporary: Boolean,
    val mapAll: Boolean,
    val objectName: String = "OrderPos"
)