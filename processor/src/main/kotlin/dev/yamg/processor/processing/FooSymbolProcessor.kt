package dev.yamg.processor.processing

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ksp.toClassName
import dev.yamg.core.anotation.Foo
import dev.yamg.processor.extensions.getClassFromAnnotation
import dev.yamg.processor.extensions.getClassName
import dev.yamg.processor.extensions.getFieldValueFromAnnotation
import dev.yamg.processor.generator.FooGenerator

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
            val parentClass = classDeclaration.toClassName()
            val targetClass = resolver.getClassFromAnnotation(Foo::class, "targetClass", logger)
            val className = targetClass?.getClassName()

            if (targetClass == null || className == null) {
                logger.exception(NullPointerException("Something wrong with parsing"))
            }
            val methodName =
                (classDeclaration.getFieldValueFromAnnotation(
                    Foo::class,
                    "methodName"
                )?.value as? String?)
                    ?: String.format(
                        DEFAULT_EXTENSION_METHOD_NAME,
                        targetClass?.toClassName()?.simpleName
                    )

            val excludeFields = (classDeclaration.getFieldValueFromAnnotation(
                Foo::class,
                "excludeFields"
            )?.value as List<String>)

//            logger.error("foo: ${excludeFields.toString()}")

            val fileName = "${parentClass.simpleName}Ext"
            val fooGenerator = FooGenerator(
                classDeclaration,
                codeGenerator,
                methodName,
                fileName,
                parentClass,
                className!!,
                excludeFields,
                logger
            )
            fooGenerator.build()
        }
    }

    companion object {
        const val DEFAULT_EXTENSION_METHOD_NAME = "to%s"
    }


}

