package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.statistics

import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.extensions.convertUnixTimestampToDateString
import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.MenuScreen
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StatisticsScreen(
    navigation: INavigationRouter,
    navController: NavController,
    viewModel: StatisticsViewModel = getViewModel()
) {
    var coffee = remember {
        mutableStateListOf<Coffee>()
    }

    viewModel.statisticsUIState.value.let {
        when (it) {
            StatisticsUIState.Default -> {
                viewModel.loadCoffee()
            }
            is StatisticsUIState.Success -> {
                coffee.clear()
                coffee.addAll(it.coffee)
            }
        }
    }

    val barChartData = mutableListOf<BarEntry>()

    countCoffee(coffee).forEach { (index, millilitres) ->
        barChartData.add(BarEntry(index, millilitres))
    }

    MenuScreen(
        appBarTitle = stringResource(id = R.string.statistics),
        route = navController.currentDestination?.route ?: "",
        navController = navController
    ) {
        StatisticsScreenContent(barChartData)
    }

}

@Composable
fun StatisticsScreenContent(
    barChartData: List<BarEntry>
) {
    val color = MaterialTheme.colorScheme.tertiaryContainer.toArgb()
    val description = stringResource(id = R.string.daily_coffee)

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.weekly_coffee),
                style = TextStyle.Default,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal
            )

            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .size(320.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AndroidView(factory = { context ->
                    BarChart(context).apply {
                        layoutParams = LinearLayout.LayoutParams(

                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                        )

                        this.description.isEnabled = false

                        this.legend.isEnabled = true
                        this.legend.textSize = 14F
                        this.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                    }
                },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp), update = {
                        updatePieChartWithData(it, barChartData, color, description)
                    }
                )
            }
        }
    }
}

fun updatePieChartWithData(
    chart: BarChart,
    data: List<BarEntry>,
    color: Int,
    description: String
) {
    val ds = BarDataSet(data, description)
    ds.color = color
    ds.valueTextColor = Color.BLACK
    ds.valueTextSize = 14f
    ds.valueTypeface = Typeface.DEFAULT_BOLD

    chart.data = BarData(ds)
    chart.invalidate()
}

fun countCoffee(
    coffeeList: List<Coffee>
) : Map<Float, Float> {
    val coffeePerDay = mutableMapOf<String, Float>()

    coffeeList.forEach { coffee ->
        val coffeeDrank = coffee.size / coffee.persons.toFloat()
        coffeePerDay[coffee.date.convertUnixTimestampToDateString()] = coffeePerDay.getOrDefault(coffee.date.convertUnixTimestampToDateString(), 0f) + coffeeDrank
    }

    val transformedMap = coffeePerDay.entries.mapIndexed { index, entry ->
        (index + 1f) to entry.value
    }.toMap()

    return transformedMap
}