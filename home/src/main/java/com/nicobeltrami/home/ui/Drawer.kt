package com.nicobeltrami.home.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicobeltrami.home.model.Destination
import com.nicobeltrami.home.R

@Composable
fun ColumnScope.DrawerContent(
    modifier: Modifier = Modifier,
    onNavigationSelected: (destination: Destination) -> Unit,
    onLogout: () -> Unit
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.label_name),
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.label_email),
        fontSize = 16.sp
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = Destination.Upgrade.path)
    {
        onNavigationSelected(Destination.Upgrade)
    }
    Spacer(modifier = Modifier.height(8.dp))
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = Destination.Settings.path)
    {
        onNavigationSelected(Destination.Settings)
    }
    Spacer(modifier = Modifier
        .weight(1f))
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.log_out)
    ) {
        onLogout()
    }
    Spacer(modifier = Modifier.height(8.dp))
}
