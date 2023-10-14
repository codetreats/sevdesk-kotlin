package net.codetreats.sevdesk.types

data class StaticCountry(
    val id: String,
    val code: String,
    val name: String,
    val nameEn: String?,
    val translationCode: String,
    val locale: String,
    val additionalInformation: String?,
    val priority: String?
)

/**
 * @param country the name of the country. Can be the ISO-Code
 */
fun List<StaticCountry>.getCountry(countryNameOrCode: String) : StaticCountry? =
    firstOrNull { it.isLike(countryNameOrCode) }

private fun StaticCountry.isLike(countryNameOrCode: String) =
    code.lowercase() == countryNameOrCode.lowercase() ||
            name.lowercase() == countryNameOrCode.lowercase() ||
            translationCode.lowercase() == "country_${countryNameOrCode.lowercase()}"
