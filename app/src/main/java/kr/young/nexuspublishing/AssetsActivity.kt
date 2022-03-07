package kr.young.nexuspublishing

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kr.young.assets.AssetsManager
import kr.young.assets.Constants.Companion.BINANCE
import kr.young.common.TouchEffect

class AssetsActivity : AppCompatActivity(), OnClickListener, OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assets)

        val tvTest = findViewById<TextView>(R.id.tv_test)
        tvTest.setOnClickListener(this)
        tvTest.setOnTouchListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_test -> test()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v!!.id) {
            R.id.tv_test -> TouchEffect.tv(v, event)
        }
        return super.onTouchEvent(event)
    }

    private fun test() {
        AssetsManager.instance.getAssets(BINANCE)
        AssetsManager.instance.getCoinInfo()
    }

    companion object {
        private const val TAG = "AssetsActivity"
    }
}
