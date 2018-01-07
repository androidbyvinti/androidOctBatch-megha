package com.android.quizapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        cLinearLayout.setOnClickListener(this)
        cPlusLinearLayout.setOnClickListener(this)
        javaLinearLayout.setOnClickListener(this)
        androidLinearLayout.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var intent = Intent(this, QuizActivity :: class.java)

        var value = when(view!!.id){
            cLinearLayout.id-> "c"
            cPlusLinearLayout.id-> "c++"
            javaLinearLayout.id-> "java"
            androidLinearLayout.id-> "android"
            else-> ""
        }
        intent.putExtra("subject", value)
        startActivity(intent)
    }
}