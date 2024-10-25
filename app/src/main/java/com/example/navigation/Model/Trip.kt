package com.example.navigation.Model

class Trip(
    val name: String,
    val startDate: String,
    val endDate: String,
    var bags: MutableList<Bag>
) {
}