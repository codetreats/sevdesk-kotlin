package net.codetreats.sevdesk.types

data class SevDeskListResponse<T>(val objects: List<T>)

data class SevDeskElementResponse<T>(val objects: T)