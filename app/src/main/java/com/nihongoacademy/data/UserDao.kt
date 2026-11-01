package com.nihongoacademy.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun findByUsername(username: String): UserEntity?

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    suspend fun findById(userId: String): UserEntity?

    @Query("UPDATE users SET passwordHash = :passwordHash, passwordSalt = :passwordSalt, iterations = :iterations, recoveryPhrase = :recoveryPhrase WHERE userId = :userId")
    suspend fun updatePassword(userId: String, passwordHash: String, passwordSalt: String, iterations: Int, recoveryPhrase: String)
}
