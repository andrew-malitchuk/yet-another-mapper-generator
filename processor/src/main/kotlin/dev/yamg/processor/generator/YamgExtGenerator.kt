package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.writeTo

class YamgExtGenerator(
    private val classDeclaration: KSClassDeclaration,
    private val codeGenerator: CodeGenerator,
    private val methodName: String,
    private val fileName: String,
    private val parentClass: ClassName,
    private val className: ClassName,
    private val excludeFields: List<String>? = null,
    private val packageName: String,
    private val logger: KSPLogger
) {

    fun build() {
        val extensionMethod = FileSpec.builder(packageName, fileName)
            .addFunction(
                FunSpec
                    .builder(methodName)
                    .receiver(parentClass)
                    .returns(className)
                    .addStatement(
                        "return ${className.simpleName}(" +
                                getClassField(classDeclaration,excludeFields) +
                                "\n)"
                    )
                    .build()
            )
            .build()
        extensionMethod.writeTo(codeGenerator, Dependencies(true))
    }

}