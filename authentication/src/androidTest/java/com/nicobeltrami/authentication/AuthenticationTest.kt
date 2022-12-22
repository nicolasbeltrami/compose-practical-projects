package com.nicobeltrami.authentication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.nicobeltrami.authentication.ui.Authentication
import com.nicobeltrami.authentication.ui.AuthenticationContent
import org.junit.Rule
import org.junit.Test

class AuthenticationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Sign_In_Title_Displayed_By_Default() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.label_sign_in_to_account)
        ).assertIsDisplayed()
    }

    @Test
    fun Need_Account_Displayed_By_Default() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.action_need_account
                )
        ).assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Title_Displayed_After_Toggled() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_need_account)
        ).performClick()

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.label_sign_up_for_account)
        ).assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Button_Displayed_After_Toggle() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithTag(
            Tags.TAG_AUTHENTICATION_TOGGLE
        ).performClick()

        composeTestRule.onNodeWithTag(
            Tags.TAG_AUTHENTICATE_BUTTON
        ).assertTextEquals(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_sign_up)
        )
    }

    @Test
    fun Already_Have_Account_Displayed_After_Toggle() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithTag(
            Tags.TAG_AUTHENTICATION_TOGGLE
        ).apply {
            performClick()
            assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.action_already_have_account
                    )
            )
        }
    }

    @Test
    fun Authentication_Button_Disabled_By_Default() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule
            .onNodeWithTag(Tags.TAG_AUTHENTICATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun Authentication_Button_Enabled_With_Valid_Content() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithTag(
            Tags.TAG_INPUT_EMAIL
        ).performTextInput("contacto@mail.com")

        composeTestRule.onNodeWithTag(
            Tags.TAG_INPUT_PASSWORD
        ).performTextInput("12345678")

        composeTestRule.onNodeWithTag(
            Tags.TAG_AUTHENTICATE_BUTTON
        ).assertIsEnabled()
    }

    @Test
    fun Error_Alert_Not_Displayed_By_Default() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithTag(Tags.TAG_ERROR_ALERT).assertDoesNotExist()
    }

    @Test
    fun Error_Alert_Displayed_After_Error() {
        composeTestRule.setContent {
            AuthenticationContent(
                AuthenticationState(error = "Some Error")
            ) { }
        }

        composeTestRule.onNodeWithTag(Tags.TAG_ERROR_ALERT).assertIsDisplayed()
    }

    @Test
    fun Progress_Not_Displayed_By_Default() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithTag(
            Tags.TAG_PROGRESS
        ).assertDoesNotExist()
    }

    @Test
    fun Progress_Displayed_While_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(isLoading = true)
                , handleEvent = {})
        }

        composeTestRule.onNodeWithTag(
            Tags.TAG_PROGRESS
        ).assertIsDisplayed()
    }

    @Test
    fun Progress_Not_Displayed_After_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(
                    email = "contacto@mail.com",
                    password = "12345678"
                ),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(
            Tags.TAG_AUTHENTICATE_BUTTON
        ).performClick()

        composeTestRule.onNodeWithTag(
            Tags.TAG_PROGRESS
        ).assertDoesNotExist()
    }

}