package com.example.appdisney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.appdisney.data.AppContainer
import com.example.appdisney.ui.navigation.DisneyNavGraph
import com.example.appdisney.ui.theme.AppDisneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDisneyTheme{
                val appContainer = AppContainer(applicationContext)
                val disneyRepository = appContainer.disneyRepository
                val navController = rememberNavController()
                DisneyNavGraph(navController = navController, disneyRepository = disneyRepository)
            }
        }
    }
}
