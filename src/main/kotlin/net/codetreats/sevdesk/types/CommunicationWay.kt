package net.codetreats.sevdesk.types

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

data class CommunicationWay(
    val id: String,
    val create: LocalDateTime,
    val update: LocalDateTime,
    val contact: ContactObject,
    val type: CommunicationWayType,
    val value: String,
    val key: CommunicationWayKeyObject,
    val main: Int
)

enum class CommunicationWayType {
    EMAIL, PHONE, WEB, MOBILE
}

enum class CommunicationWayKey(val value: Int) {
    PRIVATE(1),
    WORK(2),
    FAX(3),
    MOBILE(4),
    EMPTY(5),
    AUTOBOX(6),
    NEWSLETTER(7),
    INVOICING(8);


    companion object {
        fun from(value: Int) : CommunicationWayKey =
            CommunicationWayKey.entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid CommunicationWayKey '$value")
    }
}

class CommunicationWayTypeAdapter: JsonAdapter<CommunicationWayType>() {
    @FromJson
    override fun fromJson(reader: JsonReader): CommunicationWayType? =  if (reader.peek() != JsonReader.Token.NULL) {
        CommunicationWayType.valueOf(reader.nextString())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: CommunicationWayType?) {
        writer.value(value?.toString())
    }
}

class CommunicationWayKeyAdapter: JsonAdapter<CommunicationWayKey>() {
    @FromJson
    override fun fromJson(reader: JsonReader): CommunicationWayKey? =  if (reader.peek() != JsonReader.Token.NULL) {
        CommunicationWayKey.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: CommunicationWayKey?) {
        writer.value(value?.value)
    }
}
