package com.example.mozzartkino.data.model.results


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("ascending")
    val ascending: Boolean,
    @SerializedName("descending")
    val descending: Boolean,
    @SerializedName("direction")
    val direction: String,
    @SerializedName("ignoreCase")
    val ignoreCase: Boolean,
    @SerializedName("nullHandling")
    val nullHandling: String,
    @SerializedName("property")
    val `property`: String
)