// FIR_IDENTICAL
// SKIP_TXT
// ISSUE: KT-51758

@PublishedApi
internal class SomeClass {
    private val somethingPrivate = "123"

    public val somethingPublic = "456"

    fun foo() = "789"
}