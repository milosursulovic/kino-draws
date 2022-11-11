package com.example.mozzartkino.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "draws")
data class Draw(
    val drawBreak: Int,
    @PrimaryKey
    val drawId: Int,
    val drawTime: Long,
    val gameId: Int,
    val status: String,
    val visualDraw: Int
) : Serializable
