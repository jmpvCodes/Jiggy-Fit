package com.example.jiggyfit.ui.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun RegisterScreen(viewModel: RegisterViewModel = viewModel(), navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Register(Modifier.align(Alignment.Center), viewModel, navController)
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel, navController: NavController) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmPassword: String by viewModel.confirmPassword.observeAsState(initial = "")
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            Text("Register", style = MaterialTheme.typography.h4, modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            NameField(name) { viewModel.onRegisterChanged(it, email, password, confirmPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            EmailField(email) { viewModel.onRegisterChanged(name, it, password, confirmPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onRegisterChanged(name, email, it, confirmPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            ConfirmPasswordField(confirmPassword) { viewModel.onRegisterChanged(name, email, password, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            RegisterButton(registerEnable) {
                coroutineScope.launch {
                    viewModel.onRegisterSelected()
                }
            }
        }
    }
}

@Composable
fun NameField(name: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = onTextChanged,
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ConfirmPasswordField(confirmPassword: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = confirmPassword,
        onValueChange = onTextChanged,
        label = { Text("Confirm Password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun RegisterButton(registerEnable: Boolean, onRegisterClicked: () -> Unit) {
    Button(
        onClick = onRegisterClicked,
        enabled = registerEnable,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Register")
    }
}
