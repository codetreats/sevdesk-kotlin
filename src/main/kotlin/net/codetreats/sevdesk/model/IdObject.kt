package net.codetreats.sevdesk.model

sealed class IdObject(val id: String, val objectName: String) {
    override fun toString(): String = "$objectName($id)"

    fun asParam() : Map<String, String> =
        mapOf("${objectName.decapitalize()}[id]" to id, "${objectName.decapitalize()}[objectName]" to objectName)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IdObject) return false
        if (id != other.id) return false
        if (objectName != other.objectName) return false
        return true
    }
    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + objectName.hashCode()
        return result
    }
}

class CheckAccountObject(id: String, objectName: String = "CheckAccount") : IdObject(id, objectName)
class UnityObject(id: String, objectName: String = "Unity") : IdObject(id, objectName) {
    companion object {
        val PIECE = UnityObject("1")
        val SQUARE_METER = UnityObject("2")
        val METER = UnityObject("3")
        val KILOGRAMM = UnityObject("4")
        val TON = UnityObject("5")
        val RUNNING_METER = UnityObject("6")
        val BLANKET = UnityObject("7")
        val CUBIC_METER = UnityObject("8")
        val HOUR = UnityObject("9")
        val KILOMETER = UnityObject("10")
        val PERCENT = UnityObject("11")
        val DAY = UnityObject("13")
        val LITER = UnityObject("15")
        val EMPTY = UnityObject("37")
    }
}
class TaxSetObject(id: String, objectName: String = "TaxSet") : IdObject(id, objectName)
class AccountingTypeObject(id: String, objectName: String = "AccountingType") : IdObject(id, objectName) {
    companion object {
        val ACCOUNT_CARD_FEE = AccountingTypeObject("74")
        val MINIJOB_WAGE = AccountingTypeObject("25228")
        val MINIJOB_TAX = AccountingTypeObject("25227")
    }
}
class AccountDatevObject(id: String, objectName: String = "AccountDatev") : IdObject(id, objectName) {
    companion object {
        val ACCOUNT_CARD_FEE = AccountDatevObject("4285")
        val MINIJOB_WAGE = AccountDatevObject("4094")
        val MINIJOB_TAX = AccountDatevObject("4095")
    }
}
class CheckAccountTransactionObject(id: String, objectName: String = "CheckAccountTransaction") : IdObject(id, objectName)
class SevUserObject(id: String, objectName: String = "SevUser") : IdObject(id, objectName)
class ContactObject(id: String, objectName: String = "Contact") : IdObject(id, objectName)
class CommunicationWayKeyObject(id: String, objectName: String = "CommunicationWayKey") : IdObject(id, objectName) {
    companion object {
        val PRIVAT = CommunicationWayKeyObject("1")
        val WORK = CommunicationWayKeyObject("2")
        val FAX = CommunicationWayKeyObject("3")
        val MOBILE = CommunicationWayKeyObject("4")
        val EMPTY = CommunicationWayKeyObject("5")
        val AUTOBOX = CommunicationWayKeyObject("6")
        val NEWSLETTER = CommunicationWayKeyObject("7")
        val INVOICE_ADDRESS = CommunicationWayKeyObject("8")
    }
}
class CategoryObject(id: String, objectName: String = "Category") : IdObject(id, objectName) {
    companion object {
        val CUSTOMER = CategoryObject("3")
        val PARTNER = CategoryObject("4")
        val PRIVAT = CategoryObject("44")
        val EMPTY = CategoryObject("45")
        val INVOICE_ADDRESS = CategoryObject("47")
        val DELIVERY_ADDRESS = CategoryObject("48")
    }
}
class StaticCountryObject(id: String, objectName: String = "StaticCountry") : IdObject(id, objectName) {
    companion object {
        val GERMANY = StaticCountryObject("1")
        val SWISS = StaticCountryObject("2")
        val AUSTRIA = StaticCountryObject("3")
    }
}
class InvoiceObject(id: String, objectName: String = "Invoice") : IdObject(id, objectName)
class PartObject(id: String, objectName: String = "Part") : IdObject(id, objectName)
class DocumentObject(id: String, objectName: String = "Document") : IdObject(id, objectName)

class OrderObject(id: String, objectName: String = "Order") : IdObject(id, objectName)