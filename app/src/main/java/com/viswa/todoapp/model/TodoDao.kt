package com.viswa.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: Todo)
    @Query("SELECT * from todo ORDER BY priority DESC")
    fun selectAllTodo():List<Todo>
    //Simpan dalam unix timestamp dan variabel bahasa manusia kalau mau sort by tanggal
    @Query("SELECT * from todo WHERE uuid = :id")
    fun selectTodo(id:Int):Todo
    @Delete
    fun deleteTodo(todo: Todo)
    //Method Update #2
    @Update
    fun updateTodo(todo:Todo)
    //Method Update #1
    @Query("UPDATE todo " +
            "SET title = :title, notes = :notes, priority = :priority " +
            "WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, id:Int)
    //Query update isDone
    @Query("UPDATE todo SET is_done = :isDone WHERE uuid = :id")
    fun updateIsDone(id: Int, isDone: Int)
}