package kr.young.common

import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TouchEffect {
    companion object {
        private const val TAG = "TouchEffect"

        @JvmStatic
        fun iv(view: View, event: MotionEvent?) {
            if (view is ImageView) {
                val drawable = view.drawable
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> drawable.alpha = 50
                    else -> drawable.alpha = 255
                }
            }
        }

        @JvmStatic
        fun tv(view: View, event: MotionEvent?) {
            tv(view, event, Color.parseColor("#DFCFFF"), Color.parseColor("#cccccc"))
        }

        @JvmStatic
        fun tv(view: View, event: MotionEvent?, touchColor: Int, defaultColor: Int) {
            if (view is TextView) {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> view.setBackgroundColor(touchColor)
                    else -> view.setBackgroundColor(defaultColor)
                }
            }
        }
    }
}
