package com.bmpl.firstkotlinproject

fun main(args: Array<String>)
{

//two numbers

//Swap --> With third variable

//    var firstValue = 10
//    var secondValue = 20
//    var thirdValue : Int //initialization is must-->
//                    // no default value is assigned and hence will give error


//    thirdValue = firstValue // third = 10, first = 10
//    firstValue = secondValue // first = 20 , second = 20
//    secondValue = thirdValue

    // Swap without third variable
    // constant variable in kotlin
    // val keyword --> constant --> value will not change
    var firstValue = 10
    var secondValue = 20

    var value : Float = 94.76F  // literal must be specified with Capital or small f at the end-
    // -> now you are working with a float

    val FIXVALUE = 10

    firstValue += secondValue   // first = 30
    secondValue = firstValue - secondValue // 30-20 = 10
    firstValue -= secondValue   // 30 - 10 = 20

    print(value is Float)
    println("First Value = " + firstValue)
    println("second Value = " + secondValue)

    firstValue += FIXVALUE

}