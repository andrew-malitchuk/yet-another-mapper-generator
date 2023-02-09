package dev.yamg.processor.processing

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*
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


//            if (classDeclaration.classKind != ClassKind.INTERFACE) {
//                logger.error("Only interface can be annotated with @Function", classDeclaration)
//                return
//            }


            val foo = FileSpec.builder("dev.yamg.app", "${parentClass}Ext")
                .addFunction(
                    FunSpec
                        .builder("toFoo")
                        .receiver(parentClass)
                        .returns(targetClass)
                        .addStatement("var a = 1")
                        .addStatement("return UiMapperModel()")
                        .build()
                )
                .build()

            foo.writeTo(codeGenerator, Dependencies(true))

        }

        override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        }

        override fun visitTypeArgument(typeArgument: KSTypeArgument, data: Unit) {
        }
    }

    //

    //


}