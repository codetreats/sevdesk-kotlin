package net.codetreats.sevdesk.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDateTime.unixTimestamp(): Long = this.toEpochSecond(ZoneId.systemDefault().rules.getOffset(this))

fun LocalDateTime.unixTimestampStr(): String = "${unixTimestamp()}"

fun LocalDateTime.asTimestampParam(name: String) = Pair(name, unixTimestampStr())
