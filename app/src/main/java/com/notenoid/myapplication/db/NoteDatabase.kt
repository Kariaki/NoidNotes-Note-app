package com.notenoid.notenoid.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.notenoid.myapplication.adapters.TaskEntity
import com.notenoid.myapplication.entities.NoteEntity
import com.notenoid.myapplication.entities.TaskEvent

@Database(entities = [NoteEntity::class,TaskEntity::class,TaskEvent::class],version = 10,exportSchema = false)

abstract class NoteDatabase :RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?=null

        fun getDatabase(context: Context):NoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}