package dev.yamg.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.writeTo
import dev.yamg.core.mapper.model.DomainUiModelMapper
import dev.yamg.core.model.DomainMapperModel
import dev.yamg.core.model.UiMapperModel

class YamgItemGenerator(
//    private val classDeclaration: KSClassDeclaration,
    private val codeGenerator: CodeGenerator,
//    private val methodName: String,
    private val fileName: String,
//    private val parentClass: ClassName,
//    private val className: ClassName,
//    private val excludeFields: List<String>? = null,
    private val packageName: String,
//    private val logger: KSPLogger
) {

    fun build() {
        val mapperClass = FileSpec.builder(packageName, fileName).apply {
            this.addType(
                TypeSpec.classBuilder("foobar")
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
                    .build()
            )
        }.build()
        mapperClass.writeTo(codeGenerator, Dependencies(true))
    }

}


class FooDomainUiMapper : DomainUiModelMapper<DomainMapperModel, UiMapperModel> {

    override fun mapTo(from: DomainMapperModel): UiMapperModel {
        TODO("Not yet implemented")
    }

    override fun mapFrom(to: UiMapperModel): DomainMapperModel {
        TODO("Not yet implemented")
    }
}