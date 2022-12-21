package com.nicobeltrami.authentication.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicobeltrami.authentication.AuthenticationViewModel

@Composable
fun Authentication() {
    val viewModel: AuthenticationViewModel = viewModel()

    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        AuthenticationContent(
            authenticationState = state,
            handleEvent = viewModel::handleEvent
        )
    }
}

@Preview
@Composable
fun AuthenticationPreview() {
    MaterialTheme {
        Surface {
            Authentication()
        }
    }
}

