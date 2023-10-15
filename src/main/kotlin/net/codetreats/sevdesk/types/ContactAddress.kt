package net.codetreats.sevdesk.types

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

data class ContactAddress(
    val id: String,
    val create: LocalDateTime,
    val update: LocalDateTime,
    val contact: ContactObject,
    val street: String,
    val zip: String,
    val city: String,
    val country: StaticCountryObject,
    val category: ContactAddressCategory,
    val name: String?
)

enum class ContactAddressCategory(val value: Int) {
    WORK(43),
    PRIVATE(44),
    EMPTY(45),
    INVOICE(47),
    DELIVERY(48),
    PICKUP(49);

    companion object {
        fun from(value: Int) : ContactAddressCategory =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid ContactAddressCategory '$value")
    }
}

class ContactAddressCategoryAdapter: JsonAdapter<ContactAddressCategory>() {
    @FromJson
    override fun fromJson(reader: JsonReader): ContactAddressCategory? =  if (reader.peek() != JsonReader.Token.NULL) {
        ContactAddressCategory.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: ContactAddressCategory?) {
        writer.value(value?.value)
    }
}
