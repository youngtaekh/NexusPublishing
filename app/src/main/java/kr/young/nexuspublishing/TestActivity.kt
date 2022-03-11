package kr.young.nexuspublishing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voice_call_activity)

        val tvTest = findViewById<TextView>(R.id.tv_test)
        tvTest.text = "a;sdlkfjas;dlkfj"
    }
}