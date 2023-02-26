package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.ksp.writeTo

class YamgItemGenerator(
    private val classDeclaration: KSClassDeclaration,
    private val codeGenerator: CodeGenerator,
    private val fromClass: ClassName,
    private val toClass: ClassName,
//    private val methodName: String,
//    private val parentClass: ClassName,
    private val className: String,
//    private val excludeFields: List<String>? = null,
    private val packageName: String,
    private val mapperSupperClass: ClassName,
    private val logger: KSPLogger
) {

    fun build() {

        val mapperClass = FileSpec.builder(packageName, className).apply {
            this.addType(
                TypeSpec.classBuilder(className)
                    .addSuperinterface(
                        mapperSupperClass
                            .plusParameter(
                                fromClass
                            ).plusParameter(
                                toClass
                            )
                    )
                    .addFunction(
                        FunSpec
                            .builder("mapTo")
                            .addModifiers(KModifier.OVERRIDE)
                            .addParameter(
                                ParameterSpec(
                                    "from",
                                    fromClass
                                )
                            )
                            .returns(
                                toClass
                            )
                            .addStatement(
                                "return ${toClass.simpleName}(" +
                                        getClassField(classDeclaration, predicate = "from") +
                                        "\n)"
                            ).build()
                    )
                    .addFunction(
                        FunSpec
                            .builder("mapFrom")
                            .addModifiers(KModifier.OVERRIDE)
                            .addParameter(
                                ParameterSpec(
                                    "to",
                                    toClass
                                )
                            )
                            .returns(
                                fromClass
                            )
                            .addStatement(
                                "return ${fromClass.simpleName}(" +
                                        getClassField(classDeclaration, predicate = "to") +
                                        "\n)"
                            ).build()
                    ).build()
            ).build()
        }.build()
        mapperClass.writeTo(codeGenerator, Dependencies(true))
    }

}

//class FooDomainUiMapper : DomainUiModelMapper<DomainMapperModel, UiMapperModel> {
//
//    override fun mapTo(from: DomainMapperModel): UiMapperModel {
//        TODO("Not yet implemented")
//    }
//
//    override fun mapFrom(to: UiMapperModel): DomainMapperModel {
//        TODO("Not yet implemented")
//    }
//}