package com.nicobeltrami.authentication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicobeltrami.authentication.AuthenticationMode
import com.nicobeltrami.authentication.R
import com.nicobeltrami.authentication.Tags

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthentication: () -> Unit
) {
    Surface(
        modifier = modifier, elevation = 8.dp
    ) {
        TextButton(
            modifier = modifier
                .testTag(Tags.TAG_AUTHENTICATION_TOGGLE)
                .background(MaterialTheme.colors.surface),
            onClick = {
                toggleAuthentication()
            }) {
            Text(
                text = stringResource(
                    id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                        R.string.action_need_account
                    } else {
                        R.string.action_already_have_account
                    }
                )
            )
        }
    }
}

@Preview
@Composable
fun ToggleAuthenticationModePreview() {
    MaterialTheme {
        ToggleAuthenticationMode(
            authenticationMode = AuthenticationMode.SIGN_IN,
            toggleAuthentication = {})
    }
}