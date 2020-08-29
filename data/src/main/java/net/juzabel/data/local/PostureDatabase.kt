package net.juzabel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostureLocalModel::class, PostureDetailLocalModel::class], version = 1)
abstract class PostureDatabase : RoomDatabase(){
    abstract fun postureDao() : PostureDao
    abstract fun postureDetailDao() : PostureDetailDao
}