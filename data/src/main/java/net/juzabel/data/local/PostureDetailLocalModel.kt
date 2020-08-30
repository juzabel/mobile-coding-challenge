package net.juzabel.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostureDetailLocalModel(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "teacher") val teacher: String,
    @ColumnInfo(name = "duration")  val duration: String,
    @ColumnInfo(name = "picture")  val picture: String,
    @ColumnInfo(name = "description")  val description: String
)