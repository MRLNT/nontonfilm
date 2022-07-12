package com.ssrngstd.nontonfilm.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.ssrngstd.nontonfilm.R
import com.ssrngstd.nontonfilm.sign.SignUpPhotoScreenActivity
import com.ssrngstd.nontonfilm.sign.signin.User

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    lateinit var mDatabaseReference:DatabaseReference
    lateinit var mFirebaseInstance:FirebaseDatabase
    lateinit var mDatabase:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
//        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        val btn_lanjutkan = findViewById(R.id.btn_lanjutkan) as Button;
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_nama = findViewById<EditText>(R.id.et_nama)
        val et_email = findViewById<EditText>(R.id.et_email)

        btn_lanjutkan.setOnClickListener(){
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error = "Silahkan isi Username Anda"
                et_username.requestFocus()
            } else if(sPassword.equals("")){
                et_password.error = "Silahkan isi Password Anda"
                et_password.requestFocus()
            } else if(sNama.equals("")){
                et_nama.error = "Silahkan isi Nama Anda"
                et_nama.requestFocus()
            } else if(sEmail.equals("")){
                et_email.error = "Silahkan isi Email Anda"
                et_email.requestFocus()
            } else {
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }
        }

    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null){
            checkingUsername(sUsername, user)
        }

    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(sUsername).setValue(data)

                    startActivity(Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("nama", data.nama))

                } else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }
        })


    }
}