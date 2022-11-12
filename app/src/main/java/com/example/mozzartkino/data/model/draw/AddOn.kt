package com.example.mozzartkino.data.model.draw


import com.google.gson.annotations.SerializedName

data class AddOn(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("gameType")
    val gameType: String
)