package cz.mendelu.pef.xchyliko.coffeeapp.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchyliko.coffeeapp.R

@Composable
fun Counter(
    count: Int,
    onCountChangeMinus: () -> Unit,
    onCountChangePlus: () -> Unit
) {

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = stringResource(R.string.number_of_people))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(
                onClick = { onCountChangeMinus() },
                enabled = count > 1
            ) {
                Icon(Icons.Filled.Remove, contentDescription = "")
            }

            Text(text = count.toString())

            IconButton(
                onClick = { onCountChangePlus() },
                enabled = count < 10
            ) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
    }
}