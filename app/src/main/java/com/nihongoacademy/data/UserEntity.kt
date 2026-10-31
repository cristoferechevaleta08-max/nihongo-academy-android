package com.nihongoacademy.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class UserEntity(
    @PrimaryKey val userId: String,
    val visibleName: String,
    val username: String,
    val passwordHash: String,
    val passwordSalt: String,
    val iterations: Int,
    val recoveryPhrase: String,
    val createdAt: Long
)
