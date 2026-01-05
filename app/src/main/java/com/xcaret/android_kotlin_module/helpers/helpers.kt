package com.xcaret.android_kotlin_module.helpers

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.applyStatusBarInset() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.setPadding(0, top, 0, 0)
            insets
        }
    }

fun View.applyStatusBarInsetTop() {
    val initialPaddingTop = paddingTop

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        v.setPadding(
            v.paddingLeft,
            initialPaddingTop + topInset,
            v.paddingRight,
            v.paddingBottom
        )
        insets
    }
}
