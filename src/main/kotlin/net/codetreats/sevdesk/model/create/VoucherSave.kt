package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.*
import java.time.LocalDateTime

data class VoucherSaveContainer(
    val voucher: VoucherSave,
    val voucherPosSave: List<VoucherPosSave>
)

data class VoucherSave(
    val mapAll: Boolean = true,
    val voucherDate: LocalDateTime,
    val supplierName: String? = null,
    val supplier: ContactObject? = null,
    val description: String,
    val payDate: LocalDateTime,
    val status: VoucherStatus = VoucherStatus.UNPAID,
    val taxType: String? = null,
    val taxRule: TaxRuleObject? = null,
    val creditDebit: String = "C",
    val voucherType: String = "VOU",
    val currency: String = "EUR",
    val taxSet: TaxSetObject? = null,
    val deliveryDateUntil: LocalDateTime? = null,
    val document: DocumentObject? = null,
    val costCentre: String? = null,
    val objectName: String = "Voucher"
)

data class VoucherPosSave(
    val mapAll: Boolean = true,
    val accountDatev: AccountDatevObject,
    val taxRate: Int,
    val net: Boolean = false,
    val isAsset: Boolean = false,
    val sumNet: Double,
    val sumGross: Double,
    val comment: String,
    val objectName: String = "VoucherPos"
)
