package net.codetreats.sevdesk.types

import com.squareup.moshi.*
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

data class CheckAccountTransaction(
    val id: String,
    val create: LocalDateTime,
    val update: LocalDateTime,
    val valueDate: LocalDateTime,
    val entryDate: LocalDateTime,
    val amount: Double,
    val paymtPurpose: String,
    val payeePayerName: String,
    val checkAccount: CheckAccountObject,
    val status: CheckAccountTransactionStatus
)

enum class CheckAccountTransactionStatus(val value: Int) {
    CREATED(100),
    LINKED(200),
    PRIVATE(300),
    BOOKED(400);

    companion object {
        fun from(value: Int) : CheckAccountTransactionStatus =
            entries.firstOrNull { it.value == value} ?: throw IllegalArgumentException("Invalid CheckAccountTransactionStatus '$value")
    }
}

class CheckAccountTransactionStatusAdapter: JsonAdapter<CheckAccountTransactionStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): CheckAccountTransactionStatus? =  if (reader.peek() != JsonReader.Token.NULL) {
        CheckAccountTransactionStatus.from(reader.nextInt())
    } else {
        reader.nextNull()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: CheckAccountTransactionStatus?) {
        writer.value(value?.value)
    }
}
