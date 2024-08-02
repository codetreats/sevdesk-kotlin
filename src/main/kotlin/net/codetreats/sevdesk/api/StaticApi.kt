package net.codetreats.sevdesk.api

import net.codetreats.sevdesk.NO_LIMIT
import net.codetreats.sevdesk.SevDeskClient
import net.codetreats.sevdesk.model.StaticCountry

class StaticApi(private val client: SevDeskClient) {
    fun countries() : List<StaticCountry> =
        client.get<StaticCountry>("/StaticCountry", mapOf(NO_LIMIT))
}