package cz.mendelu.pef.xchyliko.coffeeapp.extensions

import java.text.DecimalFormat

fun Double.roundToDecimal(): Double {
    val decimalFormat = DecimalFormat("#.#")
    return decimalFormat.format(this).toDouble()
}