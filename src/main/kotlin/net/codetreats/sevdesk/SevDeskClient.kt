package net.codetreats.sevdesk

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.codetreats.rest.RestClient
import net.codetreats.sevdesk.model.*
import net.codetreats.sevdesk.util.LocalDateTimeAdapter
import java.time.LocalDateTime
import java.util.logging.Logger
import java.util.*

/**
 * Creates an instance of the API
 * @param apiUrl the URL of the API
 * @param apiKey the SevDesk API key (can be found under extensions->API)
 * @param logger an optional logger
 */
class SevDeskClient(
    apiUrl: String,
    apiKey: String,
    val logger: Logger? = null
) {
    val restClient = RestClient(apiUrl, mapOf("Authorization" to apiKey))
    val moshi: Moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(LocalDateTime::class.java, LocalDateTimeAdapter())
        .add(CheckAccountTransactionStatusAdapter())
        .add(InvoiceTypeAdapter())
        .add(InvoiceStatusAdapter())
        .add(VoucherStatusAdapter())
        .add(ContactStatusAdapter())
        .add(CommunicationWayTypeAdapter())
        .add(CommunicationWayKeyAdapter())
        .add(ContactAddressCategoryAdapter())
        .add(PartStatusAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    inline fun <reified T> get(
        subUrl: String,
        params: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap()
    ): List<T> {
        logger?.info("GET $subUrl${params.asUrl()} ${headers.asList()}")
        val message = restClient.get(subUrl, params, headers).message!!
        val responseType = Types.newParameterizedType(SevDeskListResponse::class.java, T::class.java)
        val adapter: JsonAdapter<SevDeskListResponse<T>> = moshi.adapter(responseType)
        return adapter.fromJson(message)!!.objects
    }

    inline fun <reified T> post(
        subUrl: String,
        params: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
        body: T
    ) {
        logger?.info("POST $subUrl${params.asUrl()} ${headers.asList()}")
        val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        val bodyString = adapter.toJson(body)
        logger?.fine("Content ${bodyString}")
        restClient.post(subUrl, params, headers, bodyString)
    }

    inline fun <reified T> getElement(
        subUrl: String,
        params: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap()
    ): T {
        logger?.info("GET $subUrl${params.asUrl()} (${headers.asList()})")
        val message = restClient.get(subUrl, params, headers).message!!
        val responseType = Types.newParameterizedType(SevDeskElementResponse::class.java, T::class.java)
        val adapter: JsonAdapter<SevDeskElementResponse<T>> = moshi.adapter(responseType)
        return adapter.fromJson(message)!!.objects
    }

    fun Map<String, String>.asUrl() = if (isEmpty()) {
        ""
    } else {
        entries.joinToString("&", prefix = "?") { (k, v) -> "$k=$v" }
    }

    fun Map<String, String>.asList() = if (isEmpty()) {
        ""
    } else {
        entries.joinToString(",", "(", ")") { (k, v) -> "$k=$v" }
    }
}
