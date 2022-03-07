package kr.young.nexuspublishing

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.widget.TextView
import kr.young.common.DateUtil
import kr.young.common.DebugLog
import kr.young.common.Test
import kr.young.common.TouchEffect

class MainActivity : AppCompatActivity(), OnClickListener, OnTouchListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Init DebugLog
        DebugLog.logInit(BuildConfig.DEBUG)

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.text = Test.run()

        val tvAsset = findViewById<TextView>(R.id.tv_asset)
        tvAsset.setOnClickListener(this)
        tvAsset.setOnTouchListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_asset -> assetActivity()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v!!.id) {
            R.id.tv_asset -> TouchEffect.tv(v, event)
        }
        return super.onTouchEvent(event)
    }

    private fun assetActivity() {
        val intent = Intent(this, AssetsActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
