package uz.gita.contactappcompose.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.contactappcompose.data.local.room.dao.ContactDao
import uz.gita.contactappcompose.data.local.room.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getContactDao(): ContactDao
}