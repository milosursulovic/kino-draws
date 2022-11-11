package com.example.mozzartkino.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "draws")
data class Draw(
    @SerializedName("drawBreak")
    val drawBreak: Int,
    @PrimaryKey
    @SerializedName("drawId")
    val drawId: Int,
    @SerializedName("drawTime")
    val drawTime: Long,
    @SerializedName("gameId")
    val gameId: Int,
    @SerializedName("pricePoints")
    val pricePoints: PricePoints,
    @SerializedName("prizeCategories")
    val prizeCategories: List<PrizeCategory>,
    @SerializedName("status")
    val status: String,
    @SerializedName("visualDraw")
    val visualDraw: Int,
    @SerializedName("wagerStatistics")
    val wagerStatistics: WagerStatistics
) : Serializable