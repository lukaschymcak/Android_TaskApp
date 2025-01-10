package com.example.navigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.navigation.onboardingView.OnboardingScreen
import com.example.navigation.states.PreferencesHelper
import com.example.navigation.ui.theme.NavigationTheme
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()


        val dataStoreManager = DataStoreManager(applicationContext)
        applySavedLanguage(dataStoreManager)

        setContent {
            NavigationTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val startDestination = if (PreferencesHelper.isWelcomeScreenShown(this)) {
                        Screen.HomeScreen.route
                    } else {
                        Screen.WelcomeScreen.route
                    }
                    Navigation(
                        modifier = Modifier.padding(innerPadding),
                        startDestination = startDestination,
                        dataStoreManager = dataStoreManager
                    )
                }
            }
        }
    }
    private fun applySavedLanguage(dataStoreManager: DataStoreManager) {
        runBlocking {
            val savedLanguage = dataStoreManager.getLanguage().firstOrNull() ?: "en"
            updateLanguage(this@MainActivity, savedLanguage)
        }


    }
//    @Composable
//    private fun ShowOnboardingScreen(){
//        val context = LocalContext.current
//
//        Box(
//            modifier = Modifier.background(Color.Red)){
//            OnboardingScreen {
//                Toast.makeText(context, "Onboarding finished", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
}
