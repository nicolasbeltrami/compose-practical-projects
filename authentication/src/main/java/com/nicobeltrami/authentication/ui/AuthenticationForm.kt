package com.nicobeltrami.authentication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicobeltrami.authentication.AuthenticationMode

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    password: String?,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(32.dp))
        AuthenticationTitle(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = authenticationMode
        )
        Spacer(modifier = modifier.height(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = email ?: "",
                    onEmailChanged = onEmailChanged
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    password = password ?: "",
                    onPassWordChange = onPasswordChanged
                )
            }
        }
    }
}

@Preview
@Composable
fun AuthenticationFormPreview() {
    MaterialTheme {
        Surface() {
            AuthenticationForm(
                authenticationMode = AuthenticationMode.SIGN_UP,
                email = "mail@mail.com",
                password = "123456",
                onEmailChanged = { },
                onPasswordChanged = {}
            )
        }
    }
}