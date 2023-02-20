package dev.yamg.core.anotation

import dev.yamg.core.model.BaseMapperModel
import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS)
annotation class YamgItem(
    val from: KClass<out BaseMapperModel>,
    val to: KClass<out BaseMapperModel>,
)