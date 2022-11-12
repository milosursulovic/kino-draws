package com.example.mozzartkino.data.model.results


import com.google.gson.annotations.SerializedName

data class ResultsDto(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("first")
    val first: Boolean,
    @SerializedName("last")
    val last: Boolean,
    @SerializedName("number")
    val number: Int,
    @SerializedName("numberOfElements")
    val numberOfElements: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val sort: List<Sort>,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)