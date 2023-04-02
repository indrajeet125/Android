package com.example.kotlinpractice

class KotlnCollections {
}

fun ListPractice() {
    val montths = listOf("Jnauary", "Ferbruary", "march")
    val anyType = listOf(1, 2, 3, true, false, "String")
    println("$montths\n $anyType")

    //you can access through index
    println("${montths[1]}")

    val additionalMonth = montths.toMutableList()
    val newmonth = listOf<String>("april", "May", "June")
    additionalMonth.addAll(newmonth)
    additionalMonth.add("july")
    additionalMonth.removeAll(newmonth)
    println(additionalMonth)


}

fun averageOfFive() {
    var arr = ArrayList<Double>()
    arr.add(1.0)
    arr.add(231.0)
    arr.add(124.0)
    arr.add(4351.0)
    arr.add(134.0)
    var avg = 0.0
    for (ele in arr) {
        avg += ele
    }
    avg /= arr.size
    print("average $avg")

}

class Lambda {
    //    val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
//    val sum: (Int, Int) -> Int = { a , b  -> a + b }
    val sum = { a: Int, b: Int -> a + b }



}

fun main() {


}