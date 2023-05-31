package uz.gita.contactappcompose.domain.repository.crud

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import uz.gita.contactappcompose.data.local.room.dao.ContactDao
import uz.gita.contactappcompose.data.model.ContactData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    val dao: ContactDao,
) : AppRepository {

    override suspend fun add(contactData: ContactData) {
        withContext(Dispatchers.IO) {
            dao.add(contactData.toEntity())
        }
    }

    override suspend fun delete(contactData: ContactData) {
        withContext(Dispatchers.IO) {
            dao.delete(contactData.toEntity())
        }
    }

    override suspend fun update(contactData: ContactData) {
        withContext(Dispatchers.IO) {
            dao.update(contactData.toEntity())
        }
    }

    override fun retrieveAllContacts(): Flow<List<ContactData>> =
        dao.retrieveAllContacts().map {
            it.map {
                it.toData()
            }
        }

}