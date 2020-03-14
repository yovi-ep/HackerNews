package yovi.putra.hackernews.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Int.toDateString(): String =
    SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault()).format(Date(this.toLong()))