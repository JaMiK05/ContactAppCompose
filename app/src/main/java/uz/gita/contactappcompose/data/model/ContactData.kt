package uz.gita.contactappcompose.data.model

import uz.gita.contactappcompose.data.local.room.entity.ContactEntity

data class ContactData(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
) {
    fun toEntity() = ContactEntity(
        id, firstName, lastName, phone
    )
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        val data = other as ContactData
        return this.id == data.id || this.phone == data.phone
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + phone.hashCode()
        return result
    }
}
