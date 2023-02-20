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
import dev.yamg.core.anotation.YamgExt
import dev.yamg.core.anotation.YamgExt.Companion.EXCLUDE_FIELDS
import dev.yamg.core.anotation.YamgExt.Companion.METHOD_NAME
import dev.yamg.core.anotation.YamgExt.Companion.TARGET_CLASS
import dev.yamg.processor.extensions.getClassFromAnnotation
import dev.yamg.processor.extensions.getClassName
import dev.yamg.processor.extensions.getFieldValueFromAnnotation
import dev.yamg.processor.generator.YamgExtGenerator

// TODO: pass package for ksp-gen files via arguments from build.gradle
class YamgExtSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {

    var mappersPackageName:String=DEFAULT_MAPPERS_PACKAGE_NAME

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            YamgExt::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!symbols.iterator().hasNext()) return emptyList()

        mappersPackageName = options[PARAM_MAPPER_PACKAGE_NAME]?:DEFAULT_MAPPERS_PACKAGE_NAME

        symbols.forEach { it.accept(YamgExtVisitor(resolver, logger), Unit) }
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class YamgExtVisitor(
        private val resolver: Resolver,
        private val logger: KSPLogger
    ) : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            val parentClass = classDeclaration.toClassName()
            val targetClass = resolver.getClassFromAnnotation(YamgExt::class, TARGET_CLASS, logger)
            val className = targetClass?.getClassName()

            if (targetClass == null || className == null) {
                logger.exception(NullPointerException("Something wrong with parsing"))
            }
            var methodName =
                (classDeclaration.getFieldValueFromAnnotation(
                    YamgExt::class,
                    METHOD_NAME
                )?.value as? String?)

            if (methodName.isNullOrEmpty()){
                methodName= String.format(
                    DEFAULT_EXTENSION_METHOD_NAME,
                    targetClass?.toClassName()?.simpleName
                )
            }

            @Suppress("UNCHECKED_CAST") val excludeFields =
                (classDeclaration.getFieldValueFromAnnotation(
                    YamgExt::class,
                    EXCLUDE_FIELDS
                )?.value as List<String>)

            val fileName = "${parentClass.simpleName}Ext"
            val yamgExtGenerator = YamgExtGenerator(
                classDeclaration,
                codeGenerator,
                methodName,
                fileName,
                parentClass,
                className!!,
                excludeFields,
                mappersPackageName,
                logger
            )
            yamgExtGenerator.build()
        }
    }

    companion object {

        const val PARAM_MAPPER_PACKAGE_NAME="mappersPackageName"

        const val DEFAULT_EXTENSION_METHOD_NAME = "to%s"
        const val DEFAULT_MAPPERS_PACKAGE_NAME = "dev.yamg.app"
    }


}

