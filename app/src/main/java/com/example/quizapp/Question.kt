package com.example.quizapp

data class Question(val index:Int,
                     val question:String,
                     val image:Int,
                     val options:ArrayList<String>,
                     val correct:Int,)
{
}