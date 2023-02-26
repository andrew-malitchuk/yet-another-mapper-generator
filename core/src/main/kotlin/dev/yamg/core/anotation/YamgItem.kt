package dev.yamg.core.anotation

import dev.yamg.core.model.BaseMapperModel
import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS)
annotation class YamgItem(
    val fromClass:KClass<out BaseMapperModel>,
    val toClass: KClass<out BaseMapperModel>,
    val className: String = "",
){
    companion object{
        const val FROM_CLASS="fromClass"
        const val TO_CLASS="toClass"
        const val CLASS_NAME="className"
        const val MAPPER_SUPPER_CLASS="mapperSupperClass"
        const val EXCLUDE_FIELDS="excludeFields"
    }
}