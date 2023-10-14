# sevdesk-kotlin

SevDesk-Kotlin is a library that can be used to access the REST-API of [SevDesk](https://sevdesk.de).

> **NOTE:** The author of this library is not related to SevDesk GmbH.
> It is a community project and serves only to simplify access to the SevDesk Api.


## Example Usage

```kotlin
val sevDeskClient = SevDeskClient("https://my.sevdesk.de/api/v1", System.getenv("SEVDESK_APIKEY"))
val sevDeskApi = SevDeskApi(sevDeskClient)
val invoices = sevDeskApi.invoices()
```