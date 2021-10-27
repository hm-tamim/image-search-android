package com.hmtamim.imagesearch.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.openKeyboard() {
    this.postDelayed({
        this.requestFocus()
        val imm =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, 0);
    }, 200)
}

fun EditText.hideKeyboard() {
    this.postDelayed({
        this.requestFocus()
        val imm =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }, 50)
}