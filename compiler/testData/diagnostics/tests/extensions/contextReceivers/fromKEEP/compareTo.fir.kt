// !LANGUAGE: +ContextReceivers

data class Pair<A, B>(val first: A, val second: B)

context(Comparator<T>)
infix <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun <T> T.compareTo(other: T) = <!UNRESOLVED_REFERENCE!>compare<!>(this, other)

context(Comparator<T>)
val <T> Pair<T, T>.max get() = if (first > second) first else second

fun test() {
    val comparator = Comparator<String> { a, b ->
        if (a == null || b == null) 0 else a.length.compareTo(b.length)
    }
    with(comparator) {
        Pair("OK", "fail").max
    }
}