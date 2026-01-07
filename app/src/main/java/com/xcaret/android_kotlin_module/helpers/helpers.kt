package com.xcaret.android_kotlin_module.helpers

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
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

fun Context.getThemeColor(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data
}

fun Context.getActionBarHeight(): Int {
    val typedValue = TypedValue()
    return if (theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
        TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
    } else {
        0
    }
}

fun View.updateLayoutParams(block: ViewGroup.LayoutParams.() -> Unit) {
    val params = layoutParams
    block(params)
    layoutParams = params
}