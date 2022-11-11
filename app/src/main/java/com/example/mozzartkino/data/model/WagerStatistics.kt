package com.example.mozzartkino.data.model


import com.google.gson.annotations.SerializedName

data class WagerStatistics(
    @SerializedName("addOn")
    val addOn: List<Any>,
    @SerializedName("columns")
    val columns: Int,
    @SerializedName("wagers")
    val wagers: Int
)