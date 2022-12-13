package com.nicobeltrami.practicalcompose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicobeltrami.practicalcompose.Tags.TAG_TOGGLE_ITEM

@Composable
fun NotificationSettings(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChanged: (checked: Boolean) -> Unit
) {
    SettingsItem(
        modifier = modifier
    ) {
        val notificationsEnabledState = if (checked) {
            stringResource(id = R.string.cd_notifications_enabled)
        } else {
            stringResource(id = R.string.cd_notifications_disabled)
        }
        
        Row(
            modifier = Modifier
                .testTag(TAG_TOGGLE_ITEM)
                .toggleable(
                    value = checked,
                    onValueChange = onCheckedChanged,
                    role = Role.Switch
                )
                .semantics { stateDescription = notificationsEnabledState }
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title, modifier = Modifier.weight(1f)
            )
            Switch(
                checked = checked, 
                onCheckedChange = null
            )
        }
    }
}

@Preview
@Composable
fun NotificationSettingsPreview() {
    Surface {
        NotificationSettings(title = "Enable notifications", checked = true, onCheckedChanged = {})
    }
}