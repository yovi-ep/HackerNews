package yovi.putra.hackernews.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    val by: String? = "",
    val descendants: Int? = 0,
    val id: Int? = 0,
    val kids: MutableList<Int>? = mutableListOf(),
    val score: Int? = 0,
    val time: Long? = 0,
    val text: String? = "",
    val title: String? = "",
    val type: String? = "",
    val url: String? = ""
) : Parcelable