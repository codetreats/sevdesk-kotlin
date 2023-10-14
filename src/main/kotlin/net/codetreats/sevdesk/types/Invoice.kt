package net.codetreats.sevdesk.types

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.util.*

data class Invoice(
    val id: String,
    val invoiceNumber: String,
    val contact: ContactObject,
    val create: Date,
    val update: Date,
    val invoiceDate: Date,
    val header: String,
    val headText: String,
    val footText: String,
    val timeToPay: Int,
    val addressCountry: StaticCountryObject,
    val createUser: SevUserObject,
    val deliveryDate: Date,
    val status: InvoiceStatus,
    val contactPerson: ContactObject,
    val taxRate: Int,
    val invoiceType: InvoiceType,
    val address: String,
    val currency: String,
    val sumNet: Double,
    val sumTax: Double,
    val sumGross: Double,
    val customerInternalNote: String
)

enum class InvoiceType(val abbreviation: String) {
    NORMAL("RE"),
    RECURRING("WKR"),
    CANCELLATION("SR"),
    REMINDER("MA"),
    PART("TR"),
    FINAL("ER");

    companion object {
        fun from(abbreviation: String) : InvoiceType =
            entries.firstOrNull { it.abbreviation == abbreviation} ?: throw IllegalArgumentException("Invalid InvoiceType '$abbreviation")
    }
}

enum class InvoiceStatus(val value: Int) {
    DEACTIVATED(50),
    DRAFT(100),
    OPEN(200),
    PAYED(1000);

    companion object {
        fun from(value: Int) : InvoiceStatus =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid InvoiceStatus '$value")
    }
}

class InvoiceStatusAdapter: JsonAdapter<InvoiceStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): InvoiceStatus? =  if (reader.peek() != JsonReader.Token.NULL) {
        InvoiceStatus.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: InvoiceStatus?) {
        writer.value(value?.value)
    }
}

class InvoiceTypeAdapter: JsonAdapter<InvoiceType>() {
    @FromJson
    override fun fromJson(reader: JsonReader): InvoiceType? =  if (reader.peek() != JsonReader.Token.NULL) {
        InvoiceType.from(reader.nextString())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: InvoiceType?) {
        writer.value(value?.abbreviation)
    }
}