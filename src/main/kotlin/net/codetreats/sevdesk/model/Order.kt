package net.codetreats.sevdesk.model

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

data class OrderContainer(
    val order: Order
)

data class Order(
    override val id: String,
    val orderNumber: String,
    override val contact: ContactObject,
    override val create: LocalDateTime,
    override val update: LocalDateTime,
    val orderDate: LocalDateTime,
    override val header: String,
    override val headText: String?,
    override val footText: String?,
    override val addressCountry: StaticCountryObject?,
    override val createUser: SevUserObject,
    val status: OrderStatus,
    override val contactPerson: ContactObject,
    val orderType: OrderType,
    override val address: String,
    override val customerInternalNote: String?,
    val deliveryTerms: String?,
    val paymentTerms: String?,
) : Document {
    override fun type(): String = orderType.abbreviation
    override fun status(): Int = status.value
    override fun date(): LocalDateTime = orderDate
    override fun number(): String = orderNumber
}

enum class OrderType(val abbreviation: String) {
    DELIVERY_NOTE("LI"),
    ORDER_CONFIRMATION("AB"),
    PROPOSAL("AN");

    companion object {
        fun from(abbreviation: String) : OrderType =
            entries.firstOrNull { it.abbreviation == abbreviation} ?: throw IllegalArgumentException("Invalid OrderType '$abbreviation")
    }
}

enum class OrderStatus(val value: Int) {
    DRAFT(100),
    DELIVERED(200),
    CANCELLED(300),
    ACCEPTED(500),
    PARTIALLY_CALCULATED(750),
    CALCULATED(1000);

    companion object {
        fun from(value: Int) : OrderStatus =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid OrderStatus '$value")
    }
}

class OrderStatusAdapter: JsonAdapter<OrderStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): OrderStatus? =  if (reader.peek() != JsonReader.Token.NULL) {
        OrderStatus.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: OrderStatus?) {
        writer.value(value?.value)
    }
}

class OrderTypeAdapter: JsonAdapter<OrderType>() {
    @FromJson
    override fun fromJson(reader: JsonReader): OrderType? =  if (reader.peek() != JsonReader.Token.NULL) {
        OrderType.from(reader.nextString())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: OrderType?) {
        writer.value(value?.abbreviation)
    }
}