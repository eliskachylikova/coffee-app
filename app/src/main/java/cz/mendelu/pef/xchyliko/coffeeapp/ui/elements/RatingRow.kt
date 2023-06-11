package cz.mendelu.pef.xchyliko.coffeeapp.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingRow(
    coffeeName: String,
    stars: Double,
    icon: Int,
    iconColor: Color = LocalContentColor.current
) {
    val starsRounded = String.format("%.1f", stars)

    ListItem(
        headlineText = { Text(text = coffeeName) },
        leadingContent = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = iconColor
            )
        },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "$starsRounded/5 ")
                Icon(
                    Icons.Default.StarOutline,
                    contentDescription = ""
                )
            }
        }
    )
}