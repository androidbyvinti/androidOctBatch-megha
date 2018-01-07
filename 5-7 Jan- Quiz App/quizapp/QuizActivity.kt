package com.android.quizapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    lateinit var questions : Array<String>
    lateinit var options : Array<String>
    lateinit var answers : Array<String>
    var counter = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var intent = intent

        var subject = intent.getStringExtra("subject")

        when(subject) {

            "c" -> {
                questions = resources.getStringArray(R.array.cQuestions)
                options = resources.getStringArray(R.array.cOptions)
                answers = resources.getStringArray(R.array.cAnswers)
            }
            "c++" -> {
                questions = resources.getStringArray(R.array.cQuestions)
                options = resources.getStringArray(R.array.cOptions)
                answers = resources.getStringArray(R.array.cAnswers)
            }
        }
        LoadData()
    }

    fun nextButtonClicked(view : View){
        checkScore()
        counter++
        radioGroup.clearCheck() //
        LoadData()
    }

    private fun LoadData()
    {
        if(counter <= questions.size - 1)
        {
            questionTextView.text = questions[counter]
            radioButton1.text = options[4 * counter]             // 4 * 2 -> 8
            radioButton2.text = options[4 * counter + 1] // 4 * 2  + 1= 9
            radioButton3.text = options[4 * counter + 2] // 4 * 2  + 2 =10
            radioButton4.text = options[4 * counter + 3] // 4 * 1 + 3 = 7
        }
        else{
            var intent = Intent(this, ScoreActivity :: class.java)
            intent.putExtra("Score" , score)
            startActivity(intent)
            finish()
        }
    }

    private fun checkScore(){
        var radioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        if(radioButton.text == answers[counter]){
            score += 10
            Toast.makeText(this, "Score is $score", Toast.LENGTH_LONG).show()
        }
    }
}