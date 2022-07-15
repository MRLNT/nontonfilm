package com.ssrngstd.nontonfilm.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ssrngstd.nontonfilm.R
import com.ssrngstd.nontonfilm.sign.signin.SignInActivity
import com.ssrngstd.nontonfilm.utils.Preferences

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preference:Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        var btn_home = findViewById(R.id.btn_home) as Button
        var btn_daftar = findViewById(R.id.btn_daftar) as Button

        preference = Preferences(this)

        if(preference.getValues("onboarding").equals("1")){
            finishAffinity()
            startActivity(Intent(this, SignInActivity::class.java))

        }

        btn_home.setOnClickListener(){
            startActivity(Intent(this, OnboardingTwoActivity::class.java));
        }

        btn_daftar.setOnClickListener(){
            preference.setValues("onboarding", "1")
            finishAffinity()
            startActivity(Intent(this, SignInActivity::class.java))

        }

       

    }
}