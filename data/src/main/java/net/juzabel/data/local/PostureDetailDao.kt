package net.juzabel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostureDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(postureList: List<PostureDetailLocalModel>)

    @Query("SELECT * FROM PostureDetailLocalModel WHERE id = :id")
    suspend fun getPostureDetail(id: String): PostureDetailLocalModel
}