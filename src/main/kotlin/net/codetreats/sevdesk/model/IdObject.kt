package net.codetreats.sevdesk.model

sealed class IdObject(val id: String) {
    abstract val objectName: String
    override fun toString(): String = "$objectName($id)"

    fun asParam() : Map<String, String> =
        mapOf("${objectName.decapitalize()}[id]" to id, "${objectName.decapitalize()}[objectName]" to objectName)
}

class CheckAccountObject(id: String) : IdObject(id) { override val objectName: String = "CheckAccount" }
class UnityObject(id: String) : IdObject(id) { override val objectName: String = "Unity" }
class TaxSetObject(id: String) : IdObject(id) { override val objectName: String = "TaxSet" }
class AccountingTypeObject(id: String) : IdObject(id) { 
    override val objectName: String = "AccountingType" 
    companion object {
        const val ACCOUNT_CARD_FEE = "74"
        const val MINIJOB_WAGE = "25228"
        const val MINIJOB_TAX = "25227"
    }
}
class CheckAccountTransactionObject(id: String) : IdObject(id) { override val objectName: String = "CheckAccountTransaction" }
class SevUserObject(id: String) : IdObject(id) { override val objectName: String = "SevUser" }
class ContactObject(id: String) : IdObject(id) { override val objectName: String = "Contact" }
class CommunicationWayKeyObject(id: String) : IdObject(id) { override val objectName: String = "CommunicationWayKey" }
class CategoryObject(id: String) : IdObject(id) { override val objectName: String = "Category" }
class StaticCountryObject(id: String) : IdObject(id) { override val objectName: String = "StaticCountry" }
class InvoiceObject(id: String) : IdObject(id) { override val objectName: String = "Invoice" }
class PartObject(id: String) : IdObject(id) { override val objectName: String = "PartObject" }
class DocumentObject(id: String) : IdObject(id) { override val objectName: String = "DocumentObject" }