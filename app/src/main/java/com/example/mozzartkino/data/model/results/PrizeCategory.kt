package com.example.mozzartkino.data.model.results


import com.google.gson.annotations.SerializedName

data class PrizeCategory(
    @SerializedName("categoryType")
    val categoryType: Int,
    @SerializedName("distributed")
    val distributed: Double,
    @SerializedName("divident")
    val divident: Double,
    @SerializedName("fixed")
    val fixed: Double,
    @SerializedName("gameType")
    val gameType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jackpot")
    val jackpot: Int,
    @SerializedName("winners")
    val winners: Int
)