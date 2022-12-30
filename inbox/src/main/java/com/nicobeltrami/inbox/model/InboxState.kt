package com.nicobeltrami.inbox.model

data class InboxState(
    val status: InboxStatus = InboxStatus.LOADING,
    val content: List<Email>? = null
)
