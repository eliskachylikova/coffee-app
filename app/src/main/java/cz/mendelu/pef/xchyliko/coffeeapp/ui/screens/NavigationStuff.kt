package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.ui.graphics.vector.ImageVector
import cz.mendelu.pef.xchyliko.coffeeapp.R

enum class NavigationStuff(val imageVector: ImageVector, val route: String, val titleId: Int) {

    HOME(Icons.Default.Home, "main", R.string.home),
    PAY(Icons.Default.QrCode2, "pay", R.string.pay),
    RATING(Icons.Default.StarOutline, "rating", R.string.rating),
    STATISTICS(Icons.Default.BarChart, "statistics", R.string.statistics)

}
fun getNavigationStuffByRoute(route: String): NavigationStuff? {
    return NavigationStuff.values().find { it.route == route }
}