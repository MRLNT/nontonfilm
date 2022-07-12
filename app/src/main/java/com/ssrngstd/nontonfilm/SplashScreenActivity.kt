package com.ssrngstd.nontonfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ssrngstd.nontonfilm.onboarding.OnboardingOneActivity
import com.ssrngstd.nontonfilm.onboarding.OnboardingThreeActivity


@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this,OnboardingOneActivity::class.java))
            finish()
        }, 2000)

    }
}
