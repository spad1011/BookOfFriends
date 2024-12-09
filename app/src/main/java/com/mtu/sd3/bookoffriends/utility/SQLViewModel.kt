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

class SQLViewModel(application: Application) : AndroidViewModel(application) {
    private val friendDao = FriendDatabase.getDatabase(application).friendDao()

    private val _friends = MutableStateFlow<List<FriendLite>>(emptyList())
    val friends = _friends.asStateFlow()

    private val _friend = MutableStateFlow<Friend?>(null)
    val friend = _friend.asStateFlow()

    private val _insertionResult = MutableStateFlow<InsertState>(InsertState.Idle)
    val insertionResult = _insertionResult.asStateFlow()

    fun getAllLite(
        firstName: String?,
        lastName: String?,
        birthYear: String?
    ) {
        Log.d("SQLViewModel", "Getting all lite friends, inputs: $firstName, $lastName, $birthYear")
        val test = "%$firstName%"
        val test2 = "%$birthYear%"
        Log.d("SQLViewModel", "test: $test, test2: $test2")
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
            try {
                _insertionResult.value = InsertState.Loading
                val result = friendDao.insert(friend)
                if (result > 0 ) {
                    _insertionResult.value = InsertState.Success
                } else {
                    _insertionResult.value = InsertState.Failure("Failed to insert friend. Room returned invalid ID")
                }
            } catch (e: Exception) {
                _insertionResult.value = InsertState.Failure("Error inserting friend: ${e.message ?: "Unknown error"}")
            }
        }
    }

    fun resetInsertState() {
        _insertionResult.value = InsertState.Idle
    }

}


sealed class InsertState {
    object Idle : InsertState()
    object Success : InsertState()
    object Loading : InsertState()
    data class Failure(val errorMessage: String) : InsertState()
}

