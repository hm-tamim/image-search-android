package com.hmtamim.imagesearch.data.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class AppRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Inject
    lateinit var appRepository: AppRepository

    @Before
    fun init() {
        hiltRule.inject()
        runBlocking {
            appRepository.deleteAll()
        }
    }

    @After
    fun tearDown() {
        runBlocking {
            appRepository.deleteAll()
        }
    }

    @Test
    fun testInsertPhotoToRoomDatabase() {

        // data is inserted to a test database
        val expectedEntity = ImageEntity(
            100,
            "http://url.com",
            "http://url.com/web-image.png",
            "http://url.com/preview-image.png",
            "http://url.com/large-image.png",
            Calendar.getInstance().timeInMillis
        )

        runBlocking {
            appRepository.insertPhoto(expectedEntity)
        }

        val returnedEntity = appRepository.getAllPhotosLive("url").getOrAwaitValue()[0]

        assertEquals(returnedEntity, expectedEntity)
    }

    @Test
    fun testSearchHistory() {
        // same as insert
        val expectedEntity = ImageEntity(
            100,
            "nature",
            "http://url.com/web-image.png",
            "http://url.com/preview-image.png",
            "http://url.com/large-image.png",
            Calendar.getInstance().timeInMillis
        )

        runBlocking {
            appRepository.insertPhoto(expectedEntity)
        }

        val returnedEntity = appRepository.getAllPhotosLive("nature").getOrAwaitValue()[0]
        assertEquals(returnedEntity, expectedEntity)
    }

    @Test
    fun testDeletePhoto() {
        val expectedEntity = ImageEntity(
            100,
            "nature",
            "http://url.com/web-image.png",
            "http://url.com/preview-image.png",
            "http://url.com/large-image.png",
            Calendar.getInstance().timeInMillis
        )
        runBlocking {
            appRepository.insertPhoto(expectedEntity)
            appRepository.deletePhoto(expectedEntity)
        }
        val returnedList = appRepository.getAllPhotosLive("nature").getOrAwaitValue()

        assertEquals(returnedList.size, 0)
    }


    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}