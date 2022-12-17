package com.nicobeltrami.authentication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nicobeltrami.authentication.AuthenticationEvent
import com.nicobeltrami.authentication.AuthenticationState

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                modifier = Modifier.fillMaxSize(),
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                password = authenticationState.password,
                onEmailChanged = { email ->
                    handleEvent(AuthenticationEvent.EmailChanged(email))
                },
                onPasswordChanged = { password ->
                    handleEvent(AuthenticationEvent.PasswordChanged(password))
                }
            )
        }
    }
}