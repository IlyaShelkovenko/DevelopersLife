/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */
package com.gmail.hostov47.developerslife.extensions

import android.content.Context
import android.util.TypedValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


fun Fragment.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    )
}

fun Fragment.dpToIntPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    ).toInt()
}