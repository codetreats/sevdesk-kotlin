package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.*

data class ContactAddressSave(
    val contact: ContactObject,
    val street: String,
    val zip: String,
    val city: String,
    val country: StaticCountryObject,
    val category: CategoryObject
)

