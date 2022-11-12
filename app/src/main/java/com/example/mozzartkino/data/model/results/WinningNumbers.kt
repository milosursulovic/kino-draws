package com.example.mozzartkino.data.model.results


import com.google.gson.annotations.SerializedName

data class WinningNumbers(
    @SerializedName("bonus")
    val bonus: List<Int>,
    @SerializedName("list")
    val list: List<Int>,
    @SerializedName("sidebets")
    val sidebets: Sidebets
)