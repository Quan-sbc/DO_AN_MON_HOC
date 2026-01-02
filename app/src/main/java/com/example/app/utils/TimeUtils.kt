package com.example.app.utils


import java.text.SimpleDateFormat
import java.util.*

fun formatTimeAgo(createdAt: Long): String {
    val diff = System.currentTimeMillis() - createdAt

    if (diff < 60_000) return "Vừa xong"

    val minutes = diff / 60_000
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val years = days / 365

    return when {
        minutes < 60 -> "${minutes} phút"
        hours < 24   -> "${hours} giờ"
        days < 7     -> "${days} ngày"
        weeks < 52   -> "${weeks} tuần"
        else         -> "${years} năm"
    }
}