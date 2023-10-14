package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.*

data class ContactAddressCreate(
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

