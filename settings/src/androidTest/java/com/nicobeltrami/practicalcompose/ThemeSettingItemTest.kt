package com.nicobeltrami.practicalcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.nicobeltrami.practicalcompose.Tags.TAG_SELECT_THEME
import com.nicobeltrami.practicalcompose.Tags.TAG_THEME
import com.nicobeltrami.practicalcompose.Tags.TAG_THEME_OPTION
import com.nicobeltrami.practicalcompose.model.Theme
import org.junit.Rule
import org.junit.Test

class ThemeSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Selected_Theme_Displayed() {
        val option = Theme.DARK

        composeTestRule.setContent {
            ThemeSettingItem(selectedTheme = option, onOptionSelected = {})
        }
        composeTestRule.onNodeWithTag(TAG_THEME, useUnmergedTree = true).assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext.getString(option.label)
            )
    }

    @Test
    fun Theme_Options_Displayed() {
        composeTestRule.setContent {
            ThemeSettingItem(selectedTheme = Theme.DARK, onOptionSelected = {})
        }

        composeTestRule
            .onNodeWithTag(TAG_SELECT_THEME)
            .performClick()

        Theme.values().forEach { theme ->
            composeTestRule
                .onNodeWithTag(
                    TAG_THEME_OPTION + InstrumentationRegistry
                        .getInstrumentation().targetContext
                        .getString(theme.label)
                )
                .assertIsDisplayed()
        }
    }
}