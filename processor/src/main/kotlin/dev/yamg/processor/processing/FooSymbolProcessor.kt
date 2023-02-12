package dev.yamg.processor.processing

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.yamg.core.anotation.Foo
import dev.yamg.processor.extensions.getClassFromAnnotation

class FooSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    private lateinit var startupType: KSType

    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            Foo::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!symbols.iterator().hasNext()) return emptyList()

        //
//        val foo = resolver.getSymbolsWithAnnotation("dev.yamg.core.anotation.Foo")
//            .map { ksAnnotated ->
//                val args = ksAnnotated.annotations.single {
//                    it.shortName.asString() == "Foo" && it.annotationType.resolve().declaration.qualifiedName?.asString() == "dev.yamg.core.anotation.Foo"
//                }.arguments
//                val consumerType =
//                    args.single { it.name?.asString() == "targetClass" }.value as KSType
//                val consumerDeclaration = consumerType.declaration as KSClassDeclaration // etc
//                return@map consumerDeclaration
//            }
//        logger.error("foo: ${(foo.first().qualifiedName!!.getQualifier())}")
//        logger.error("foo: ${(foo.first().qualifiedName!!.getShortName())}")
//        val clazz =
//            foo.first().qualifiedName!!.getQualifier() +"."+ foo.first().qualifiedName!!.getShortName()
//        val className = ClassName.bestGuess(clazz)
//        logger.error("bar: $className")
        //
        symbols.forEach { it.accept(FooVisitor(resolver, logger), Unit) }
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class FooVisitor(
        private val resolver: Resolver,
        private val logger: KSPLogger
    ) : KSVisitorVoid() {

        @OptIn(KspExperimental::class)
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {


            //
            val targetClass = resolver.getClassFromAnnotation(Foo::class, "targetClass", logger)
            val clazz =
                foo?.qualifiedName!!.getQualifier() + "." + foo?.qualifiedName!!.getShortName()
            val className = ClassName.bestGuess(clazz)
            logger.error("bar: $className")
            //

            val parentClass = classDeclaration.toClassName().toString()
            val fileName = "${parentClass}Ext"
            val returns = ClassName.bestGuess("dev.yamg.core.model.UiMapperModel")

            val bar = FileSpec.builder("dev.yamg.app", fileName)
                .addFunction(
                    FunSpec
                        .builder("toFoo")
                        .receiver(
                            ClassName(
                                className.packageName,
                                className.simpleName
                            )
                        )
                        .returns(returns)
                        .addStatement("var a = 1")
                        .addStatement("return UiMapperModel()")
                        .build()
                )
                .build()

            bar.writeTo(codeGenerator, Dependencies(true))
        }

    }


}

