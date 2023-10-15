package net.codetreats.sevdesk.util

import com.squareup.moshi.*
import net.codetreats.sevdesk.types.InvoiceStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDateTime? = if (reader.peek() != JsonReader.Token.NULL) {
        LocalDateTime.parse(reader.nextString(), formatter)
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        writer.value(formatter.format(value))
    }
}
