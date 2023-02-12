package dev.yamg.processor.extensions

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName


fun KSClassDeclaration.getClassName(): ClassName {
    return ClassName.bestGuess(qualifiedName!!.getQualifier() + "." + qualifiedName!!.getShortName())
}