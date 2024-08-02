package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.CheckAccountObject
import net.codetreats.sevdesk.model.CheckAccountTransaction
import net.codetreats.sevdesk.model.CheckAccountTransactionStatus
import net.codetreats.sevdesk.util.asTimestampParam
import java.time.LocalDateTime

class TransactionApi(private val client: SevDeskClient) {
    fun get(accountId: String, start: LocalDateTime, end: LocalDateTime): List<CheckAccountTransaction> {
        val params = CheckAccountObject(accountId).asParam() +
                NO_LIMIT +
                start.asTimestampParam("startDate") +
                end.asTimestampParam("endDate")
        return client.get<CheckAccountTransaction>("/CheckAccountTransaction", params)
    }

    fun getOpen(accountId: String? = null) : List<CheckAccountTransaction> {
        val params = mutableMapOf(NO_LIMIT)
        params["status"] = CheckAccountTransactionStatus.CREATED.value.toString()
        if (accountId != null) {
            params += CheckAccountObject(accountId).asParam()
        }
        return client.get<CheckAccountTransaction>("/CheckAccountTransaction", params)
    }
}