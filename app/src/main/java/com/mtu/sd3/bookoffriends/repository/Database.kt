package com.mtu.sd3.bookoffriends.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mtu.sd3.bookoffriends.entity.Friend
import com.mtu.sd3.bookoffriends.repository.dao.FriendDAO

@Database(
    entities = [Friend::class],
    version = 1,
    exportSchema = false
)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDAO

    companion object {
        @Volatile
        private var INSTANCE: FriendDatabase? = null
        fun getDatabase(context: Context): FriendDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendDatabase::class.java,
                    "friend_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}