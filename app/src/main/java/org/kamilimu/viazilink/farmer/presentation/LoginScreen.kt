package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.AuthStatus
import org.kamilimu.viazilink.util.ScreenNames
import org.kamilimu.viazilink.util.components.LoadingIndicator

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController,
    onSuccessfulLogin: () -> Unit
) {
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()

    LoginScreenContent(
        modifier = Modifier.fillMaxSize(),
        authStatus = authStatus,
        onLoginClicked = authViewModel::onLogin,
        onSignUpPromptClicked = {
            navController.navigate(ScreenNames.SignUpScreen.route) {
                launchSingleTop = true
            }
        },
        onSuccessfulLogin = onSuccessfulLogin
    )
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    authStatus: AuthStatus,
    onLoginClicked: (String, String) -> Unit,
    onSignUpPromptClicked: () -> Unit,
    onSuccessfulLogin: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(authStatus) {
        when (authStatus) {
            is AuthStatus.Authenticated -> onSuccessfulLogin()
            is AuthStatus.Error -> Toast.makeText(
                context,
                authStatus.message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(text = stringResource(R.string.email))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = stringResource(R.string.password))
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(R.string.password_visibility_icon)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Button(
                onClick = { onLoginClicked(email, password) },
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text(stringResource(R.string.login))
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_small)))
            TextButton(
                onClick = onSignUpPromptClicked
            ) {
                Text(stringResource(R.string.signup_prompt))
            }
        }
        if (authStatus == AuthStatus.Loading) LoadingIndicator()
    }
}

@Preview(name = "LoginScreenLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "LoginScreenDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreenContent(
            modifier = Modifier.fillMaxSize(),
            AuthStatus.Unauthenticated,
            { _, _ -> },
            {},
            {}
        )
    }
}