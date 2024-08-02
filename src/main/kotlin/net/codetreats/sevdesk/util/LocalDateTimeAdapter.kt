package net.codetreats.sevdesk.util

import com.squareup.moshi.*
import net.codetreats.sevdesk.types.InvoiceStatus
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    private val readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    private val writeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    @FromJson
    override fun fromJson(reader: JsonReader): LocalDateTime? = if (reader.peek() != JsonReader.Token.NULL) {
        LocalDateTime.parse(reader.nextString(), readFormatter)
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        val zonedDateTime = value?.atZone(ZoneId.systemDefault())
        val write: String? = zonedDateTime?.let { writeFormatter.format(it) }
        writer.value(write)
    }
}
