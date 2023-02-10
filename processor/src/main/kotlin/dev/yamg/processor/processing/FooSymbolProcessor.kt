package dev.yamg.processor.processing

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.symbol.impl.binary.KSAnnotationDescriptorImpl
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.yamg.core.anotation.ExtensiveModel
import dev.yamg.core.anotation.ExtensiveSealed

class FooSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    private lateinit var startupType: KSType

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            ExtensiveSealed::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!symbols.iterator().hasNext()) return emptyList()
        symbols.forEach { it.accept(FooVisitor(resolver, logger), Unit) }
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class FooVisitor(
        private val resolver: Resolver,
        private val logger: KSPLogger
    ) : KSVisitorVoid() {

        @OptIn(KspExperimental::class)
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {

            val res=foo(classDeclaration)

//            val className = ClassName.bestGuess("${res[0].type.toString()}.${res[0].name}")

            val parentClass = classDeclaration.toClassName().toString()

            val fileName = "${parentClass}Ext"

            val bar = FileSpec.builder("dev.yamg.app", fileName)
                .addFunction(
                    FunSpec
                        .builder("toFoo")
                        .receiver(
                            ClassName(
//                                targetClass.java.packageName,
//                                targetClass.simpleName.toString(),
//                                className.packageName,
//                                className.simpleName
                                res[0].type.toString(),
                                res[0].name
                            )
                        )
//                        .returns(targetClass)
                        .addStatement("var a = 1")
                        .addStatement("return UiMapperModel()")
                        .build()
                )
                .build()

            bar.writeTo(codeGenerator, Dependencies(true))
        }

    }

    //
    fun foo(declaration:KSClassDeclaration): List<ExtensiveModelBag> {
        val arguments = declaration.annotations.first {
            it.annotationType.resolve().declaration.qualifiedName?.asString() == ExtensiveSealed::class.qualifiedName
        }.arguments.first { it.name?.asString() == ExtensiveSealed.PARAM_MODELS }.value

        // Extract a list of KSType from the class type of the array of `ExtensiveModel`.
        val modelsKSTypesDescriptor =
            (arguments as ArrayList<*>).map { it as KSAnnotationDescriptorImpl }
        val models = modelsKSTypesDescriptor.map { kSAnnotationDescriptor ->
            val name = kSAnnotationDescriptor.arguments.first { KSValueArgument ->
                KSValueArgument.name?.asString() == ExtensiveModel.PARAM_NAME
            }.value as String

            val type = kSAnnotationDescriptor.arguments.first { KSValueArgument ->
                KSValueArgument.name?.asString() == ExtensiveModel.PARAM_TYPE
            }.value as KSType
            ExtensiveModelBag(name = name, type = type)
        }.distinct()
        return models
    }

    data class ExtensiveModelBag(
        val name: String,
        val type: KSType
    )
    //


}

