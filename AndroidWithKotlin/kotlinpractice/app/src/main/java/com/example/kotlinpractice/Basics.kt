package com.example.kotlinpractice

 
class Basics {}

fun comment() {
    //single line comment
    /*
    this is multiline comment

     */
    //TODO: add new functionality
}

fun varVal() {

    var myname = "Indra"
    myname = "changed"
    print("hello $myname " + myname)
}

fun datatype() {
    var f: Float = 13.37f
    var d: Double = 3.14565652325656532
    var i: Byte = 25
    var i1: Short = 2020
    var l: Long = 1888888856666565
    var t: Boolean = true
    var c: Char = 'a'

}

fun ifelse() {
    var age = 34
    if (age > 21) {
        println("now you can drink ")
    } else if (age >= 18)
        println("you may vote now ")
    else if (age > 16)
        println("you may drive now ")
    else println("you are to young  ")
}

fun When() {
    var season = 9
    when (season) {
        1 -> println("spring")
        2 -> println("summer ")
        3 -> {
            println("fall")
            println("autumn")
        }
        else -> println("default")
    }

    var month = 55
    when (month) {
        in 3..5 -> println("spring")
        in 6..8 -> println("summer")
        in 9..11 -> println("fall")
        12, 1, 2 -> println("winter ")
        else -> println("invalid season ")

    }

}

fun breakContinue() {
    for (i in 1 until 20) {
        println("$i")
        if (i / 2 == 5)
            break
    }
    println("Done with the break ")

}

fun myfuntion() {
    println("function is called ")
}

fun addUp(a: Int, b: Int): Int {

    return a + b
}

fun nullabel() {
    var name: String = "deNIish"
    var nullabeName: String? = "denSJHHFDJsh"


    var len = name.length
    var nullableLen = nullabeName?.length

//    println(nullabeName?.lowercase( ))
    nullabeName?.let { println(it.length) }
}


fun main() {
    nullabel()
}


