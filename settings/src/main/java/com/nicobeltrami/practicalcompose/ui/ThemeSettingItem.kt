package com.nicobeltrami.practicalcompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.nicobeltrami.practicalcompose.Tags.TAG_MARKETING_OPTION
import com.nicobeltrami.practicalcompose.Tags.TAG_SELECT_THEME
import com.nicobeltrami.practicalcompose.Tags.TAG_THEME
import com.nicobeltrami.practicalcompose.Tags.TAG_THEME_OPTION
import com.nicobeltrami.practicalcompose.model.Theme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ThemeSettingItem(
    modifier: Modifier = Modifier, selectedTheme: Theme, onOptionSelected: (option: Theme) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    SettingsItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable(
                    onClick = { expanded = !expanded },
                    onClickLabel = stringResource(id = R.string.setting_option_theme)
                )
                .padding(16.dp)
                .testTag(TAG_SELECT_THEME)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.setting_option_theme)
            )
            Text(
                modifier = Modifier.testTag(TAG_THEME),
                text = stringResource(id = selectedTheme.label)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(16.dp, 0.dp),
            properties = PopupProperties(
                usePlatformDefaultWidth = true
            )
        ) {
            Theme.values().forEach { theme ->
                val themeLabel = stringResource(id = theme.label)
                DropdownMenuItem(
                    modifier = Modifier.testTag(TAG_THEME_OPTION + themeLabel),
                    onClick = {
                        onOptionSelected(theme)
                        expanded = false
                    }
                ) {
                    Text(text = stringResource(id = theme.label))
                }
            }
        }
    }
}

@Preview
@Composable
fun ThemeSettingItemPreview() {
    ThemeSettingItem(selectedTheme = Theme.SYSTEM, onOptionSelected = {})
}