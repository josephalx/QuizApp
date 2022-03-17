@file:Suppress("DEPRECATION")

package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
        val startBtn=findViewById<Button>(R.id.start_btn)
        val nameField=findViewById<EditText>(R.id.nameField)
        startBtn.setOnClickListener {
            if (nameField.text.toString().isEmpty())
            {
                Toast.makeText(this,"No Text",Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this, QuizQuestion::class.java)
                intent.putExtra(Constants.username,nameField.text)
                startActivity(intent)
                finish()
                println("end")
            }

        }
    }
}