package com.ehopperproject_ad340

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button1 -> startActivity(Intent(this, Movies::class.java))
            R.id.button2 -> startActivity(Intent(this, LiveCameras::class.java))
            R.id.button3 -> startActivity(Intent(this, MapsActivity::class.java))
            R.id.button4 -> Toast.makeText(applicationContext, R.string.button4_toast, Toast.LENGTH_SHORT).show()
            R.id.button5 -> Toast.makeText(applicationContext, R.string.button5_toast, Toast.LENGTH_SHORT).show()
            R.id.button6 -> Toast.makeText(applicationContext, R.string.button6_toast, Toast.LENGTH_SHORT).show()
            R.id.button7 -> Toast.makeText(applicationContext, R.string.button7_toast, Toast.LENGTH_SHORT).show()
            R.id.button8 -> Toast.makeText(applicationContext, R.string.button8_toast, Toast.LENGTH_SHORT).show()
            R.id.button9 -> Toast.makeText(applicationContext, R.string.button9_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }


