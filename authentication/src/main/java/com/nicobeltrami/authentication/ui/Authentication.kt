package com.nicobeltrami.authentication.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicobeltrami.authentication.AuthenticationEvent
import com.nicobeltrami.authentication.AuthenticationState
import com.nicobeltrami.authentication.AuthenticationViewModel

@Composable
fun Authentication() {
    val viewModel : AuthenticationViewModel = viewModel()

    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        AuthenticationContent(
            modifier = Modifier.fillMaxWidth(),
            authenticationState = state,
            handleEvent = viewModel::handleEvent
        )
    }
}

