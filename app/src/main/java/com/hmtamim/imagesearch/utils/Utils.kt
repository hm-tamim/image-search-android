package com.hmtsoft.webcapture.utils

import android.util.Patterns
import android.webkit.URLUtil
import java.net.MalformedURLException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt


class Utils {

    companion object {

        fun urlToFileName(str: String): String {
            var newString = str
            newString = newString.replace("[^A-Za-z0-9_\\-]".toRegex(), "_");
            return newString
        }

        fun urlToTitle(str: String): String {
            var newString = str
            newString = newString.replace("https://", "")
            newString = newString.replace("http://", "")
            return newString
        }

        fun isValidUrl(urlString: String): Boolean {
            try {
                val urlNew = urlString
                val url = URL(urlNew)
                return URLUtil.isValidUrl(urlNew) && Patterns.WEB_URL.matcher(urlNew)
                    .matches()
            } catch (ignored: MalformedURLException) {
            }

            return false
        }

        fun getTimeAgo(time: Long): String? {
            val curDate = Calendar.getInstance()
            val now = curDate.timeInMillis
            if (time > now || time <= 0) {
                return "a moment ago"
            }
            val timeDIM = getTimeDistanceInMinutes(time)
            var timeAgo: String? = null
            when {
                timeDIM == 0 -> {
                    timeAgo = "Just now"
                }
                timeDIM == 1 -> {
                    return "1 minute ago"
                }
                timeDIM in 2..44 -> {
                    timeAgo = "$timeDIM minutes ago"
                }
                timeDIM in 45..89 -> {
                    timeAgo = "1 hour ago"
                }
                timeDIM in 90..1439 -> {
                    timeAgo = ((timeDIM / 60).toFloat().roundToInt()).toString() + " hours ago"
                }
                timeDIM >= 1440 -> {
                    val tDate = Date(time)
                    val jdf = SimpleDateFormat("MMM dd 'at' h:mm a", Locale.getDefault())
                    timeAgo = jdf.format(tDate)
                }
            }
            return timeAgo
        }

        private fun getTimeDistanceInMinutes(time: Long): Int {
            val timeDistance = Calendar.getInstance().timeInMillis - time
            return ((abs(timeDistance) / 1000) / 60).toFloat().roundToInt()
        }

    }
}