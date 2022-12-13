package com.nicobeltrami.practicalcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicobeltrami.practicalcompose.model.SettingsState
import com.nicobeltrami.practicalcompose.model.Theme
import com.nicobeltrami.practicalcompose.model.MarketingOption

@Composable
fun Settings() {
    val viewModel: SettingsViewModel = viewModel()

    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        SettingsList(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            toggleNotificationSetting = viewModel::toggleNotificationSettings,
            toggleHintSetting = viewModel::toggleHintSetting,
            setMarketingOption = viewModel::setMarketingSettings,
            setSelectedTheme = viewModel::setTheme
        )
    }
}

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState,
    toggleNotificationSetting: () -> Unit,
    toggleHintSetting: () -> Unit,
    setMarketingOption: (option: MarketingOption) -> Unit,
    setSelectedTheme: (theme: Theme) -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(start = 12.dp)
        ) {
            Row {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_go_back)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.title_settings),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 18.sp
                )
            }
        }
        NotificationSettings(modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.settings_enable_notifications),
            checked = state.notificationsEnabled,
            onCheckedChanged = { toggleNotificationSetting() })
        Divider()
        HintSettingsItem(modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_show_hints),
            checked = state.hintsEnabled,
            onShowHintsToggled = { toggleHintSetting() })
        Divider()
        ManageSubscriptionSettingItem(modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_manage_subscriptions),
            onSettingClicked = {})
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = state.marketingOption,
            onOptionSelected = setMarketingOption
        )
        Divider()
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = state.themeOption,
            onOptionSelected = setSelectedTheme
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        AppVersionSettingItem(
            modifier = Modifier.fillMaxWidth(), stringResource(id = R.string.setting_app_version)
        )
        Divider()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingsPreview() {
    MaterialTheme {
        Settings()
    }
}