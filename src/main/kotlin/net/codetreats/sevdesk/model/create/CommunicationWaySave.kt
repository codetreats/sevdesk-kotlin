package net.codetreats.sevdesk.model.create

import net.codetreats.sevdesk.model.CommunicationWayKeyObject
import net.codetreats.sevdesk.model.CommunicationWayType
import net.codetreats.sevdesk.model.ContactObject

data class CommunicationWaySave(
    val contact: ContactObject,
    val type: CommunicationWayType,
    val value: String,
    val main: Boolean,
    val key: CommunicationWayKeyObject,
)
