package com.anonymous.giphyapp.ui.base

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}
