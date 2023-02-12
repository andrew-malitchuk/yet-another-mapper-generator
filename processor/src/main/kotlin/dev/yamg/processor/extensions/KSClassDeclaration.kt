package dev.yamg.processor.extensions

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument
import com.squareup.kotlinpoet.ClassName
import kotlin.reflect.KClass


/**
 *
 */
// TODO: add doc
fun KSClassDeclaration.getClassName(): ClassName {
    return ClassName.bestGuess(qualifiedName!!.getQualifier() + "." + qualifiedName!!.getShortName())
}

/**
 *
 */
// TODO: add doc
fun KSClassDeclaration.getFieldValueFromAnnotation(
    annotationClass: KClass<*>,
    fieldName: String,
): KSValueArgument? {
    return annotations.firstOrNull { it.shortName.asString() == annotationClass.simpleName }?.arguments?.firstOrNull {
        it.name?.asString() == fieldName
    }
}