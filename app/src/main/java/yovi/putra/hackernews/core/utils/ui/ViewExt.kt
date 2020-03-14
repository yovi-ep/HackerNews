package yovi.putra.hackernews.core.utils.ui

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible() : Boolean = visibility == View.VISIBLE