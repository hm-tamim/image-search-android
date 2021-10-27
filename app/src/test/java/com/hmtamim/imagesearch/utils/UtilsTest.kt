package com.hmtamim.imagesearch.utils

import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsTest {

    @Test
    fun testUrlToFileName() {
        val result = Utils.urlToFileName("https://google.com")
        assertFalse(result.contains(":"))
        assertFalse(result.contains("/"))
        assertFalse(result.contains("."))
    }

    @Test
    fun testUrlToTitle() {
        val result = Utils.urlToTitle("https://google.com")
        assertFalse(result.contains("http"))
    }

}