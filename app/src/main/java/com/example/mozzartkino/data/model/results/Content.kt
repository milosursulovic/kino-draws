package com.example.mozzartkino.data.model.results


import com.example.mozzartkino.domain.model.Draw
import com.google.gson.annotations.SerializedName

data class Content(
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
    val wagerStatistics: WagerStatistics,
    @SerializedName("winningNumbers")
    val winningNumbers: WinningNumbers
)

fun Content.toDraw(): Draw {
    return Draw(
        null,
        drawId,
        drawTime,
        winningNumbers.list.joinToString(":"),
        calculateQuota(winningNumbers.list.size)
    )
}

fun calculateQuota(listSize: Int) = when (listSize) {
    1 -> 4.00
    2 -> 18.00
    3 -> 85.00
    4 -> 400.00
    5 -> 2000.00
    6 -> 10000.00
    7 -> 50000.00
    8 -> 250000.00
    else -> 0.0
}