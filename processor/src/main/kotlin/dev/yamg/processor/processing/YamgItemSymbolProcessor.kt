package dev.yamg.processor.processing

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import dev.yamg.core.anotation.YamgItem

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

    }


    companion object {

        const val PARAM_MAPPER_PACKAGE_NAME = "mappersPackageName"

        const val DEFAULT_EXTENSION_METHOD_NAME = "to%s"
        const val DEFAULT_MAPPERS_PACKAGE_NAME = "dev.yamg.app"
    }
}