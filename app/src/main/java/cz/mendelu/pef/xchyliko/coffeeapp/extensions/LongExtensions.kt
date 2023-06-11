package cz.mendelu.pef.xchyliko.coffeeapp.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertUnixTimestampToDateString(): String {
    val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val date = Date(this)
    return dateFormat.format(date)
}