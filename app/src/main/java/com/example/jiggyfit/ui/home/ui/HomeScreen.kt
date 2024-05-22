package com.example.jiggyfit.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jiggyfit.R


@Composable
fun HomeScreen(navController: NavController, userName: String = "User") {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("JiggyFit") },
                actions = {
                    IconButton(onClick = { /* Add logout logic here */ }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout")
                    }
                    IconButton(onClick = { /* Navigate to settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { /* Navigate to help/FAQ */ }) {
                        Icon(Icons.AutoMirrored.Filled.Help, contentDescription = "Help/FAQ")
                    }
                },
                backgroundColor = Color.Transparent,
            )
        }
    ) { paddingValues ->
        HomeContent(navController, userName, paddingValues)
    }
}

@Composable
fun HomeContent(navController: NavController, userName: String, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Categories section
        CategoriesSection()

        Spacer(modifier = Modifier.height(4.dp))

        // Upcoming Events section
        UpcomingEventsSection()

        Spacer(modifier = Modifier.height(4.dp))

        // Top Sports Clubs section
        TopSportsClubsSection()

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun CategoriesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Categorías",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryCard("CLUBS", R.drawable.jiggyfitlogo)
            CategoryCard("EVENTOS", R.drawable.jiggyfitlogo)
            CategoryCard("CLASES", R.drawable.jiggyfitlogo)
            CategoryCard("TIENDA", R.drawable.jiggyfitlogo)
        }
    }
}

@Composable
fun CategoryCard(categoryName: String, @DrawableRes categoryImage: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = categoryImage),
                contentDescription = categoryName,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = categoryName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
fun UpcomingEventsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Modos de juego",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        GameModeCard("¡Resuelve en tiempo récord!", "Contrarreloj", R.drawable.boy, 0xFF48215C, 0xFFEA5C70)
        GameModeCard("¡Resuelve en tiempo récord!", "Contrarreloj", R.drawable.boy, 0xFFEA5C70, 0xFF48215C)
        GameModeCard("¡Resuelve en tiempo récord!", "Contrarreloj", R.drawable.boy, 0xFF48215C, 0xFFEA5C70)
        GameModeCard("¡Resuelve en tiempo récord!", "Contrarreloj", R.drawable.boy, 0xFFEA5C70, 0xFF48215C)
        }
}
}


@Composable
fun GameModeCard(primaryText:String, secondaryText:String, @DrawableRes image: Int, backgroundColor: Long, buttonColor: Long) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(backgroundColor), // Color morado
        modifier = Modifier
            .padding(8.dp)
            //lo que ocupe
            .width(300.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = primaryText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = secondaryText,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00FF00) // Color verde brillante
                )
                Button(
                    onClick = { /* Navigate to event */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(buttonColor)),
                ) {
                    Text(text = "Entrar", color = Color.White)
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.boy),
                    contentDescription = "Niño",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}



@Composable
fun TopSportsClubsSection() {
    // Implement your top sports clubs UI here
}
