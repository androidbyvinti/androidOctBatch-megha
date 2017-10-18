package com.bmpl.firstkotlinproject

import java.util.*

// Decision making statements in kotlin or Control flow statements
// if-else  and  when

    fun main(args: Array<String>){

        val scanner = Scanner(System.`in`)

        print("Enter your first value")
        val firstValue = scanner.nextInt()

        print("Enter your second value")
        val secondValue = scanner.nextInt()

        print("Enter your third value")
        val thirdValue = scanner.nextInt()

        if (firstValue < secondValue)
        {
            print("Second is greater in First Block")
            //print("Yes this is true")
        }
        else {
            print("First is greater in Ist Block")
            //print("Yes this is true")
        }

        if (firstValue>secondValue) print("FirstValue Greater")
        else print("Second is greater")

        if(firstValue>secondValue && firstValue>thirdValue)
            print("first is greater")
        else if(secondValue>firstValue && secondValue>thirdValue)
            print("second is greater")
        else
            print("Third is greater")


        val result  = if (firstValue>secondValue){  //{Blocks}
            print("first is greater")
            firstValue
        }
        else{
            print("second is greater")
            secondValue
        }
        print("Result is= " +result)

        if(firstValue>secondValue) println(firstValue) else println(secondValue)

        //int --> String , Integer
        // Int, Float, Double

        var firstName : String = "Ram"
        var secondName : String = "ram"

        if(firstName == secondName) println("equal") else println("Not equal")

    }