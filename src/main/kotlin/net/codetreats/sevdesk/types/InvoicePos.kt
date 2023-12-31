package net.codetreats.sevdesk.types

data class InvoicePos(
    val id: String,
    val name: String,
    val invoice: InvoiceObject,
    val part: PartObject?,
    val sumNet: Double,
    val sumTax: Double,
    val sumGross: Double,
    val price: Double,
    val quantity: Int,
    val taxRate: Int
)