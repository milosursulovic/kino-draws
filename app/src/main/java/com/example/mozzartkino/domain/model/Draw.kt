package com.example.mozzartkino.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "draws")
data class Draw(
    @PrimaryKey
    val drawId: Int,
    val drawTime: Long,
    val submitedNumbers: String
) : Serializable
