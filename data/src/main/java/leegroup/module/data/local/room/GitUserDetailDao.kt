package leegroup.module.data.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import leegroup.module.data.models.GitUserDetail

@Dao
interface GitUserDetailDao {

    @Query("SELECT * FROM gituserdetail WHERE login = :login")
    suspend fun getUserDetail(login: String): GitUserDetail?

    @Upsert
    suspend fun upsert(user: GitUserDetail)
}
