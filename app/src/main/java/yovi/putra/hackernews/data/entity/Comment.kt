package yovi.putra.hackernews.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Comment(
    val by: String?,
    val id: Int?,
    val kids: MutableList<String>?,
    val parent: Int?,
    val text: String?,
    val time: Date?,
    val type: String?
) : Parcelable