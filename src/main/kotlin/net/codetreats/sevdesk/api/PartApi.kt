package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.Part
import java.lang.IllegalArgumentException

class PartApi(private val client: SevDeskClient) {

    fun get(partNumber: String, description: String? = null): Part {
        val parts = client.get<Part>("/Part", mapOf(NO_LIMIT, "partNumber" to partNumber))
        if (parts.isEmpty()) {
            val descr = description?.let { "($it)" } ?: ""
            throw IllegalArgumentException("No Part found for $partNumber $descr")
        }
        return parts.first()
    }

    fun get(): List<Part> = client.get<Part>("/Part", mapOf(NO_LIMIT))

    /**
     * @param itemNumber the chosen number, not the internal id
     */
    fun exists(itemNumber: String): Boolean = client.get<Part>("/Part", mapOf("partNumber" to itemNumber)).isNotEmpty()
}
