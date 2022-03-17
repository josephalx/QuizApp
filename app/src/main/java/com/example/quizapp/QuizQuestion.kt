package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*


class QuizQuestion : AppCompatActivity() {
    private var selectId: Int? = null
    private var position: Int = 1
    private var submitted:Boolean=false
    private var listQuestion:ArrayList<Question>? = null
    private var score:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        listQuestion = Constants.getQuestions()
//        Log.i("Question Size", listQuestion.toString())
        question()
        findViewById<TextView>(R.id.submit).apply {
            setOnClickListener {
                submit()

            }
        }
        
    }

    private fun question() {
        if (position <= listQuestion?.size!!) {
            populate(listQuestion!![position - 1])
        }
        else
        {
            val username=intent.getCharSequenceExtra(Constants.username)
           val ind= Intent(this,Result::class.java)
            ind.putExtra(Constants.username,username)
            ind.putExtra(Constants.score,score)
            ind.putExtra(Constants.questions, listQuestion!!.size)
            startActivity(ind)
            finish()
        }

    }

    private fun populate(mcq: Question) {
        submitted=false
        val llt = findViewById<LinearLayout>(R.id.options)
        delete()
        selectId = null
        findViewById<TextView>(R.id.question).apply {
            text = mcq.question
        }
        findViewById<ProgressBar>(R.id.progressBar).also {PB->
            PB.progress = position
        findViewById<TextView>(R.id.progress).apply {
            "$position/${PB.max}".also { text = it }
        }}
        mcq.options.mapIndexed { _index, item ->
            llt.also { LL ->
                LL.addView(OptionView(this@QuizQuestion).apply {
                    text = item
                    setBackgroundResource(R.drawable.option_background)
                    id = (_index + 1)
                    gravity = Gravity.CENTER
                    textSize = 18f
                    val scale = ((resources.displayMetrics.density) * 15 + 0.5f).toInt()
                    setPadding(scale, scale, scale, scale)
                    setTypeface(null, Typeface.BOLD)
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, scale, 0, 0)
                    }
                    setOnClickListener {
                        checker(it as OptionView)
                    }

                })
            }
        }

    }

    // Submit function
    @SuppressLint("ResourceType", "SetTextI18n")
    private fun submit() {
        if (submitted && listQuestion!!.size>=position)
        {
            position++
            question()
            findViewById<Button>(R.id.submit).apply {
                text="Submit"
            }
            return
        }
        Log.i("select id"," $selectId")
        val correct=listQuestion?.get(position-1)?.correct
        if (selectId == null) {
          Toast.makeText(this,"Please Select an Option", Toast.LENGTH_SHORT).show()
        }
        else {
            selectId?.let {
                if (it != correct) {
                    findViewById<OptionView>(it).setWrong(true)
                }
                else {
                    score++
                }
                correct.let { it1 ->
                    if (it1 != null) {
                        findViewById<OptionView>(it1).setCorrect(true)
                    }
                }
            }
            submitted=true
            when(position)
            {
                listQuestion?.size->{findViewById<Button>(R.id.submit).apply {
                    text="Finish"
                }
                Toast.makeText(this,"You have completed the quiz",Toast.LENGTH_SHORT).show()
                }
                else->
                {
                    findViewById<Button>(R.id.submit).apply {
                        text="Next Question"
                    }
                }
            }

        }
    }
// Toggle Function to select one option at a time

    fun checker(view: OptionView) {
        if (submitted)
        {
            return

        }

        selectId?.let {
            findViewById<OptionView>(it).apply {
                setSelect(!isSelect())
            }
        }
        if(view.id==selectId)
        {
            selectId=null
        }
        else if (view.id != selectId) {

            selectId = view.id
            view.apply {
                setSelect(true)
            }
        }

    }

    //for deleting options
    private fun delete() {
            findViewById<LinearLayout>(R.id.options).apply {
                removeAllViewsInLayout()
        }
    }
}

