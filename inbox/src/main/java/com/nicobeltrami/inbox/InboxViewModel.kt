package com.nicobeltrami.inbox

import androidx.lifecycle.ViewModel
import com.nicobeltrami.inbox.model.InboxEvent
import com.nicobeltrami.inbox.model.InboxState
import com.nicobeltrami.inbox.model.InboxStatus
import kotlinx.coroutines.flow.MutableStateFlow

class InboxViewModel : ViewModel() {
    val uiState = MutableStateFlow(InboxState())

    fun handleEvent(inboxEvent: InboxEvent) {
        when(inboxEvent) {
            InboxEvent.OnRefreshContent -> loadContent()
            is InboxEvent.OnDeleteEmail -> deleteEmail(inboxEvent.id)
        }
    }

    fun loadContent() {
        uiState.value = uiState.value.copy(
            status = InboxStatus.LOADING
        )
        uiState.value = uiState.value.copy(
            status = InboxStatus.SUCCESS,
            content = EmailFactory.makeEmailList()
        )
    }

    private fun deleteEmail(id: String) {
        uiState.value = uiState.value.copy(
            content = uiState.value.content?.filter {
                it.id != id
            }
        )
    }
}
