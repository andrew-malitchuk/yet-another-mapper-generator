package dev.yamg.core.anotation

import dev.yamg.core.model.BaseMapperModel
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
annotation class Foo(
    val targetClass: KClass<out BaseMapperModel>,
    val methodName: String = "methodName"
)

