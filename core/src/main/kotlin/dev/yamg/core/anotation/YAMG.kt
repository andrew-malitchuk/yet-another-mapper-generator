package dev.yamg.core.anotation


import dev.yamg.core.model.BaseMapperModel
import kotlin.reflect.KClass

annotation class YAMG(
    val from: KClass<BaseMapperModel>,
    val to: KClass<BaseMapperModel>,
)
