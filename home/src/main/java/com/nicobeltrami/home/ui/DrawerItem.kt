package com.nicobeltrami.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Text(text = label.replaceFirstChar {
        it.titlecase(Locale.getDefault())
    },
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DrawerItemPreview() {
    DrawerItem(label = "upgrade") {

    }
}