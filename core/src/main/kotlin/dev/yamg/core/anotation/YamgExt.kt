package dev.yamg.core.anotation

import dev.yamg.core.model.BaseMapperModel
import kotlin.reflect.KClass

/**
 * Annotation which helps KSP to generate mapper extension method
 */
@Target(AnnotationTarget.CLASS)
annotation class YamgExt(
    /**
     * The class that will be used as a mapping result
     */
    val targetClass: KClass<out BaseMapperModel>,
    /**
     * Extension method name
     */
    val methodName: String="",
    /**
     * Fields to exclude in parent class
     */
    val excludeFields: Array<String> = [],
)

