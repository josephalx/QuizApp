package com.example.quizapp

object Constants{
    const val username="Username"
    const val score="Score"
    const val questions="Questions"
    fun getQuestions():ArrayList<Question>{
        val question=ArrayList<Question>()
        question.add(Question(1,"What is my Name",R.drawable.person, ArrayList(listOf("Joseph","James","Alex","Mathew")),1
        ))
        question.add(Question(2,"What is my Name",R.drawable.person, ArrayList(listOf("Joseph","James","Alex","Mathew")),1
        ))
        question.add(Question(3,"What is my Name", R.drawable.person,ArrayList(listOf("Joseph","James","Alex","Mathew")),1
        ))
        question.add(Question(4,"What is my Name",R.drawable.person, ArrayList(listOf("Joseph","James","Alex","Mathew")),1
        ))
        return  question
    }
}