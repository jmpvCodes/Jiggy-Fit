package com.example.jiggyfit.ui.login.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jiggyfit.R
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import com.example.jiggyfit.navigation.Routes

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Login(Modifier.align(Alignment.Center), viewModel, navController)
        }

    }
@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {

        val email: String by viewModel.email.observeAsState(initial = "")
        val password: String by viewModel.password.observeAsState(initial = "")
        val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
        val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
        val coroutineScope = rememberCoroutineScope()



    if (isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            Column(modifier = modifier) {
                HeaderImage()
                Spacer(modifier = Modifier.padding(16.dp))
                EmailField(email) { viewModel.onLoginChanged(it, password) }
                Spacer(modifier = Modifier.padding(4.dp))
                PasswordField(password) { viewModel.onLoginChanged(email, it) }
                Spacer(modifier = Modifier.padding(8.dp))
                ForgotPassword(Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.padding(16.dp))
                LoginButton(loginEnable) {
                    coroutineScope.launch {
                        viewModel.onLoginSelected()
                    }
                }
                Separator()
                FacebookButton()
                GoogleButton()
                EmailButton(navController)
                
            }
        }
    }
@Composable
fun Separator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Divider(color = Color(0xFF636262), thickness = 2.dp)
        Text(text = " O ", color = Color(0xFF636262), modifier = Modifier.padding(8.dp), style = TextStyle(background = Color.White))
    }
}
@Composable
fun HeaderImage() {
        Image(
            painter = painterResource(id = R.drawable.jiggyfitlogo),
            contentDescription = "JiggyFit Logo",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
    }
@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = email, onValueChange = { onTextFieldChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF636262),
                backgroundColor = Color(0xFFDEDDDD),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = password, onValueChange = { onTextFieldChanged(it) },
            placeholder = { Text(text = "Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF636262),
                backgroundColor = Color(0xFFDEDDDD),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
@Composable
fun ForgotPassword(modifier: Modifier) {
        Text(
            text = "Olvidaste la contraseña?",
            modifier = modifier.clickable { },
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFB9600)
        )
    }
@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
        Button(
            onClick = { onLoginSelected() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF4303),
                disabledBackgroundColor = Color(0xFFF78058),
                contentColor = Color.White,
                disabledContentColor = Color.White
            ), enabled = loginEnable
        ) {
            Text(text = "Iniciar sesión")
        }
    }
@Composable
fun FacebookButton() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(0.dp, Color.Transparent), // Sin bordes
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent) // Fondo transparente
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // Icono alineado con el texto de forma central
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook Logo",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Continuar con Facebook",
                modifier = Modifier.padding(start = 8.dp),
                color = Color(0xFF48215C),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun GoogleButton() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(0.dp, Color.Transparent), // Sin bordes
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent) // Fondo transparente
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // Icono alineado con el texto de forma central
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Logo",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Continuar con Google",
                modifier = Modifier.padding(start = 8.dp),
                color = Color(0xFF48215C),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun EmailButton(navController: NavController) {
    OutlinedButton(
        onClick = { navController.navigate(Routes.Register) }, // Navega a la pantalla de registro
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(0.dp, Color.Transparent), // Sin bordes
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent) // Fondo transparente
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // Icono alineado con el texto de forma central
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "Email Logo",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Continuar con Email",
                modifier = Modifier.padding(start = 8.dp),
                color = Color(0xFF48215C),
                fontWeight = FontWeight.Bold
            )
        }
    }
}


