package com.example.silverorange_assessment.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
): Parcelable