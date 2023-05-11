package com.example.simplequizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val ... -> finding the edittext and button
        val editTextName = findViewById<EditText>(R.id.nameInput)

        val navBtn = findViewById<Button>(R.id.startButton)

        navBtn.setOnClickListener {
            val userNameInput = editTextName.text
            // makes a toast = context + welcome + text and user input + duration
            Toast.makeText(this, "Welcome, $userNameInput!", Toast.LENGTH_SHORT).show()



            //new page when button is clicked (activity_second.xml and SecondActivity.kt
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("NAME_INPUT", userNameInput)
            startActivity(intent)




        }
    }
}