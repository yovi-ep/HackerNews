package yovi.putra.hackernews.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Story(
    val by: String? = "",
    val descendants: Int? = 0,
    val id: Int? = 0,
    val kids: MutableList<String>? = mutableListOf(),
    val score: Int? = 0,
    val time: Date? = Date(),
    val title: String? = "",
    val type: String? = "",
    val url: String? = ""
) : Parcelable