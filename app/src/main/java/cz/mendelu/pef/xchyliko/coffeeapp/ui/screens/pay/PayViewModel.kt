package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.pay

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.database.ICoffeeRepository
import kotlinx.coroutines.launch

class PayViewModel(
    private val repository: ICoffeeRepository
): BaseViewModel(), PayActions {

    var data: PayData = PayData()

    var payUIState: MutableState<PayUIState> =
        mutableStateOf(PayUIState.Loading)

    override fun markAsPaid() {
        launch {
            repository.markCoffeeAsPaid()
            payUIState.value = PayUIState.Paid
        }
    }

    override fun onDebtChange(debt: Float) {
        data.debt = debt
        payUIState.value = PayUIState.Changed
    }


    fun init() {
        launch {
            data.debt = repository.countDebt() ?: 0f
            data.loading = false
            payUIState.value = PayUIState.Changed
        }
    }

}