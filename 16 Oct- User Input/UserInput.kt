package com.bmpl.firstkotlinproject

import java.util.*


fun main(args : Array<String>){
    //from user
    //Scanner class
    //Scanner scanner = new Scanner(System.in);

    //when ever object is created a constructor is called
    //System perdefiend class
    // in--> object --> Class.object

    val scanner = Scanner(System.`in`)
    println("Enter your first value")
    var firstValue = scanner.nextInt()

    println("Enter your second value")
    var secondValue = scanner.nextInt()

    println("Result is = " + (firstValue + secondValue))
}