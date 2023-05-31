package uz.gita.contactappcompose.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.gita.contactappcompose.data.local.room.entity.ContactEntity

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(contactEntity: ContactEntity)

    @Delete
    fun delete(contactEntity: ContactEntity)

    @Update
    fun update(contactEntity: ContactEntity)

    @Query("Select * from contacts")
    fun retrieveAllContacts(): Flow<List<ContactEntity>>

}