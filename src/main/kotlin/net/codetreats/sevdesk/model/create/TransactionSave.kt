package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.CheckAccountObject
import java.time.LocalDateTime

data class TransactionSave(
    val valueDate: LocalDateTime,
    val entryDate: LocalDateTime,
    val amount: String,
    val paymtPurpose: String,
    val payeePayerName: String,
    val checkAcckount: CheckAccountObject,
    val status: String
)