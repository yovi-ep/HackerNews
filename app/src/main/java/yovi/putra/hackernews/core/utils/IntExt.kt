package yovi.putra.hackernews.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Int.toDate(): String =
    SimpleDateFormat("dd-MMM/yyyy HH:mm", Locale.getDefault()).format(Date(this.toLong()))