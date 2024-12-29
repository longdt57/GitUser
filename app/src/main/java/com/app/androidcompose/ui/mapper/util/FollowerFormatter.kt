package com.app.androidcompose.ui.mapper.util

object FollowerFormatter {
    fun formatLargeNumber(value: Int): String {
        return if (value >= 1000) "999+" else value.toString()
    }
}