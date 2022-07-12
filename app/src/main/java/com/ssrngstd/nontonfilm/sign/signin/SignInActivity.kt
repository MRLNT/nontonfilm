package com.ssrngstd.nontonfilm.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.ssrngstd.nontonfilm.HomeActivity
import com.ssrngstd.nontonfilm.R
import com.ssrngstd.nontonfilm.sign.signup.SignUpActivity
import com.ssrngstd.nontonfilm.utils.Preferences
import kotlin.jvm.java as java


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String

    //    Inisialisasi Firebase
    lateinit var mDatabase: DatabaseReference
    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password = findViewById<EditText>(R.id.et_password)
        val btn_home = findViewById(R.id.btn_home) as Button
        val btn_daftar = findViewById(R.id.btn_daftar) as Button

//        Mengambil data dari firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding","1")
        if(preference.getValues("status").equals("1")){
            finishAffinity()
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
        }

        btn_home.setOnClickListener(){

            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")){
                et_username.error = "Silahkan tulis username anda"
                et_username.requestFocus()
            }else if (iPassword.equals("")){
                et_password.error = "Silahkan tulis password anda"
                et_password.requestFocus()
            }else{
                pushLogin(iUsername, iPassword)
            }
        }

        btn_daftar.setOnClickListener(){
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG).show()
                } else{
                    if (user.password.equals(iPassword)){
                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        startActivity(Intent(this@SignInActivity, HomeActivity::class.java));
                    } else{
                        Toast.makeText(this@SignInActivity, "Password anda salah", Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
    }
}