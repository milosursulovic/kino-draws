package com.example.mozzartkino.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Draw(
    @SerializedName("drawBreak")
    val drawBreak: Int,
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
): Serializable