package com.example.navigation.Screens

class HomeScreenState {
    companion object {
        private var name: String = "Zuzka"

        fun getName(): String {
            return name
        }

        fun setName(newName: String) {
            name = newName
        }
    }
}