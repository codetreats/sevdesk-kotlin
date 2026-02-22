package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.*
import java.time.LocalDateTime

data class InvoiceSaveContainer(
    val invoice: InvoiceSave,
    val invoicePosSave: List<InvoicePosSave>,
)

data class InvoiceSave(
    val invoiceNumber: String,
    val header: String,
    val customerInternalNote: String,
    val headText: String,
    val footText: String,
    val contact: ContactObject,
    val invoiceDate: LocalDateTime,
    val discountTime: String,
    val discount: String,
    val status: InvoiceStatus,
    val smallSettlement: Int,
    val taxText: String,
    val taxType: String? = null,
    val taxRule: TaxRuleObject? = null,
    val taxRate: String,
    val showNet: Int,
    val timeToPay: Int,
    val contactPerson: SevUserObject,
    val invoiceType: InvoiceType,
    val address: String,
    val currency: String,
    val mapAll: Boolean,
    val objectName: String = "Invoice",
)

data class InvoicePosSave(
    val part: PartObject,
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
    val objectName: String = "InvoicePos",
)
