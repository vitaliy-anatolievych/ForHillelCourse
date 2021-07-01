package com.vitalii.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.VideoView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)


        val splashVideo: VideoView = findViewById(R.id.police_splash)
        splashVideo.setVideoPath("android.resource://$packageName/" + R.raw.police_splash)
        splashVideo.start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 3000)
    }
}