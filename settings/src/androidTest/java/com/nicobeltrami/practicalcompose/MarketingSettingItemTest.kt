package com.nicobeltrami.practicalcompose

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.nicobeltrami.practicalcompose.Tags.TAG_MARKETING_OPTION
import com.nicobeltrami.practicalcompose.model.MarketingOption
import org.junit.Rule
import org.junit.Test

class MarketingSettingItemTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @Test
    fun Merketing_Option_Selected() {
        val option = MarketingOption.NOT_ALLOWED

        composableTestRule.setContent {
            MarketingSettingItem(selectedOption = option, onOptionSelected = {})
        }

        composableTestRule
            .onNodeWithTag(TAG_MARKETING_OPTION + option.id)
            .assertIsSelected()
    }
}
