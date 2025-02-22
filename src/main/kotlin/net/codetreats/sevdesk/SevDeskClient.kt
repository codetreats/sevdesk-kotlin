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
import org.apache.logging.log4j.Logger
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
        .add(OrderTypeAdapter())
        .add(OrderStatusAdapter())
        .add(VoucherStatusAdapter())
        .add(ContactStatusAdapter())
        .add(CommunicationWayTypeAdapter())
        .add(CommunicationWayKeyAdapter())
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

    inline fun <reified T, reified U> post(
        subUrl: String,
        params: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
        body: T
    ) : U {
        logger?.info("POST $subUrl${params.asUrl()} ${headers.asList()}")
        val adapterT: JsonAdapter<T> = moshi.adapter(T::class.java)
        val bodyString = adapterT.toJson(body)
        logger?.trace("Content: ${bodyString}")
        val message = restClient.post(subUrl, params, headers, bodyString).message!!
        logger?.trace("Result: ${message}")
        val responseType = Types.newParameterizedType(SevDeskElementResponse::class.java, U::class.java)
        val adapterU: JsonAdapter<SevDeskElementResponse<U>> = moshi.adapter(responseType)
        return adapterU.fromJson(message)!!.objects
    }

    inline fun <reified T, reified U> put(
        subUrl: String,
        params: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
        body: T
    ) : U {
        logger?.info("PUT $subUrl${params.asUrl()} ${headers.asList()}")
        val adapterT: JsonAdapter<T> = moshi.adapter(T::class.java)
        val bodyString = adapterT.toJson(body)
        logger?.trace("Content: ${bodyString}")
        val message = restClient.put(subUrl, params, headers, bodyString).message!!
        logger?.trace("Result: ${message}")
        val responseType = Types.newParameterizedType(SevDeskElementResponse::class.java, U::class.java)
        val adapterU: JsonAdapter<SevDeskElementResponse<U>> = moshi.adapter(responseType)
        return adapterU.fromJson(message)!!.objects
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
