package com.example.mozzartkino.data.model.draw


import com.google.gson.annotations.SerializedName

data class PricePoints(
    @SerializedName("addOn")
    val addOn: List<AddOn>,
    @SerializedName("amount")
    val amount: Double
)