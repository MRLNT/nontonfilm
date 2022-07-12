package com.ssrngstd.nontonfilm.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ssrngstd.nontonfilm.R

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        var btn_home = findViewById(R.id.btn_home) as Button;
        btn_home.setOnClickListener(){
            startActivity(Intent(this, OnboardingThreeActivity::class.java));
        }
    }
}