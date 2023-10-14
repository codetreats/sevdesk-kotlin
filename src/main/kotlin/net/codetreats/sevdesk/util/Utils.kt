package net.codetreats.sevdesk.util

import java.text.SimpleDateFormat
import java.util.*


fun Date.unixTimestamp() : Long = time / 1000

fun Date.unixTimestampStr() : String = "${unixTimestamp()}"

fun Date.asTimestampParam(name: String) = Pair(name, unixTimestampStr())

fun Date.toAtom() : String = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(this)