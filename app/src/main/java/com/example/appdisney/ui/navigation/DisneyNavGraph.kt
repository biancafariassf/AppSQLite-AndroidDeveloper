package com.example.appdisney.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdisney.data.DisneyRepository
import com.example.appdisney.ui.DisneyScreen
import com.example.appdisney.ui.DisneyViewModel
import com.example.appdisney.ui.DisneyViewModelFactory

@Composable
fun DisneyNavGraph(navController: NavHostController, disneyRepository: DisneyRepository) {
    val viewModel: DisneyViewModel = viewModel(factory = DisneyViewModelFactory(disneyRepository))

    NavHost(navController, startDestination = "disneyScreen") {
        composable("disneyScreen") { DisneyScreen(viewModel) }
    }
}
