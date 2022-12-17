package com.nicobeltrami.authentication.ui

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.authentication.R

@Composable
fun EmailInput(
    modifier: Modifier = Modifier, email: String?, onEmailChanged: (email: String) -> Unit
) {
    TextField(modifier = modifier, value = email ?: "", onValueChange = { email ->
        onEmailChanged(email)
    }, label = {
        Text(text = stringResource(id = R.string.label_email))
    },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
fun EmailInputPreview() {
    MaterialTheme {
        Surface {
            EmailInput(email = "mail@mail.com", onEmailChanged = {})
        }
    }
}