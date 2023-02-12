package dev.yamg.processor.extensions

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import kotlin.reflect.KClass


/**
 *
 */
// TODO: add doc
// TODO: somehow extract field name
fun Resolver.getClassFromAnnotation(
    annotationClass: KClass<*>,
    fieldName: String,
    logger: KSPLogger
): KSClassDeclaration? {
    val annotationClassPath = annotationClass.qualifiedName
    val annotationClassName = annotationClass.simpleName
    if (annotationClassPath == null || annotationClassName == null) {
        logger.exception(NullPointerException("Something wrong with ${annotationClass.simpleName}; please check it."))
        return null
    }
    val result = getSymbolsWithAnnotation(annotationClassPath)
        .map { ksAnnotated ->
            val args = ksAnnotated.annotations.single {
                it.shortName.asString() == annotationClassName
                        && it.annotationType.resolve().declaration.qualifiedName?.asString() == annotationClassPath
            }.arguments
            val consumerType =
                args.single { it.name?.asString() == fieldName }.value as KSType
            consumerType.declaration as KSClassDeclaration
        }
    return result.firstOrNull()
}