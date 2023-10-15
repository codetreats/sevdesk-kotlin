package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.CheckAccountObject
import java.time.LocalDateTime

data class TransactionCreate(
    val valueDate: LocalDateTime,
    val entryDate: LocalDateTime,
    val amount: String,
    val paymtPurpose: String,
    val payeePayerName: String,
    val checkAcckount: CheckAccountObject,
    val status: String
)