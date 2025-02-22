package net.codetreats.sevdesk.model

data class InvoiceSendBy(
    val sendType: InvoiceSendType,
    val sendDraft: Boolean = false
)


enum class InvoiceSendType {
    /**
     * Print the invoice.
     */
    VPR,

    /**
     * Send the invoice via post.
     */
    VP,

    /**
     * Send the invoice via email.
     */
    VM,

    /**
     * Generate the invoice as a PDF.
     */
    VPDF
}