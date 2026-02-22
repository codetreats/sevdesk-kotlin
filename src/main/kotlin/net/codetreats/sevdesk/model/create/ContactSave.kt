package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.CategoryObject

data class ContactSave(
    val surename: String,
    val familyname: String,
    val description: String,
    val name2: String,
    val category: CategoryObject,
)
