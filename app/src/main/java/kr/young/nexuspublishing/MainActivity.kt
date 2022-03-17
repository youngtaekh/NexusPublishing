package kr.young.nexuspublishing

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.widget.TextView
import kr.young.common.DebugLog
import kr.young.common.Test
import kr.young.common.TouchEffect
import kr.young.common.activity.SettingsActivity

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
        val tvPjsip = findViewById<TextView>(R.id.tv_pjsip)
        tvAsset.setOnClickListener(this)
        tvAsset.setOnTouchListener(this)
        tvPjsip.setOnClickListener(this)
        tvPjsip.setOnTouchListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_asset -> assetActivity()
            R.id.tv_pjsip -> pjsipActivity()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v is TextView) {
            TouchEffect.tv(v, event)
        }
//        when (v!!.id) {
//            R.id.tv_asset -> TouchEffect.tv(v, event)
//            R.id.tv_pjsip -> TouchEffect.tv(v, event)
//        }
        return super.onTouchEvent(event)
    }

    private fun assetActivity() {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }

    private fun pjsipActivity() {
        val intent = Intent(this, PjsipActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
