package net.codetreats.sevdesk.model

import java.time.LocalDateTime

data class ContactAddress(
    val id: String,
    val create: LocalDateTime,
    val update: LocalDateTime,
    val contact: ContactObject,
    val street: String,
    val zip: String,
    val city: String,
    val country: StaticCountryObject,
    val category: CategoryObject,
    val name: String?
)
