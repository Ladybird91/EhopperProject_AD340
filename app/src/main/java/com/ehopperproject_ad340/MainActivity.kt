package com.ehopperproject_ad340

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var displayname : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayname = findViewById(R.id.text_Username)
        email = findViewById(R.id.text_EmailAddress)
        password = findViewById(R.id.text_Password)

        val login = findViewById<Button>(R.id.login_button)
        login.setOnClickListener(this)

        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)


        displayname.setText(getPreference(DISPLAYNAME))
        email.setText(getPreference(EMAIL))
        password.setText(getPreference(PASSWORD))
        // uGUPkSGyw5Hy7jnK
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_button -> signIn()
            R.id.button1 -> startActivity(Intent(this, Movies::class.java))
            R.id.button2 -> LiveCameraInfo.getLiveCameraInfo(this,LiveCameras::class.java)
            R.id.button3 -> LiveCameraInfo.getLiveCameraInfo(this, MapsActivity::class.java)
            R.id.button4 -> Toast.makeText(applicationContext, R.string.button4_toast, Toast.LENGTH_SHORT).show()
            R.id.button5 -> Toast.makeText(applicationContext, R.string.button5_toast, Toast.LENGTH_SHORT).show()
            R.id.button6 -> Toast.makeText(applicationContext, R.string.button6_toast, Toast.LENGTH_SHORT).show()
            R.id.button7 -> Toast.makeText(applicationContext, R.string.button7_toast, Toast.LENGTH_SHORT).show()
            R.id.button8 -> Toast.makeText(applicationContext, R.string.button8_toast, Toast.LENGTH_SHORT).show()
            R.id.button9 -> Toast.makeText(applicationContext, R.string.button9_toast, Toast.LENGTH_SHORT).show()
        }
    }

    private fun signIn() {
        val displayNameEntry = displayname.text.toString()
        val emailEntry = email.text.toString()
        val passwordEntry = password.text.toString()

        if(!validateForm(displayNameEntry, emailEntry, passwordEntry)) {
            return
        }

        savePreference(DISPLAYNAME, displayNameEntry)
        savePreference(EMAIL, emailEntry)
        savePreference(PASSWORD, passwordEntry)
        Log.d("signIn", "Saved display name: $displayNameEntry")
        Log.d("signIn", "Saved email: $emailEntry")
        Log.d("signIn", "Saved password: $passwordEntry")

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(emailEntry, passwordEntry)
            .addOnCompleteListener(this) { task ->
                Log.d("FIREBASE", "signIn:Listener:" + task.isSuccessful)
                if (task.isSuccessful) {
                    val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                    val profileUpdates: UserProfileChangeRequest =
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(displayNameEntry)
                            .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("FIREBASE", "User profile updated.")
                                startActivity(Intent(this, FirebaseActivity::class.java))
                            }
                        }
                } else {
                    Log.d("FIREBASE", "Sign-in failed")
                    Toast.makeText(this, "Sign in failed :(", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun validateForm(displayNameEntry : String, emailEntry : String, passwordEntry : String) : Boolean {
        var test = true

        if (TextUtils.isEmpty(displayNameEntry)) {
            displayname.error = "Required field"
            test = false
        }
        if (TextUtils.isEmpty(emailEntry)) {
            email.error = "Required field"
            test = false
        }
        if (TextUtils.isEmpty(passwordEntry)) {
            password.error = "Required field"
            test = false
        }

        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(emailEntry).matches())) {
            email.error = "Invalid email entry"
            test = false
        }
        if (passwordEntry.length < MINPASSWORDLENGTH) {
            password.error = "Invalid password entry"
            test = false
        }
        return test
    }

    private fun savePreference(title : String, input : String) {
        val pref = this.getSharedPreferences("com.ehopperproject_ad340", 0)
        pref.edit().putString(title, input).apply()
    }

    private fun getPreference(name : String) : String? {
        return this.getSharedPreferences("com.ehopperproject_ad340", 0).getString(name, "")
    }

    companion object {
        const val DISPLAYNAME = "displayname"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val MINPASSWORDLENGTH = 3
    }
}


