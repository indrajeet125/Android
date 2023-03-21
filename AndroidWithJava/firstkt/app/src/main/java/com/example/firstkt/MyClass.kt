package com.example.firstkt


fun main() {
    var obj=Add(2)



}

class Add {
    init {
        println("primary constroctor called  ..")
    }

    constructor(a: Int, b: Int) {
        println("two ")

    }



  constructor(a: Int) {
        println("one ")


    }

    constructor(a: Int, b: Int, c: Int) {
        println("three ")


    }


}