package net.codetreats.sevdesk.model

import java.time.LocalDateTime

interface Document {
    val id: String
    val contact: ContactObject
    val create: LocalDateTime
    val update: LocalDateTime
    val header: String
    val headText: String?
    val footText: String?
    val addressCountry: StaticCountryObject?
    val createUser: SevUserObject
    val contactPerson: ContactObject
    val address: String
    val customerInternalNote: String?

    fun type(): String
    fun status(): Int
    fun date(): LocalDateTime
    fun number(): String
}