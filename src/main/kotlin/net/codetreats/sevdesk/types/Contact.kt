package net.codetreats.sevdesk.types

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

data class Contact(
    val id: String,
    val create: LocalDateTime,
    val update: LocalDateTime,
    val name: String?,
    val status: ContactStatus,
    val customerNumber: String,
    val surename: String?,
    val familyname: String?,
    val category: CategoryObject,
    val description: String
)
enum class ContactStatus(val value: Int) {
    LEAD(100),
    PENDING(500),
    ACTIVE(1000);

    companion object {
        fun from(value: Int) : ContactStatus =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid ContactStatus '$value")
    }
}

class ContactStatusAdapter: JsonAdapter<ContactStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): ContactStatus? =  if (reader.peek() != JsonReader.Token.NULL) {
        ContactStatus.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: ContactStatus?) {
        writer.value(value?.value)
    }
}

