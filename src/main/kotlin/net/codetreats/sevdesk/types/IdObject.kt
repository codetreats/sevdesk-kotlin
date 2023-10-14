package net.codetreats.sevdesk.types

sealed class IdObject(val id: String, val objectName: String) {
    override fun toString(): String = "$objectName($id)"

    fun asParam() : Map<String, String> =
        mapOf("${objectName.decapitalize()}[id]" to id, "${objectName.decapitalize()}[objectName]" to objectName)
}

class CheckAccountObject(id: String) : IdObject(id, "CheckAccount")
class UnityObject(id: String) : IdObject(id, "Unity")
class TaxSetObject(id: String) : IdObject(id, "TaxSet")
class AccountingTypeObject(id: String) : IdObject(id, "AccountingType")
class CheckAccountTransactionObject(id: String) : IdObject(id, "CheckAccountTransaction")
class SevUserObject(id: String) : IdObject(id, "SevUser")
class ContactObject(id: String) : IdObject(id, "Contact")
class CommunicationWayKeyObject(id: String) : IdObject(id, "CommunicationWayKey")
class CategoryObject(id: String) : IdObject(id, "Category")
class StaticCountryObject(id: String) : IdObject(id, "StaticCountry")
class InvoiceObject(id: String) : IdObject(id, "Invoice")
class PartObject(id: String) : IdObject(id, "PartObject")
class DocumentObject(id: String) : IdObject(id, "DocumentObject")