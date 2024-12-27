package leegroup.module.data.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import leegroup.module.data.models.User

@Dao()
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllAsFlow(): Flow<List<User>>

    @Upsert
    suspend fun upsert(users: List<User>)

    @Query("DELETE FROM user")
    suspend fun clearTable()
}