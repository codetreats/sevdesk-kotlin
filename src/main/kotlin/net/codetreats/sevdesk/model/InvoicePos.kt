package net.codetreats.sevdesk.model

data class InvoicePos(
    val id: String,
    val name: String,
    val text: String?,
    val invoice: InvoiceObject,
    val part: PartObject?,
    val sumNet: Double,
    val sumTax: Double,
    val sumGross: Double,
    val price: Double,
    val priceGross: Double,
    val priceNet: Double,
    val quantity: Int,
    val taxRate: Int,
    val discount: Double,
    val sumDiscount: Double,
    val unity: UnityObject
)