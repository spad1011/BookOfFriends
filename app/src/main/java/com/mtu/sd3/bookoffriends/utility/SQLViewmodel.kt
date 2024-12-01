package com.mtu.sd3.bookoffriends.utility

import android.app.Application
import android.util.Log
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
    val friends = _friends.asStateFlow()

    private val _friend = MutableStateFlow<Friend?>(null)
    val friend = _friend.asStateFlow()

    fun getAllLite(
        firstName: String?,
        lastName: String?,
        birthYear: String?
    ) {
        Log.d("SQLViewModel", "Getting all lite friends")
        viewModelScope.launch {
            _friends.value = friendDao.getAllLite(firstName, lastName, birthYear)
        }
    }

    fun getFriendById(id: Int) {
        viewModelScope.launch {
            _friend.value = friendDao.getFriendById(id)
        }
    }

    fun insertFriend(friend: Friend) {
        Log.d("SQLViewModel", "Inserting friend: $friend")
        viewModelScope.launch {
            friendDao.insert(friend)
        }
    }

}

