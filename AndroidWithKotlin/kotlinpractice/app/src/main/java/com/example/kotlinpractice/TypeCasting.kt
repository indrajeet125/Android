package com.example.kotlinpractice
/*
1. as unsafe caste, if caste  not possible then throw an exception
2. as? safe case if caste not possible then return null

 */


class TypeCasting {
}
fun main(args:Array<String>){
    val obj:Any?=123
    val str:String? =obj as? String
    print(str)
}