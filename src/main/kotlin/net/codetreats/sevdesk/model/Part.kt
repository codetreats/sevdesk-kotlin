package net.codetreats.sevdesk.model

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

data class Part(
    val id: String,
    val name: String,
    val partNumber: String,
    val category: CategoryObject,
    val create: LocalDateTime,
    val update: LocalDateTime,
    val unity: UnityObject,
    val price: Double,
    val taxRate: Int,
    val status: PartStatus,
    val priceNet: Double,
    val priceGross: Double,
    )

enum class PartStatus(val value: Int) {
    INACTIVE(50),
    ACTIVE(100);

    companion object {
        fun from(value: Int) : PartStatus =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid PartStatus '$value")
    }
}

class PartStatusAdapter: JsonAdapter<PartStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): PartStatus? =  if (reader.peek() != JsonReader.Token.NULL) {
        PartStatus.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: PartStatus?) {
        writer.value(value?.value)
    }
}
