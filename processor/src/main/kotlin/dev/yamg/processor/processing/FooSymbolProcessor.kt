package dev.yamg.processor.processing

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.yamg.core.anotation.Foo
import dev.yamg.processor.extensions.getClassFromAnnotation
import dev.yamg.processor.extensions.getClassName
import dev.yamg.processor.extensions.getFieldValueFromAnnotation

// TODO: pass package for ksp-gen files via arguments from build.gradle
class FooSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            Foo::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!symbols.iterator().hasNext()) return emptyList()
        symbols.forEach { it.accept(FooVisitor(resolver, logger), Unit) }
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class FooVisitor(
        private val resolver: Resolver,
        private val logger: KSPLogger
    ) : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            //
            val parentClass = classDeclaration.toClassName()
            val targetClass = resolver.getClassFromAnnotation(Foo::class, "targetClass", logger)

            if (targetClass == null) {
                logger.exception(NullPointerException("Something wrong with parsing"))
            }

            val methodName = classDeclaration.getFieldValueFromAnnotation(Foo::class, "methodName")?.toString()
                ?: String.format(DEFAULT_EXTENSION_METHOD_NAME, targetClass?.toClassName()?.simpleName)
            logger.error("methodName: $methodName")

            val fileName = "${parentClass}Ext"

            val className = targetClass?.getClassName()
            //

            val extensionMethod = FileSpec.builder("dev.yamg.app", fileName)
                .addFunction(
                    FunSpec
                        .builder(methodName)
                        .receiver(
                            ClassName(
                                className!!.packageName,
                                className!!.simpleName
                            )
                        )
//                        .returns(returns)
                        .addStatement("var a = 1")
                        .addStatement("return UiMapperModel()")
                        .build()
                )
                .build()

            extensionMethod.writeTo(codeGenerator, Dependencies(true))
        }

    }

    companion object {
        const val DEFAULT_EXTENSION_METHOD_NAME = "to%s"
    }


}

