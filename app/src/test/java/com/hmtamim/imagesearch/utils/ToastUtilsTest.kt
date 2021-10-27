package com.hmtamim.imagesearch.utils

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
class ToastUtilsTest {

    private var context: Application? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testShowToast() {
        ToastUtils.show("tamim", context)
        assertEquals(ShadowToast.showedToast("tamim"), true)
    }

}