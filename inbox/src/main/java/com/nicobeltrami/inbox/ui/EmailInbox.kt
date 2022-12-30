package com.nicobeltrami.inbox.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicobeltrami.inbox.R
import com.nicobeltrami.inbox.model.InboxEvent
import com.nicobeltrami.inbox.model.InboxState
import com.nicobeltrami.inbox.model.InboxStatus

@Composable
fun EmailInbox(
    modifier: Modifier = Modifier,
    inboxState: InboxState,
    inboxEventListener: (inboxEvent: InboxEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp,
            ) {
                Text(
                    text = stringResource(
                        id = R.string.title_inbox,
                        inboxState.content?.count() ?: 0
                    ),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center,
        ) {
            if (inboxState.status == InboxStatus.LOADING) {
                Loading()
            } else if (inboxState.status == InboxStatus.ERROR) {
                ErrorState (
                    inboxEventListener = { inboxEventListener(InboxEvent.OnRefreshContent) }
                )
            } else {
                EmptyState(inboxEventListener = {
                    InboxEvent.OnRefreshContent
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailInboxPreview() {
    EmailInbox(inboxState = InboxState(InboxStatus.LOADING), inboxEventListener = {})
}