package com.nicobeltrami.inbox.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicobeltrami.inbox.InboxViewModel
import com.nicobeltrami.inbox.ui.EmailInbox

@Composable
fun Inbox() {
    val viewModel: InboxViewModel = viewModel()
    MaterialTheme {
        EmailInbox(
            modifier = Modifier.fillMaxWidth(),
            inboxState = viewModel.uiState
                .collectAsState().value,
            inboxEventListener = viewModel::handleEvent
        )
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.loadContent()
    }
}

@Preview(showBackground = true)
@Composable
fun InboxPreview() {
    Inbox()
}
