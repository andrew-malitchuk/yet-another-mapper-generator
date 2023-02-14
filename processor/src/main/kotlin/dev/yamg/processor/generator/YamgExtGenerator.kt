package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.yamg.processor.extensions.getAnnotation

class YamgExtGenerator(
    private val classDeclaration: KSClassDeclaration,
    private val codeGenerator: CodeGenerator,
    private val methodName: String,
    private val fileName: String,
    private val parentClass: ClassName,
    private val className: ClassName,
    private val excludeFields: List<String>? = null,
    private val logger: KSPLogger
) {

    fun build() {
        val extensionMethod = FileSpec.builder("dev.yamg.app", fileName)
            .addFunction(
                FunSpec
                    .builder(methodName)
                    .receiver(parentClass)
                    .returns(className)
                    // TODO: format string (a.k.a pretty print)
                    .addStatement(
                        "return ${className.simpleName}(" +
                                getClassField() +
                                ")"
                    )
                    .build()
            )
            .build()

        extensionMethod.writeTo(codeGenerator, Dependencies(true))
    }


    private fun getClassField(): String {
        val params = classDeclaration.primaryConstructor?.parameters
        var result = ""
        params?.forEach {
            val customFieldName = it.getAnnotation<String>("fieldName")
            val field = it.name?.getShortName()
            if (!excludeFields.isNullOrEmpty()) {
                if (!excludeFields.contains(customFieldName ?: field)) {
                    result += generateConstructorField(it)
                }
            } else {
                result += generateConstructorField(it)
            }
        }
        return result
    }

    private fun generateConstructorField(item: KSValueParameter): String? {
        with(item) {
            val customFieldName = getAnnotation<String>("fieldName")
            val field = name?.getShortName()
            val isMarkedNullable = type.resolve().isMarkedNullable

            var parameter = customFieldName ?: field
            if (isMarkedNullable) {
                parameter += "?:${getNullableDefaultValue(item)}"
            }
            return parameter
        }
    }

    private fun getNullableDefaultValue(field: KSValueParameter): String {
        var result = ""
        val className = field.type.resolve().toClassName().simpleName
        logger.error("className: $className")
        result = when (className) {
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
                throw IllegalStateException("")
            }
        }


        return result
    }


}