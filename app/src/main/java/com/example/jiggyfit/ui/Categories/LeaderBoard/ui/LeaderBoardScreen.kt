package com.example.jiggyfit.ui.Categories.LeaderBoard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jiggyfit.ui.home.ui.BottomNavigationBar
import com.example.jiggyfit.ui.home.ui.TopBar

@Composable
fun LeaderBoardScreen (navController: NavController) {
    Scaffold(
        topBar = {
            TopBar("Ranking de jugadores")
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { paddingValues ->
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 2.dp))
        LeaderBoardContent(paddingValues)
    }
}

@Composable
fun LeaderBoardContent(paddingValues: PaddingValues) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Ranking de jugadores")
    }

}
