package edu.uw.basickotlin

import java.util.Currency

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(phrase:Any): String {
    when (phrase) {
        "Hello" -> return("world")
        0 -> return("zero")
        1 -> return("one")
        in 2..10 -> return("low number")
        is String -> return("Say what?")
        is Int -> return("a number")
    }
    return("I don't understand")
}


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(first: Int, second: Int): Int {
    return(first + second)
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(first: Int, second: Int): Int {
    return(first - second)
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(first: Int, second: Int, func:(Int, Int) -> Int): Int {
    return(func(first, second))
}
// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(private var _amount: Int, private val _currency: String) {
    init {
        require(_amount >= 0) { "Amount cannot be negative" }
        require(_currency in setOf("USD", "EUR", "CAN", "GBP")) { "Currency not accepted" }
    }

    val amount: Int
        get() = _amount

    val currency: String
        get() = _currency

    fun convert(targetCurrency: String): Money {
        val convertedAmount = when (_currency) {
            "USD" -> {
                when (targetCurrency) {
                    "GBP" -> _amount / 2
                    "EUR" -> _amount * 3 / 2
                    "CAN" -> _amount * 5 / 4
                    else -> _amount
                }
            }
            "GBP" -> {
                when (targetCurrency) {
                    "USD" -> _amount * 2
                    "EUR" -> _amount * 3
                    "CAN" -> _amount * 5 / 2
                    else -> _amount
                }
            }
            "EUR" -> {
                when (targetCurrency) {
                    "USD" -> _amount * 2 / 3
                    "GBP" -> _amount / 3
                    "CAN" -> _amount * 5 / 6
                    else -> _amount
                }
            }
            "CAN" -> {
                when (targetCurrency) {
                    "USD" -> _amount * 4 / 5
                    "GBP" -> _amount * 2 / 5
                    "EUR" -> _amount * 6 / 5
                    else -> _amount
                }
            }
            else -> _amount
        }
        return Money(convertedAmount, targetCurrency)
    }
    operator fun plus(other: Money): Money {
        var changeMoney = other.convert(this.currency)
        return(Money(changeMoney.amount + this.amount, this.currency))
    }
}



