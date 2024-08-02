package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.Part
import net.codetreats.sevdesk.model.Voucher
import net.codetreats.sevdesk.model.VoucherStatus
import net.codetreats.sevdesk.model.create.VoucherSaveContainer
import net.codetreats.sevdesk.util.unixTimestamp
import java.time.LocalDateTime

class VoucherApi(private val client: SevDeskClient) {

    fun get(status: VoucherStatus) : List<Voucher> =
        client.get<Voucher>("/Voucher", mapOf(NO_LIMIT, "status" to "${status.value}"))

    fun from(startTime: LocalDateTime) : List<Voucher> =
        client.get<Voucher>("/Voucher", mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}"))

    fun add(voucher: VoucherSaveContainer)
    {
        client.post("/Voucher/Factory/saveVoucher", body = voucher);
    }
}