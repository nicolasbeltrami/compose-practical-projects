package com.nicobeltrami.authentication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nicobeltrami.authentication.PasswordRequirements

@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    satisfiedRequirements: List<PasswordRequirements>
) {
    Column(modifier = modifier) {
        PasswordRequirements.values().forEach { passwordRequirement ->
            Requirement(
                message = stringResource(
                    id = passwordRequirement.label),
                satisfied = satisfiedRequirements.contains(
                    passwordRequirement
                )
            )
        }
    }
}