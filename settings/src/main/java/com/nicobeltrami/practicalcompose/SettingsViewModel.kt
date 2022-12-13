package com.nicobeltrami.practicalcompose

import androidx.lifecycle.ViewModel
import com.nicobeltrami.practicalcompose.model.MarketingOption
import com.nicobeltrami.practicalcompose.model.SettingsState
import com.nicobeltrami.practicalcompose.model.Theme
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel : ViewModel() {
    val uiState = MutableStateFlow(SettingsState())

    fun toggleNotificationSettings() {
        uiState.value =
            uiState.value.copy(notificationsEnabled = !uiState.value.notificationsEnabled)
    }

    fun toggleHintSetting() {
        uiState.value = uiState.value.copy(hintsEnabled = !uiState.value.hintsEnabled)
    }

    fun setMarketingSettings(option: MarketingOption) {
        uiState.value = uiState.value.copy(marketingOption = option)
    }

    fun setTheme(theme: Theme) {
        uiState.value = uiState.value.copy(themeOption = theme)
    }

}
