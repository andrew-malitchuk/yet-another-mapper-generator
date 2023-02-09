package dev.yamg.processor.processing

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.writeTo
import dev.yamg.core.anotation.Foobar
import dev.yamg.core.model.DomainMapperModel
import dev.yamg.core.model.UiMapperModel
import java.io.OutputStream

class YamgSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {


    private var className: String? = null
    private var targetClassName: String? = null


    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            Foobar::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!symbols.iterator().hasNext()) return emptyList()
        symbols.forEach { it.accept(Visitor(resolver, logger), Unit) }
        return symbols.filterNot { it.validate() }.toList()
    }

    operator fun OutputStream.plusAssign(str: String) {
        this.write(str.toByteArray())
    }

    inner class Visitor(
        private val resolver: Resolver,
        private val logger: KSPLogger
    ) :
        KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {


            val parentClass = DomainMapperModel::class
            val targetClass = UiMapperModel::class


            val from = classDeclaration.superTypes.first()
                .resolve().arguments.first()?.type?.resolve()?.declaration
            val to = classDeclaration.superTypes.first()
                .resolve().arguments.elementAtOrNull(1)?.type?.resolve()?.declaration

            val fileName = "_${from}To${to}ModelMapper"
//
//            if (classDeclaration.classKind != ClassKind.INTERFACE) {
//                logger.error("Only interface can be annotated with @Function", classDeclaration)
//                return
//            }

            val foo = FileSpec.builder("dev.yamg.app", "$fileName")
                .addType(
                    TypeSpec
                        .classBuilder(fileName)
                        .addSuperinterface(
                            ClassName(
                                "dev.yamg.core.mapper.model", "DomainUiModelMapper"
                            ).parameterizedBy(
                                ClassName(
                                    "dev.yamg.core.model", "DomainMapperModel"
                                ),
                                ClassName(
                                    "dev.yamg.core.model", "UiMapperModel"
                                )
                            )
                        )
                        .addFunction(
                            FunSpec
                                .builder("mapTo")
                                .addModifiers(KModifier.OVERRIDE)
                                .addParameter(
                                    "from",
                                    ClassName(
                                        "dev.yamg.core.model", "DomainMapperModel"
                                    )
                                )
                                .returns(
                                    ClassName(
                                        "dev.yamg.core.model", "UiMapperModel"
                                    ),
                                )
                                .addStatement(
                                    "return Any()"
                                )
                                .build()
                        )
                        .addFunction(
                            FunSpec
                                .builder("mapFrom")
                                .addModifiers(KModifier.OVERRIDE)
                                .addParameter(
                                    "to",
                                    ClassName(
                                        "dev.yamg.core.model", "UiMapperModel"
                                    )
                                )
                                .returns(
                                    ClassName(
                                        "dev.yamg.core.model", "DomainMapperModel"
                                    ),
                                )
                                .addStatement(
                                    "return Any()"
                                )
                                .build()
                        ).build()
                ).build()


            foo.writeTo(codeGenerator, Dependencies(true))


//            val bar = FileSpec.builder("dev.yamg.app", "${from}Ext")
//                .addFunction(
//                    FunSpec
//                        .builder("toFoo")
//                        .receiver(
//                            ClassName(
//                                "",
//                                from.toString()
//                            )
//                        )
//                        .returns(targetClass)
//                        .addStatement("var a = 1")
//                        .addStatement("return UiMapperModel()")
//                        .build()
//                )
//                .build()
//
//            bar.writeTo(codeGenerator, Dependencies(true))

        }

        override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        }

        override fun visitTypeArgument(typeArgument: KSTypeArgument, data: Unit) {
        }
    }

    //

    //


}