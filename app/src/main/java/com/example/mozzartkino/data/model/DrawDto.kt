package com.example.mozzartkino.data.model


import androidx.room.PrimaryKey
import com.example.mozzartkino.domain.model.Draw
import com.google.gson.annotations.SerializedName

data class DrawDto(
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
)

fun DrawDto.toDraw(): Draw {
    return Draw(
        drawBreak,
        drawId,
        drawTime,
        gameId,
        status,
        visualDraw
    )
}