package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.CheckAccountObject
import net.codetreats.sevdesk.model.CheckAccountTransactionStatus
import java.time.LocalDateTime

data class TransactionSave(
    val valueDate: LocalDateTime,
    val entryDate: LocalDateTime,
    val amount: Double,
    val paymtPurpose: String,
    val payeePayerName: String,
    val checkAccount: CheckAccountObject,
    val status: CheckAccountTransactionStatus,
)
