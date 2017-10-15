package com.bmpl.firstkotlinproject

// inside your class
// public static void main(String args[]){...} --> method or function

//void main(int value)
//fun main(value : int)
// type

fun main(args: Array<String>)
{
    // variable declaration :
    // int value = 10;

    //variable declaration -->
    // keyword :
    // var (normal variable and value can be changed at runtime or compile time)
    var value : String = "Hey" // sure hold --> for a specific type --> explictly define
    println(value is String) //keyword --> is --> true or false --> check
    //system.out.println("");
    println("Hello Kotlin")
}
