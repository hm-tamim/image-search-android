package com.hmtamim.imagesearch.ui.gallery

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@MediumTest
@HiltAndroidTest
class GalleryFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun isHomePageFragmentVisible() {
        launchFragmentInHiltContainer<GalleryFragment> {

        }
        onView(withId(R.id.galleryContainer))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSearchClearButton() {
        launchFragmentInHiltContainer<GalleryFragment> {

        }
        onView(withId(R.id.etSearch))
            .perform(typeText("cats"))

        onView(withId(R.id.btnClearSearch))
            .perform(click())

        onView(withId(R.id.etSearch))
            .check(matches(withText("")))
    }

    @Test
    fun testGridChangeButton() {
        var fragmentViewModel: GalleryViewModel? = null
        launchFragmentInHiltContainer<GalleryFragment> {
            fragmentViewModel = viewModel
        }

        var oldGridCount = fragmentViewModel?.getGridSizeLiveData()?.value

        onView(withId(R.id.btnGrid))
            .perform(click())

        var newGridCount = fragmentViewModel?.getGridSizeLiveData()?.value

        assertNotEquals("grid column count has changed", oldGridCount, newGridCount)
    }

    @Test
    fun testBackButtonPress() {
        var galleryFragment: GalleryFragment? = null
        launchFragmentInHiltContainer<GalleryFragment> {
            galleryFragment = this
        }

        onView(withId(R.id.btnBack))
            .perform(click())

        galleryFragment?.let { assertFalse(it.isInLayout) }
    }

}