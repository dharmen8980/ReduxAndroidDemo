package com.teknophase.reduxexample.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.teknophase.redux.map
import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.navigation.AppNavRoutes
import com.teknophase.reduxexample.state.LoginActions
import com.teknophase.reduxexample.ui.theme.ReduxExampleTheme
import com.teknophase.reduxexample.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val username = viewModel.loginStore.stateFlow.map(coroutineScope) {
        it.username
    }.collectAsState()
    val password = viewModel.loginStore.stateFlow.map(coroutineScope) {
        it.password
    }.collectAsState()
    val isLoading = viewModel.loginStore.stateFlow.map(coroutineScope) {
        it.isLoading
    }.collectAsState()
    val isLoggedIn = viewModel.loginStore.stateFlow.map(coroutineScope) {
        it.isLoggedIn
    }.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = username.value,
            placeholder = {
                Text(text = "Username")
            },
            onValueChange = { updatedUsername ->
                viewModel.loginStore.dispatch(LoginActions.UpdateUsername(updatedUsername))
            },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .align(CenterHorizontally)
        )

        TextField(
            value = password.value,
            placeholder = {
                Text(text = "Password")
            },
            onValueChange = { updatedPassword ->
                viewModel.loginStore.dispatch(LoginActions.UpdatePassword(updatedPassword))
            },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .align(CenterHorizontally),
            visualTransformation = PasswordVisualTransformation()
        )

        if (!isLoading.value && !isLoggedIn.value) {
            Button(
                onClick = {
                    viewModel.loginStore.dispatch(
                        LoginActions.Login(
                            AuthRequestModel(
                                username = username.value,
                                password = password.value
                            )
                        )
                    )
                },
                modifier = Modifier.align(CenterHorizontally)
            ) {
                Text(text = "Login")
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
        }

        if (isLoggedIn.value) {
            navController.navigate(AppNavRoutes.HOME.name)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReduxExampleTheme {
        LoginScreen(navController = rememberNavController())
    }
}