package dev.yamg.processor.generator

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.ksp.toClassName
import dev.yamg.core.anotation.YamgFieldName
import dev.yamg.processor.extensions.getAnnotation

fun getNullableDefaultValue(field: KSValueParameter): String {
    return when (field.type.resolve().toClassName().simpleName) {
        String::class.simpleName -> {
            "\"\""
        }
        Int::class.simpleName -> {
            "0"
        }
        Long::class.simpleName -> {
            "0L"
        }
        Float::class.simpleName -> {
            "0f"
        }
        Double::class.simpleName -> {
            "0"
        }
        Boolean::class.simpleName -> {
            "false"
        }
        Byte::class.simpleName -> {
            "0"
        }
        Short::class.simpleName -> {
            "0"
        }
        Char::class.simpleName -> {
            "\'\'"
        }
        else -> {
            throw IllegalStateException("YAMG is not able to predict default value for nullable field `$field`")
        }
    }
}

fun generateConstructorField(item: KSValueParameter, predicate: String? = null): String {
    with(item) {
        val customFieldName = getAnnotation<String>(YamgFieldName.FIELD_NAME)
        val field = name?.getShortName()
        val isMarkedNullable = type.resolve().isMarkedNullable

        var parameter = customFieldName ?: field
        if (isMarkedNullable) {
            parameter += "?:${getNullableDefaultValue(item)}"
        }
//        return "\n\t${parameter},"
        return if (predicate.isNullOrEmpty()) {
            "\n\t${parameter},"
        } else {
            "\n\t${predicate}.${parameter},"
        }
    }
}

fun getClassField(
    classDeclaration: KSClassDeclaration,
    excludeFields: List<String>? = null,
    predicate: String? = null
): String {
    val params = classDeclaration.primaryConstructor?.parameters
    var result = ""
    params?.forEach {
        val customFieldName = it.getAnnotation<String>(YamgFieldName.FIELD_NAME)
        val field = it.name?.getShortName()
        if (!excludeFields.isNullOrEmpty()) {
            if (!excludeFields.contains(customFieldName ?: field)) {
                result += generateConstructorField(it,predicate)
            }
        } else {
            result += generateConstructorField(it,predicate)
        }
    }
    return result
}
