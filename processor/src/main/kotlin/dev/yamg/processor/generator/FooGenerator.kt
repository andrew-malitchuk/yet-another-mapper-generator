package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.writeTo

class FooGenerator(
    private val classDeclaration: KSClassDeclaration,
    private val codeGenerator: CodeGenerator,
    private val methodName: String,
    private val fileName: String,
    private val parentClass: ClassName,
    private val className: ClassName,
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
            result += "${it.name?.getShortName()},\n"
        }
        return result
    }

}