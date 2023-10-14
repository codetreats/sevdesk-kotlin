package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.CategoryObject

data class ContactCreate(
    val surename: String,
    val familyname: String,
    val description: String,
    val name2: String,
    val category: CategoryObject
)
