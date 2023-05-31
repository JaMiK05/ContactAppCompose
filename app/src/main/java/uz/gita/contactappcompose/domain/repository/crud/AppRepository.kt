package uz.gita.contactappcompose.domain.repository.crud

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappcompose.data.model.ContactData

interface AppRepository {

    suspend fun add(contactData: ContactData)
    suspend fun delete(contactData: ContactData)
    suspend fun update(contactData: ContactData)
    fun retrieveAllContacts(): Flow<List<ContactData>>
}