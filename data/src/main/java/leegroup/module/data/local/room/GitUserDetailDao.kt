package leegroup.module.data.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import leegroup.module.data.models.GitUserDetail

@Dao
interface GitUserDetailDao {

    @Query("SELECT * FROM gituserdetail WHERE login = :login LIMIT 1")
    fun getUserDetail(login: String): Flow<GitUserDetail>

    @Upsert
    suspend fun upsert(user: GitUserDetail)
}
