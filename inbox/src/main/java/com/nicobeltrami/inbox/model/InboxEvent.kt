package com.nicobeltrami.inbox.model

sealed interface InboxEvent {
    object OnRefreshContent: InboxEvent
    data class OnDeleteEmail(val id: String) : InboxEvent
}
