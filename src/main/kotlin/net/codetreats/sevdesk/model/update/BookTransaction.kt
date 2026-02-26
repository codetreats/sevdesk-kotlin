package net.codetreats.sevdesk.model.update

import net.codetreats.sevdesk.model.CheckAccountObject
import net.codetreats.sevdesk.model.CheckAccountTransaction
import net.codetreats.sevdesk.model.CheckAccountTransactionObject
import java.time.LocalDateTime

data class BookTransaction(
    val amount: Double,
    val date: LocalDateTime,
    val type: String,
    val checkAccount: CheckAccountObject,
    val checkAccountTransaction: CheckAccountTransactionObject,
    val createFeed: Boolean,
) {
    companion object {
        fun from(transaction: CheckAccountTransaction): BookTransaction = BookTransaction(
            amount = transaction.amount,
            date = transaction.valueDate,
            type = "FULL_PAYMENT",
            checkAccount = transaction.checkAccount,
            checkAccountTransaction = CheckAccountTransactionObject(transaction.id),
            createFeed = true,
        )
    }
}

data class BookTransactionResponse(
    val id: String,
    val objectName: String,
    val additionalInformation: String?,
    val create: LocalDateTime,
    val fromStatus: String,
    val toStatus: String,
    val amountPayed: String?,
    val bookingDate: LocalDateTime,
)
