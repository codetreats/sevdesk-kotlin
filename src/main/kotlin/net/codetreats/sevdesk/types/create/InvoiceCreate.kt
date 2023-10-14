package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.*
import net.codetreats.sevdesk.util.toAtom
import java.util.*

data class InvoiceCreateContainer(
    val invoice: InvoiceCreate,
    val invoicePosSave: List<InvoicePosCreate>
)

data class InvoiceCreate(
    val invoiceNumber: String,
    val header: String,
    val customerInternalNote: String,
    val headText: String,
    val footText: String,
    val contact: ContactObject,
    @Transient val invoiceDateObject: Date,
    val discountTime: String,
    val discount: String,
    val status: InvoiceStatus,
    val smallSettlement: Int,
    val taxText: String,
    val taxType: String,
    val taxRate: String,
    val showNet: Int,
    val timeToPax: Int,
    val contactPerson: ContactObject,
    val invoiceType: InvoiceType,
    val address: String,
    val currency: String,
    val mapAll: Boolean
) {
    val objectName: String = "Invoice"
    val invoiceDate: String = invoiceDateObject.toAtom()
}

data class InvoicePosCreate(
    val part: PartObject,
    val quantity: Int,
    val price: Double,
    val name: String,
    val priority: Int,
    val unity: UnityObject,
    val positionNumber: Int,
    val discount: Double,
    val taxRate: String,
    val temporary: Boolean,
    val mapAll: Boolean
) {
    val objectName: String = "InvoicePos"
}