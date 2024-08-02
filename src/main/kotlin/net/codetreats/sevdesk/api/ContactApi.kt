package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.CommunicationWay
import net.codetreats.sevdesk.model.Contact
import net.codetreats.sevdesk.model.ContactObject

class ContactApi(private val client: SevDeskClient) {
    fun byZip(zip: String) =
        client.get<Contact>("/Contact", mapOf(NO_LIMIT, "depth" to "1", "zip" to zip))

    fun hasEmail(customerId: String, email: String): Boolean {
        val allEmails = client.get<CommunicationWay>(
            "/CommunicationWay",
            ContactObject(customerId).asParam() + Pair("type", "EMAIL")
        )
        return allEmails.any { it.value.lowercase() == email.lowercase() }
    }
}