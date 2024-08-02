package net.codetreats.sevdesk.types

sealed class IdObject(val id: String, val objectName: String) {
    override fun toString(): String = "$objectName($id)"

    fun asParam() : Map<String, String> =
        mapOf("${objectName.decapitalize()}[id]" to id, "${objectName.decapitalize()}[objectName]" to objectName)
}

class CheckAccountObject(id: String, objectName: String = "CheckAccount") : IdObject(id, objectName)
class UnityObject(id: String, objectName: String = "Unity") : IdObject(id, objectName)
class TaxSetObject(id: String, objectName: String = "TaxSet") : IdObject(id, objectName)
class AccountingTypeObject(id: String, objectName: String = "AccountingType") : IdObject(id, objectName) {
    companion object {
        const val ACCOUNT_CARD_FEE = "74"
        const val MINIJOB_WAGE = "25228"
        const val MINIJOB_TAX = "25227"
    }
}
class CheckAccountTransactionObject(id: String, objectName: String = "CheckAccountTransaction") : IdObject(id, objectName)
class SevUserObject(id: String, objectName: String = "SevUser") : IdObject(id, objectName)
class ContactObject(id: String, objectName: String = "Contact") : IdObject(id, objectName)
class CommunicationWayKeyObject(id: String, objectName: String = "CommunicationWayKey") : IdObject(id, objectName)
class CategoryObject(id: String, objectName: String = "Category") : IdObject(id, objectName)
class StaticCountryObject(id: String, objectName: String = "StaticCountry") : IdObject(id, objectName)
class InvoiceObject(id: String, objectName: String = "Invoice") : IdObject(id, objectName)
class PartObject(id: String, objectName: String = "PartObject") : IdObject(id, objectName)
class DocumentObject(id: String, objectName: String = "DocumentObject") : IdObject(id, objectName)