package com.hmtamim.imagesearch.utils


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

    }
}