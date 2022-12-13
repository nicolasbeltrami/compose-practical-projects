package com.nicobeltrami.practicalcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.nicobeltrami.practicalcompose.Tags.TAG_CHECK_ITEM
import org.junit.Rule
import org.junit.Test

class HintsSettingItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = "Show Hints"
        composeTestRule.setContent {
            HintSettingsItem(title = title, checked = true, onShowHintsToggled = {})
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun Setting_Checked() {
        composeTestRule.setContent {
            HintSettingsItem(title = "Show Hint", checked = true, onShowHintsToggled = {})
        }

        composeTestRule
            .onNodeWithTag(TAG_CHECK_ITEM)
            .assertIsOn()
    }

}
