package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.pay

interface PayActions {
    fun markAsPaid()
    fun onDebtChange(debt: Float)
}