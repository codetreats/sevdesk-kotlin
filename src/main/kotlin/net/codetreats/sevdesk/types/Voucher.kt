package net.codetreats.sevdesk.types

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.util.Date

data class Voucher(
    val id: String,
    val additionalInformation: String?,
    val create: Date,
    val update: Date,
    val createUser: SevUserObject,
    val voucherDate: Date,
    val supplierName: String,
    val description: String,
    val document: DocumentObject,
    val payDate: Date?,
    val status: VoucherStatus,
    val currency: String,
    val sumNet: Double,
    val sumTax: Double,
    val sumGross: Double,
    val showNet: Int,
    val voucherType: String,
    val deliveryDate: Date
)

enum class VoucherStatus(val value: Int) {
    DRAFT(50),
    UNPAID(100),
    TRANSFERRED(150),
    PARTIALLY_PAID(750),
    PAID(1000);

    companion object {
        fun from(value: Int) : VoucherStatus =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid VoucherStatus '$value")
    }
}

class VoucherStatusAdapter: JsonAdapter<VoucherStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): VoucherStatus? =  if (reader.peek() != JsonReader.Token.NULL) {
        VoucherStatus.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: VoucherStatus?) {
        writer.value(value?.value)
    }
}