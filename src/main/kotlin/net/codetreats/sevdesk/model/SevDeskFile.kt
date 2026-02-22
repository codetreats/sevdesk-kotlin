package net.codetreats.sevdesk.model

import java.util.Base64

data class SevDeskFile(
    val filename: String,
    val mimetype: String,
    val base64Encoded: Boolean,
    val content: String,
) {
    fun content(): ByteArray = Base64.getDecoder().decode(content)
}
