package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

@Suppress("DEPRECATION")
class Result : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        val username=intent.getCharSequenceExtra(Constants.username)
        val score=intent.getIntExtra(Constants.score,0)
        val questionNumber=intent.getIntExtra(Constants.questions,0)


        findViewById<TextView>(R.id.score).apply {
        text= score.toString()
        }

        when(score)
        {
            questionNumber->{
                findViewById<ImageView>(R.id.trophy).apply {
                    visibility=View.VISIBLE
                }
                findViewById<nl.dionsegijn.konfetti.xml.KonfettiView>(R.id.Confetti).apply {
                    start(Presets.explode())
                    start(Presets.explode())
                }
            }
        }


        findViewById<TextView>(R.id.scoreText).text="out of $questionNumber questions"

        findViewById<TextView>(R.id.congrats).apply {
            text="Congrats, $username"
        }

        findViewById<Button>(R.id.playAgain).apply {
            setOnClickListener{
                val ind=Intent(this@Result,MainActivity::class.java)
                startActivity(ind)
                finish()
            }
        }

    }
}