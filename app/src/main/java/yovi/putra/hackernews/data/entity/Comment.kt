package yovi.putra.hackernews.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val by: String?,
    val id: Int?,
    val kids: MutableList<String>?,
    val parent: Int?,
    val text: String?,
    val time: Int?,
    val type: String?
) : Parcelable