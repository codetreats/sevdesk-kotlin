package net.codetreats.sevdesk

data class SevDeskListResponse<T>(val objects: List<T>)

data class SevDeskElementResponse<T>(val objects: T)
