package com.mtu.sd3.bookoffriends.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mtu.sd3.bookoffriends.entity.Friend
import com.mtu.sd3.bookoffriends.entity.FriendLite

@Dao
interface FriendDAO {
    @Insert
    suspend fun insert(friend: Friend)

    @Query(
        """
        SELECT id, firstName, lastName, age, address, phoneNumber
        FROM friend_table
        WHERE(:firstName IS NULL OR firstName LIKE :firstName)
        AND (:lastName IS NULL OR lastName LIKE :lastName)
        AND (:birthYear IS NULL OR birthdate LIKE :birthYear)
        """
    )
    fun getAllLite(firstName: String?, lastName: String?, birthYear: String?): List<FriendLite>

    @Query("SELECT * FROM friend_table WHERE id = :id")
    fun getFriendById(id: Int): Friend


}