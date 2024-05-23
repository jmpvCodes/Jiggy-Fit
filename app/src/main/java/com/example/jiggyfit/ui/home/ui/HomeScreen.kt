package com.example.jiggyfit.ui.home.ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jiggyfit.R
import com.example.jiggyfit.navigation.NavigationItem
import com.example.jiggyfit.navigation.Routes


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar("Inicio")
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { paddingValues ->
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 2.dp))
        HomeContent(paddingValues, navController)
    }
}

@Composable
fun TopBar(s: String) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        contentPadding = PaddingValues(horizontal = 16.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Imagen del perfil
                Image(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                        .background(Color.White)

                )
                Spacer(modifier = Modifier.width(8.dp))
                // Texto de ubicación
                Column {
                    Text(s, style = MaterialTheme.typography.subtitle1, modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
            Row {
                IconButton(onClick = { /* Accion de búsqueda */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = { /* Accion de menú */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem(Routes.Home, R.drawable.home_icon, "Inicio"),
        NavigationItem(Routes.Profile, R.drawable.profile_icon, "Perfil"),
        NavigationItem(Routes.Settings, R.drawable.settings_icon, "Ajustes")
    )

    BottomNavigation(
        backgroundColor = Color(0xFF48215C),
        contentColor = Color(0xFFEA5C70)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color(0xFFE98190),
                unselectedContentColor = Color(0xFFFFFFFF),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}




@Composable
fun HomeContent(paddingValues: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CategoriesSection(navController)
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 1.dp))
        GameModesSection(navController)
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 1.dp))
        SectionRecommendations(navController)
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 1.dp))
    }
}

@Composable
fun CategoriesSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryCard("PERFIL", R.drawable.jiggyfitlogo, navController)
            CategoryCard("RETOS", R.drawable.jiggyfitlogo, navController)
            CategoryCard("LOGROS", R.drawable.jiggyfitlogo, navController)
            CategoryCard("RANKING", R.drawable.jiggyfitlogo, navController)
            CategoryCard("TIENDA", R.drawable.jiggyfitlogo, navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryCard(
    categoryName: String,
    @DrawableRes categoryImage: Int,
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp),
        onClick = {
            when (categoryName) {
                "PERFIL" -> {
                    navController.navigate(Routes.Profile)
                }
                "RETOS" -> {
                    navController.navigate(Routes.Challenges)
                }
                "LOGROS" -> {
                    navController.navigate(Routes.Achievements)
                }
                "RANKING" -> {
                    navController.navigate(Routes.Leaderboard)
                }
                "TIENDA" -> {
                    navController.navigate(Routes.Store)
                }
            }
        }
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
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }

}


@Composable
fun GameModesSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Modos de juego",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        GameModeCard("¡Resuelve en tiempo récord!", "Contrarreloj", R.drawable.boy, 0xFF48215C, 0xFFEA5C70, navController)
        GameModeCard("¡Descubre nuevas áreas!", "Exploración", R.drawable.boy, 0xFFEA5C70, 0xFF48215C, navController)
        GameModeCard("!Gana recompensas cada día¡", "Reto diario", R.drawable.boy, 0xFF48215C, 0xFFEA5C70, navController)
        GameModeCard("Forma equipos y resolved juntos", "Trabajo en equipo", R.drawable.boy, 0xFFEA5C70, 0xFF48215C, navController)
        }
}
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameModeCard(primaryText:String, secondaryText:String, @DrawableRes image: Int, backgroundColor: Long, buttonColor: Long, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(backgroundColor), // Color morado
        modifier = Modifier
            .padding(8.dp)
            //lo que ocupe
            .width(300.dp),
        onClick = {
            Log.i("GameModeCard", "primaryText: $secondaryText")
            when (secondaryText) {
                "Contrarreloj" -> {
                    navController.navigate(Routes.TimeTrialMode)
                    Log.i("GameModeCard", "Has pulsado: $secondaryText")
                }
                "Exploración" -> {
                    navController.navigate(Routes.ExplorationMode)
                    Log.i("GameModeCard", "Has pulsado: $secondaryText")
                }
                "Reto diario" -> {
                    navController.navigate(Routes.DailyMode)
                    Log.i("GameModeCard", "Has pulsado: $secondaryText")
                }
                "Trabajo en equipo" -> {
                    navController.navigate(Routes.TeamMode)
                    Log.i("GameModeCard", "Has pulsado: $secondaryText")
                }
            }
        }
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
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
                    onClick = {
                        Log.i("GameModeCard", "primaryText: $secondaryText")
                        when (secondaryText) {
                            "Contrarreloj" -> {
                                navController.navigate(Routes.TimeTrialMode)
                                Log.i("GameModeCard", "Has pulsado: $secondaryText")
                            }
                            "Exploración" -> {
                                navController.navigate(Routes.ExplorationMode)
                                Log.i("GameModeCard", "Has pulsado: $secondaryText")
                            }
                            "Reto diario" -> {
                                navController.navigate(Routes.DailyMode)
                                Log.i("GameModeCard", "Has pulsado: $secondaryText")
                            }
                            "Trabajo en equipo" -> {
                                navController.navigate(Routes.TeamMode)
                                Log.i("GameModeCard", "Has pulsado: $secondaryText")
                            }
                        }
                    },
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
fun RecommendationCard(
    title: String,
    description: String,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(text = "Ver más")
                }
            }
        }
    }
}
@Composable
fun SectionRecommendations(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Recomendaciones",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RecommendationCard(
                title = "Consejo de entrenamiento",
                description = "Aprende cómo mejorar tu rendimiento con estos simples ejercicios.",
                imageRes = R.drawable.jiggyfitlogo,
                onClick = { /* Acción al hacer clic */ }
            )
            RecommendationCard(
                title = "Nutrición deportiva",
                description = "Descubre las mejores prácticas de nutrición para mejorar tu rendimiento.",
                imageRes = R.drawable.jiggyfitlogo,
                onClick = { /* Acción al hacer clic */ }
            )
            RecommendationCard(
                title = "Ejercicios de calentamiento",
                description = "Realiza estos ejercicios de calentamiento antes de empezar tu rutina.",
                imageRes = R.drawable.jiggyfitlogo,
                onClick = { /* Acción al hacer clic */ }
            )
        }
    }
}

