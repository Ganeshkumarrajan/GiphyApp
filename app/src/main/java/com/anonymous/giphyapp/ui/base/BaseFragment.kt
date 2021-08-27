package com.anonymous.giphyapp.ui.base

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {
    fun showSnackBar(message: String) {
        activity?.window?.decorView?.rootView?.let {
            val snackBar =
                Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            snackBar.show()
        }
    }
}
