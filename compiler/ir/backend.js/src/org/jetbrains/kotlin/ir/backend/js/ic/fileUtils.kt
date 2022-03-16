/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js.ic

import org.jetbrains.kotlin.protobuf.CodedInputStream
import org.jetbrains.kotlin.protobuf.CodedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

internal inline fun <T> File.ifExists(f: File.() -> T): T? = if (exists()) f() else null

internal fun File.recreate() {
    if (exists()) {
        delete()
    }
    createNewFile()
}

internal inline fun <T> File.useCodedInputIfExists(f: CodedInputStream.() -> T) = ifExists {
    CodedInputStream.newInstance(FileInputStream(this)).f()
}

internal inline fun File.useCodedOutput(f: CodedOutputStream.() -> Unit) {
    parentFile?.mkdirs()
    recreate()
    FileOutputStream(this).use {
        val out = CodedOutputStream.newInstance(it)
        out.f()
        out.flush()
    }
}

internal inline fun <T> CodedOutputStream.writeCollection(collection: Collection<T>, f: (T) -> Unit) {
    writeInt32NoTag(collection.size)
    collection.forEach { f(it) }
}

internal inline fun <T, C : MutableCollection<T>> CodedInputStream.readCollection(collection: C, f: () -> T): C {
    repeat(readInt32()) {
        collection.plusAssign(f())
    }
    return collection
}
