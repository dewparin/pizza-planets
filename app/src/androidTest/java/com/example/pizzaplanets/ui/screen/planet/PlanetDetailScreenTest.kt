package com.example.pizzaplanets.ui.screen.planet

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.pizzaplanets.qc.TEST_TAG_PLANET_DETAIL_SCREEN_MENU_CHECKBOX
import com.example.pizzaplanets.qc.TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

private const val PLANET_ID = 1
private const val MENU1_ID = 1
private const val MENU2_ID = 2

class PlanetDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun selectMenuItems() {
        // Prepare mock
        val completionCallbackMock = mockk<(Int, List<Int>) -> Unit>()
        val capturedPlanetId = slot<Int>()
        val capturedSelectedMenuIds = slot<List<Int>>()
        every {
            completionCallbackMock.invoke(
                capture(capturedPlanetId),
                capture(capturedSelectedMenuIds),
            )
        } just Runs

        // Show Moon Detail (Yeah, it's not a planet)
        composeTestRule.setContent {
            PizzaPlanetsTheme {
                PlanetDetailScreen(
                    planetId = PLANET_ID,   // Set target to Moon (ID = 1)
                    navigateBack = {},
                    navigateToReviewOrder = completionCallbackMock,
                )
            }
        }

        // Verify the Review Order button disabled
        composeTestRule
            .onNodeWithTag(TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON)
            .assertIsNotEnabled()

        // Select Moon's Crater Crust Supreme Menu (ID = 1)
        composeTestRule
            .onNodeWithTag("${TEST_TAG_PLANET_DETAIL_SCREEN_MENU_CHECKBOX}$MENU1_ID")
            .performClick()

        // Verify the Review Order button is now enabled
        composeTestRule
            .onNodeWithTag(TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON)
            .assertIsEnabled()

        // Select Moon's The Dark Side Deluxe Menu (ID = 2)
        composeTestRule
            .onNodeWithTag("${TEST_TAG_PLANET_DETAIL_SCREEN_MENU_CHECKBOX}$MENU2_ID")
            .performClick()

        // Verify the Review Order button is still enabled
        composeTestRule
            .onNodeWithTag(TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON)
            .assertIsEnabled()

        // Click on the Review Order button
        composeTestRule
            .onNodeWithTag(TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON)
            .performClick()

        // Verify callback data
        verify {
            completionCallbackMock.invoke(any(), any())
        }
        assert(capturedPlanetId.captured == PLANET_ID)
        capturedSelectedMenuIds.captured.let {
            assertEquals(2, it.size)
            assertEquals(MENU1_ID, it.first())
            assertEquals(MENU2_ID, it.last())
        }
    }
}