package com.hmtamim.imagesearch.ui.photoViewer

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@MediumTest
@HiltAndroidTest
class PhotoViewerFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun isPhotoViewerFragmentVisible() {
        launchFragmentInHiltContainer<PhotoViewerFragment> {

        }
        Espresso.onView(ViewMatchers.withId(R.id.photoViewerContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testDownloadButton() {

        var fragment: PhotoViewerFragment? = null
        launchFragmentInHiltContainer<PhotoViewerFragment> {
            fragment = this
        }

        Espresso.onView(ViewMatchers.withId(R.id.download))
            .perform(ViewActions.click())

        fragment?.let { assertFalse(it.isInLayout) }

    }

    @Test
    fun testBackButtonPress() {
        var fragment: PhotoViewerFragment? = null
        launchFragmentInHiltContainer<PhotoViewerFragment> {
            fragment = this
        }

        Espresso.onView(ViewMatchers.withId(R.id.back))
            .perform(ViewActions.click())

        fragment?.let { assertFalse(it.isInLayout) }
    }

}