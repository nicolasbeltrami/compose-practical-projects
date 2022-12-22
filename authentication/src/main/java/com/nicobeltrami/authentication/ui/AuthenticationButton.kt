package com.nicobeltrami.authentication.ui

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.authentication.AuthenticationMode
import com.nicobeltrami.authentication.R
import com.nicobeltrami.authentication.Tags

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit
) {
    Button(
        modifier = modifier.testTag(Tags.TAG_AUTHENTICATE_BUTTON),
        onClick = { onAuthenticate() },
        enabled = enableAuthentication
    ) {
        Text(
            text = stringResource(
                id = if (authenticationMode ==
                    AuthenticationMode.SIGN_IN
                ) {
                    R.string.action_sign_in
                } else {
                    R.string.action_sign_up
                }
            )
        )
    }
}

@Preview
@Composable
fun AuthenticationButtonPreview() {
    MaterialTheme {
        Surface {
            AuthenticationButton(
               authenticationMode = AuthenticationMode.SIGN_UP,
                onAuthenticate = {},
                enableAuthentication = true
            )
        }
    }
}