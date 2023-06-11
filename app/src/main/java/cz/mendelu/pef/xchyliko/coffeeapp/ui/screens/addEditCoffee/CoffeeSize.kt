package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee

enum class CoffeeSize(val size: Float, val price: Float) {
    SMALL(40f, 12f),
    MEDIUM(80f, 12f),
    LARGE(230f, 20f),
    EXTRA(535f, 25f)
}

fun getCoffeeSizeBySize(size: Float): CoffeeSize? {
    return CoffeeSize.values().find { it.size == size }
}