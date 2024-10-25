package com.example.navigation

class HomeScreenState {

    companion object {
        private var name: String = ""
        private var wasShown: Boolean = false

        fun getWasShown(): Boolean {
            return wasShown
        }
        fun setWasShown(newWasShown: Boolean) {
            wasShown = newWasShown
        }

        fun getName(): String {
            return name
        }

        fun setName(newName: String) {
            name = newName
        }
    }
}