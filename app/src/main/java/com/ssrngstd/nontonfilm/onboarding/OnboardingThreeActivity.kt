package com.ssrngstd.nontonfilm.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ssrngstd.nontonfilm.R
import com.ssrngstd.nontonfilm.sign.signin.SignInActivity

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        var btn_home = findViewById(R.id.btn_home) as Button;
        btn_home.setOnClickListener(){
            finishAffinity()
            startActivity(Intent(this, SignInActivity::class.java));
        }
    }
}