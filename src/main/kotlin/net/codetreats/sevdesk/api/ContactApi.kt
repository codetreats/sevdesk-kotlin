package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.*
import net.codetreats.sevdesk.model.create.CommunicationWaySave
import net.codetreats.sevdesk.model.create.ContactAddressSave
import net.codetreats.sevdesk.model.create.ContactSave
import net.codetreats.sevdesk.model.create.InvoiceSaveContainer

class ContactApi(private val client: SevDeskClient) {
    fun byZip(zip: String) : List<Contact> =
        client.get<Contact>("/Contact", mapOf(NO_LIMIT, "depth" to "1", "zip" to zip))

    fun all() : List<Contact> =
        client.get<Contact>("/Contact", mapOf(NO_LIMIT, "depth" to "1"))

    fun hasEmail(customerId: String, email: String): Boolean {
        val allEmails = client.get<CommunicationWay>(
            "/CommunicationWay",
            ContactObject(customerId).asParam() + Pair("type", "EMAIL")
        )
        return allEmails.any { it.value.lowercase() == email.lowercase() }
    }

    fun add(contact: ContactSave) : Contact =
        client.post("/Contact", body = contact);


    fun addAddress(contactAddress: ContactAddressSave) : ContactAddress =
        client.post("/ContactAddress", body = contactAddress);


    fun addCommunicationWay(communicationWaySave: CommunicationWaySave) : CommunicationWay =
        client.post("/CommunicationWay", body = communicationWaySave);

    fun communicationWays(type: CommunicationWayType) : List<CommunicationWay> =
        client.get("/CommunicationWay", mapOf(NO_LIMIT, "type" to type.name))
}