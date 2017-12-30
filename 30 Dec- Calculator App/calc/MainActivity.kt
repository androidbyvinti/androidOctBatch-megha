package com.android.calc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClicked(view : View){
        var button = findViewById<Button>(view.id)

        if(resultTextView.text.isEmpty()){
            resultTextView.text = button.text
        } else{
            resultTextView.text = resultTextView.text.toString().plus(button.text)
        }
    }

    fun cancelText(view : View){
        resultTextView.text = ""
    }

    fun clearText(view : View){
        if(!resultTextView.text.isEmpty()){
            resultTextView.text = resultTextView.text.dropLast(1)
        }
    }
}