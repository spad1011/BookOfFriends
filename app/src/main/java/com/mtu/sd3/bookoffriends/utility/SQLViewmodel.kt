package com.mtu.sd3.bookoffriends.utility

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mtu.sd3.bookoffriends.entity.Friend
import com.mtu.sd3.bookoffriends.entity.FriendLite
import com.mtu.sd3.bookoffriends.repository.FriendDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SQLViewmodel(application: Application) : AndroidViewModel(application) {
    private val friendDao = FriendDatabase.getDatabase(application).friendDao()

    private val _friends = MutableStateFlow<List<FriendLite>>(emptyList())
    public val friends = _friends.asStateFlow()

    fun getAllLite(
        firstName: String,
        lastName: String,
        birthYear: String
    ): List<FriendLite> {
        return friendDao.getAllLite(
            if (firstName.isBlank()) null else "%${firstName}%",
            if (lastName.isBlank()) null else "%${lastName}%",
            if (birthYear.isBlank()) null else "%${birthYear}%",
        )
    }
    fun getFriendById(id: Int): Friend {
        return friendDao.getFriendById(id)
    }

    fun insertFriend(friend: Friend){
        viewModelScope.launch{
            friendDao.insert(friend)
        }
    }

}

