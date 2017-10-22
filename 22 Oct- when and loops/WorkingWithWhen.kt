package com.bmpl.firstkotlinproject

// decision making statement --> if with kotlin
// when as a replacement of switch

//switch(condition){
// case '' : break;}

fun main(args: Array<String>) {

    var character = 'o'

    //vowel or consonant
    when(character){
        'a','e','i','o','u' -> print("vowel")
        else -> print("consonant")

//        'a'-> print("vowel")
//        'e'-> print("vowel")
//        'i'-> print("vowel")
//        'o'-> print("vowel")
//        'u'-> print("vowel")
//        else-> print("consonant")
    }

    var num = 57    //--> 40 - 50

    var array = intArrayOf(5,8,3,76,32,87, 57)

    when (num){
        in array -> print("inside array")
        else -> print("outside array")

//         !in 40..50 -> print("inside range")
//        else -> print("outside range")
    }

    val value = true
    val data = "Hello"
    print(value is Boolean) //true or false

    when (data){
        is String -> { print("true")
                    print("It is boolean")
        }
            else -> print("false")
    }
}











