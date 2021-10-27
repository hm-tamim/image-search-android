package com.hmtamim.imagesearch.ui.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun isHomePageFragmentVisible() {
        launchFragmentInHiltContainer<HomeFragment> {
        }
        onView(withId(R.id.welcome_text)).check(matches(withText("Welcome to")))
    }

    @Test
    fun testPhotoSearchedWithTerm_ShouldNavigateToGalleryFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<HomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.etSearch))
            .perform(typeText("dogs"), pressImeActionButton())
        verify(navController).navigate(HomeFragmentDirections.actionHomeFragmentToGalleryFragment("dogs"))
    }

    @Test
    fun testPhotoSearchedWithEmptyString_ShouldNotNavigateToGalleryFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<HomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.etSearch))
            .perform(typeText(""), pressImeActionButton())
        verify(
            navController,
            never()
        ).navigate(HomeFragmentDirections.actionHomeFragmentToGalleryFragment("dogs"))
    }

}