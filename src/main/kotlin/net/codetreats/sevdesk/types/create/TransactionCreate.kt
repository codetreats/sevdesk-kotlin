package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.CheckAccountObject
import net.codetreats.sevdesk.types.CheckAccountTransactionStatus
import net.codetreats.sevdesk.util.toAtom
import java.util.*

data class TransactionCreate(
    val valueDate: String,
    val entryDate: String,
    val amount: String,
    val paymtPurpose: String,
    val payeePayerName: String,
    val checkAcckount: CheckAccountObject,
    val status: String
) {
    companion object {
        fun from(
            date: Date,
            amount: Double,
            purpose: String,
            payer: String,
            accountId: String,
            status: CheckAccountTransactionStatus = CheckAccountTransactionStatus.CREATED
        ) = TransactionCreate(
            date.toAtom(),
            date.toAtom(),
            amount.toString(),
            purpose,
            payer,
            CheckAccountObject(accountId),
            status.value.toString()
        )
    }
}