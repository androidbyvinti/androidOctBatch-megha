package com.android.calc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var firstValue : Int = 0
    var secondValue : Int = 0
    var temp : Int = 0
    lateinit var opeartion : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClicked(view : View){
        var button = findViewById<Button>(view.id)

        if(viewTextView.text.isEmpty()){
            //resultTextView.text = button.text
            viewTextView.text = button.text

        } else{
            //resultTextView.text = resultTextView.text.toString().plus(button.text)
            viewTextView.text = viewTextView.text.toString().plus(button.text)
        }
    }

    fun cancelText(view : View){
        viewTextView.text = ""
        resultTextView.text = ""
        firstValue = 0
        secondValue = 0
    }

    fun clearText(view : View){
        if(!viewTextView.text.isEmpty()){
            viewTextView.text = viewTextView.text.dropLast(1)
        }
    }
    // void operationRequest(String operation){...}
    fun operationRequest(view : View){

        if(firstValue==0){
            firstValue = viewTextView.text.toString().toInt()
        }

        Log.d("MainActivity","First value = " + firstValue)

        //resultTextView.text = ""
        var button = findViewById<Button>(view.id)
        viewTextView.text = viewTextView.text.toString().plus(button.text)
        opeartion = button.text.toString()
    }

    fun equal(view : View){
        secondValue = viewTextView.text.substring(viewTextView.text.lastIndexOf(opeartion) + 1).toInt()
        Log.d("MainActivity","Second value = " + secondValue)
        Log.d("MainActivity","Operation = " + opeartion)
        resultTextView.text = when(opeartion){
            "+"->{
                (firstValue + secondValue).toString()
            }
            "-"->{
                (firstValue - secondValue).toString()
            }
            "X"->{
                (firstValue * secondValue).toString()
            }
            "/"->{
                (firstValue / secondValue).toString()
            }
            else->{""}
        }
        firstValue = resultTextView.text.toString().toInt()
        //secondValue = 0
    }
}