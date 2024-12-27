package leegroup.module.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import leegroup.module.data.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}