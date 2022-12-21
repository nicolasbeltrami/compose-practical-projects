package com.nicobeltrami.authentication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.authentication.AuthenticationEvent
import com.nicobeltrami.authentication.AuthenticationState

@Composable
fun AuthenticationContent(
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                password = authenticationState.password,
                completedPasswordRequirements = authenticationState.passwordRequirements,
                enableAuthentication = authenticationState.isFormValid(),
                onEmailChanged = { handleEvent(AuthenticationEvent.EmailChanged(it)) },
                onPasswordChanged = { handleEvent(AuthenticationEvent.PasswordChanged(it)) },
                onAuthenticate = { handleEvent(AuthenticationEvent.Authenticate) },
                onToggleMode = { handleEvent(AuthenticationEvent.ToggleAuthenticationMode) }
            )
            authenticationState.error?.let {  error ->
                AuthenticationErrorDialog(
                    error = error,
                    dismissError = {
                        handleEvent(AuthenticationEvent.ErrorDismissed)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthenticationContentPreview() {
    MaterialTheme {
        AuthenticationContent(
            authenticationState = AuthenticationState(),
            handleEvent = {}
        )
    }
}
