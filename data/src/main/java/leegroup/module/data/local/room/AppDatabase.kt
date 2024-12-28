package leegroup.module.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import leegroup.module.data.models.GitUser
import leegroup.module.data.models.GitUserDetail
import leegroup.module.data.models.User

@Database(entities = [User::class, GitUser::class, GitUserDetail::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun gitUserDao(): GitUserDao
    abstract fun gitUserDetailDao(): GitUserDetailDao
}