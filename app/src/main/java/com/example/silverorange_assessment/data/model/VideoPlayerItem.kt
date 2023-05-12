package com.example.silverorange_assessment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoPlayerItem(
    @SerializedName("author")
    val author: Author,
    @SerializedName("description")
    val description: String,
    @SerializedName("fullURL")
    val fullURL: String,
    @SerializedName("hlsURL")
    val hlsURL: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("title")
    val title: String
): Parcelable