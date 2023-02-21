package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName

class YamgItemGenerator(
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

    }

}
