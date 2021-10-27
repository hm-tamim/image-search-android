package com.hmtamim.imagesearch.ui.gallery

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.launchFragmentInHiltContainer
import com.hmtamim.imagesearch.ui.home.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class GalleryFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun isHomePageFragmentVisible() {
        launchFragmentInHiltContainer<GalleryFragment> {

        }
        Espresso.onView(ViewMatchers.withId(R.id.galleryContainer))
            .check(ViewAssertions.matches(ViewMatchers.withText("Welcome to")))
    }
//
//    @Test
//    fun testPhotoSearched() {
//
//        launchFragmentInHiltContainer<HomeFragment>{
//
//        }
//
//        Espresso.onView(ViewMatchers.withId(R.id.etSearch))
//            .perform(ViewActions.typeText("dogs"))
//        Espresso.onView(ViewMatchers.withId(R.id.etSearch))
//            .perform(ViewActions.pressImeActionButton())
//    }

}