package com.bmpl.firstkotlinproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bmpl.firstkotlinproject.R.id.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        //findViewById(id)
        //drag and drop --> R.java file --> id of all the resources which we use

        //step-1 : connected widgets with backend

        var firstValue = findViewById(editText) as EditText
        var secondValue = findViewById(editText2) as EditText

        var checkButton = findViewById(checkButton) as Button
        var clearButton = findViewById(button2) as Button

        var resultTextView = findViewById(textView4) as TextView

        //step-2: attach listener

        //Lambda expression --> Java 8 java 8 --> dependencies --> java 7
        // Listener --> interface -->  unimplemented methods -->
        checkButton.setOnClickListener {

            var first  =  firstValue.text.toString().toInt()

            var second  = secondValue.text.toString().toInt()

            var result = first + second

            resultTextView.text = result.toString()

        }

        clearButton.setOnClickListener {

            firstValue.text = null
            secondValue.text = null
            resultTextView.text = null
        }
    }
}
