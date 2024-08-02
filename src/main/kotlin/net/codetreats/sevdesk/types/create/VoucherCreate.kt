package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.*
import java.time.LocalDateTime

data class VoucherCreate(
    val voucher: VoucherCreateInner,
    val voucherPosSave: List<VoucherPosSave>
)

data class VoucherCreateInner(
    val objectName: String = "Voucher",
    val mapAll: Boolean = true,
    val voucherDate: LocalDateTime,
    val supplierName: String? = null,
    val supplier: ContactObject? = null,
    val description: String,
    val payDate: LocalDateTime,
    val status: VoucherStatus = VoucherStatus.UNPAID,
    val taxType: String? = null,
    val creditDebit: String = "C",
    val voucherType: String = "VOU",
    val currency: String = "EUR",
    val taxSet: TaxSetObject? = null,
    val deliveryDateUntil: LocalDateTime? = null,
    val document: DocumentObject? = null,
    val costCentre: String? = null
)

data class VoucherPosSave(
    val objectName: String = "VoucherPos",
    val mapAll: Boolean = true,
    val accountingType: AccountingTypeObject,
    val taxRate: Int,
    val net: Boolean = false,
    val isAsset: Boolean = false,
    val sumNet: Double,
    val sumGross: Double,
    val comment: String
)
