package com.nicobeltrami.authentication.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nicobeltrami.authentication.AuthenticationMode
import com.nicobeltrami.authentication.R

@Composable
fun AuthenticationTitle(
    modifier: Modifier = Modifier, authenticationMode: AuthenticationMode
) {
    Text(
        text = stringResource(
            id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                R.string.label_sign_in_to_account
            } else {
                R.string.label_sign_up_for_account
            }
        ),
        fontSize = 24.sp,
        fontWeight = FontWeight.Black
    )
}

@Preview
@Composable
fun AuthenticationTitlePreview() {
    MaterialTheme {
        Surface() {
            AuthenticationTitle(authenticationMode = AuthenticationMode.SIGN_IN)
        }
    }
}