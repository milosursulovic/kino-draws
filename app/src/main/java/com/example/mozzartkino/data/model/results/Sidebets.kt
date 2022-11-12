package com.example.mozzartkino.data.model.results


import com.google.gson.annotations.SerializedName

data class Sidebets(
    @SerializedName("columnNumbers")
    val columnNumbers: ColumnNumbers,
    @SerializedName("evenNumbers")
    val evenNumbers: List<Int>,
    @SerializedName("evenNumbersCount")
    val evenNumbersCount: Int,
    @SerializedName("oddNumbers")
    val oddNumbers: List<Int>,
    @SerializedName("oddNumbersCount")
    val oddNumbersCount: Int,
    @SerializedName("winningColumn")
    val winningColumn: Int,
    @SerializedName("winningParity")
    val winningParity: String
)