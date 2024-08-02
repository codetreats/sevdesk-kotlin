package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.*

data class ContactAddressSave(
    @Transient
    val contactId: String,
    val street: String,
    val zip: Int,
    val city: String,
    @Transient
    val countryId: String,
    @Transient
    val contactAddressCategory: ContactAddressCategory
) {
    val contact = ContactObject(contactId)
    val country = StaticCountryObject(countryId)
    val category = CategoryObject(contactAddressCategory.value.toString())
}

