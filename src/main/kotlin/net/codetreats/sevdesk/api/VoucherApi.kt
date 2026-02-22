package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.CheckAccountTransaction
import net.codetreats.sevdesk.model.Voucher
import net.codetreats.sevdesk.model.VoucherContainer
import net.codetreats.sevdesk.model.VoucherStatus
import net.codetreats.sevdesk.model.create.VoucherSaveContainer
import net.codetreats.sevdesk.model.create.VoucherUploadResult
import net.codetreats.sevdesk.model.update.BookTransaction
import net.codetreats.sevdesk.model.update.BookTransactionResponse
import net.codetreats.sevdesk.util.unixTimestamp
import java.io.File
import java.time.LocalDateTime

class VoucherApi(private val client: SevDeskClient) {

    fun get(status: VoucherStatus): List<Voucher> =
        client.get<Voucher>("/Voucher", mapOf(NO_LIMIT, "status" to "${status.value}"))

    fun from(startTime: LocalDateTime): List<Voucher> =
        client.get<Voucher>("/Voucher", mapOf(NO_LIMIT, "startDate" to "${startTime.unixTimestamp()}"))

    fun add(voucher: VoucherSaveContainer): VoucherContainer =
        client.post("/Voucher/Factory/saveVoucher", body = voucher)

    fun uploadTmpFile(file: File): VoucherUploadResult = client.postFile("/Voucher/Factory/uploadTempFile", file = file)

    fun resetToOpen(id: String): Voucher = client.put<Any, Voucher>("/Voucher/$id/resetToOpen", body = Any())

    fun resetToDraft(id: String): Voucher = client.put<Any, Voucher>("/Voucher/$id/resetToDraft", body = Any())

    fun book(voucherId: String, transaction: CheckAccountTransaction): BookTransactionResponse =
        client.put<BookTransaction, BookTransactionResponse>(
            "/Voucher/$voucherId/bookAmount",
            body = BookTransaction.from(transaction),
        )

    fun delete(id: String) = client.delete("/Voucher/$id")
}
