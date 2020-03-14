package yovi.putra.hackernews.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Story(
    val by: String?,
    val descendants: Int?,
    val id: Int?,
    val kids: MutableList<String>?,
    val score: Int?,
    val time: Date?,
    val title: String?,
    val type: String?,
    val url: String?
) : Parcelable