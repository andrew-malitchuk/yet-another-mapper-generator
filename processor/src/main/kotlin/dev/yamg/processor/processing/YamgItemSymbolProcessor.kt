package dev.yamg.processor.processing

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.asClassName
import dev.yamg.core.anotation.YamgItem
import dev.yamg.core.mapper.model.ModelMapper
import dev.yamg.processor.extensions.getClassFromAnnotation
import dev.yamg.processor.extensions.getClassName
import dev.yamg.processor.extensions.getFieldValueFromAnnotation
import dev.yamg.processor.generator.YamgItemGenerator

class YamgItemSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {

    var mappersPackageName: String = DEFAULT_MAPPERS_PACKAGE_NAME

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(
            YamgItem::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!symbols.iterator().hasNext()) return emptyList()

        mappersPackageName = options[PARAM_MAPPER_PACKAGE_NAME] ?: DEFAULT_MAPPERS_PACKAGE_NAME

        symbols.forEach { it.accept(YamgItemVisitor(resolver, logger), Unit) }
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class YamgItemVisitor(
        private val resolver: Resolver,
        private val logger: KSPLogger
    ) : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            val fromClass = resolver.getClassFromAnnotation(
                YamgItem::class,
                YamgItem.FROM_CLASS,
                logger
            )
            val toClass = resolver.getClassFromAnnotation(
                YamgItem::class,
                YamgItem.TO_CLASS,
                logger
            )

            val mapperSuperClass = ModelMapper::class.asClassName()

            if (fromClass == null || toClass == null ) {
                logger.exception(NullPointerException("Something wrong with parsing"))
            }

            var className =
                (classDeclaration.getFieldValueFromAnnotation(
                    YamgItem::class,
                    YamgItem.CLASS_NAME
                )?.value as? String?)

            if (className.isNullOrEmpty()) {
                className = String.format(
                    DEFAULT_CLASS_NAME,
                    fromClass?.getClassName()?.simpleName,
                    toClass?.getClassName()?.simpleName
                )
            }

            val yamgItemGenerator = YamgItemGenerator(
                classDeclaration,
                codeGenerator,
                fromClass = fromClass?.getClassName()!!,
                toClass = toClass?.getClassName()!!,
                className,
                mappersPackageName,
                mapperSuperClass,
                logger
            )
            yamgItemGenerator.build()
        }

    }


    companion object {

        const val PARAM_MAPPER_PACKAGE_NAME = "mappersPackageName"

        const val DEFAULT_CLASS_NAME = "%s%sMapper"
        const val DEFAULT_MAPPERS_PACKAGE_NAME = "dev.yamg.app"
    }
}