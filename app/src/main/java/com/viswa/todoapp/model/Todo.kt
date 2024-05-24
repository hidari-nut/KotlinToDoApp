package com.viswa.todoapp.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done")
    var is_done:Int = 0 //In practice I think we are better to use integer instead of boolean because it is more efficient as it only requires a single bit to store values.
    // You would store '1' or '0' instead of 'True' or 'False'.
    // Also, specifically for the case of 'Room', apparently, SQLite is not capable of storing boolean value.
    ){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
    }


