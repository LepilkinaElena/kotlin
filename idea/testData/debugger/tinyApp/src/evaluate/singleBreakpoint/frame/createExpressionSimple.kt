package createExpressionSimple

class MyClass: Base() {
    val i = 1
}

open class Base {
    val baseI = 2
}

fun main(args: Array<String>) {
    val myClass: Base = MyClass()
    //Breakpoint!
    val a = 1
}

// PRINT_FRAME
// DESCRIPTOR_VIEW_OPTIONS: NAME_AND_EXPRESSION

