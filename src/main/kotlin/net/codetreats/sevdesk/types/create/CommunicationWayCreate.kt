package net.codetreats.sevdesk.types.create

import net.codetreats.sevdesk.types.CommunicationWayKey
import net.codetreats.sevdesk.types.CommunicationWayKeyObject
import net.codetreats.sevdesk.types.CommunicationWayType
import net.codetreats.sevdesk.types.ContactObject

data class CommunicationWayCreate(
    val contact: ContactObject,
    val type: CommunicationWayType,
    val value: String,
    val main: Boolean,
    val key: CommunicationWayKeyObject
)