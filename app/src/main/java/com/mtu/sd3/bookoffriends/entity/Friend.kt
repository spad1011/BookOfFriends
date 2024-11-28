package com.mtu.sd3.bookoffriends.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_table")
data class Friend (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var firstName: String = "",
    var lastName: String = "",
    var age: Int = -1,
    var height: String = "",
    var address: String = "",
    var phoneNumber: String = "",
    var occupation: String = "",
    var hobbies: String = "",
    var birthdate: String = "",
    var birthplace: String = "",
    var favFood: String = "",
    var favMovie: String = "",
    var mostLoved: String = "",
    var mostHated: String = "",
    var messageToOwner: String = "",
)