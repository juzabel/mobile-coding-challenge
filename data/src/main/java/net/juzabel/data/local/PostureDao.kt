package net.juzabel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostureDao {
    @Query("SELECT * FROM PostureLocalModel")
    suspend fun getAll(): List<PostureLocalModel>

    @Insert
    suspend fun insertAll(postureList: List<PostureLocalModel>)
}