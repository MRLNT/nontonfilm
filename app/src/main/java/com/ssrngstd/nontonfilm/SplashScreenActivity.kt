package com.ssrngstd.nontonfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ssrngstd.nontonfilm.onboarding.OnboardingOneActivity

/*
    ini adalah activity pertama yang akan dirun
    tidak ada fitur spesial disini
    hanya melakukan pending
    beberapa detik saja.

 */

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity,
                OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
