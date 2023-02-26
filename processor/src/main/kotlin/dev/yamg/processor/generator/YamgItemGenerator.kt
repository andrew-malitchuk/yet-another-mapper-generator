package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.writeTo

class YamgItemGenerator(
    private val classDeclaration: KSClassDeclaration,
    private val codeGenerator: CodeGenerator,
//    private val methodName: String,
//    private val parentClass: ClassName,
    private val className: String,
//    private val excludeFields: List<String>? = null,
    private val packageName: String,
    private val logger: KSPLogger
) {

    fun build() {

        val mapperClass = FileSpec.builder(packageName, className).apply {
            this.addType(
                TypeSpec.classBuilder(className)
                    .addSuperinterface(
                        ClassName(
                            "dev.yamg.core.mapper.model",
                            "DomainUiModelMapper"
                        ).plusParameter(
                            ClassName(
                                "dev.yamg.core.model",
                                "DomainMapperModel"
                            )
                        ).plusParameter(
                            ClassName(
                                "dev.yamg.core.model",
                                "UiMapperModel"
                            )
                        )
                    )
                    .addFunction(
                        FunSpec
                            .builder("mapTo")
                            .returns(
                                ClassName(
                                    "dev.yamg.core.model",
                                    "UiMapperModel"
                                )
                            )
                            .addStatement(
                                "return ${"UiMapperModel"}(" +
                                        getClassField(classDeclaration) +
                                        "\n)"
                            ).build()
                    )
                    .addFunction(
                        FunSpec
                            .builder("mapFrom")
                            .returns(
                                ClassName(
                                    "dev.yamg.core.model",
                                    "DomainMapperModel"
                                )
                            )
                            .addStatement(
                                "return ${"DomainMapperModel"}(" +
                                        getClassField(classDeclaration) +
                                        "\n)"
                            ).build()
                    ).build()
            ).build()
        }.build()
        mapperClass.writeTo(codeGenerator, Dependencies(true))
    }

}
