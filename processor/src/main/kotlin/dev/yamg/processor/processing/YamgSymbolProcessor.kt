package dev.yamg.processor.processing

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import dev.yamg.core.anotation.Foobar
import java.io.OutputStream

class YamgSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val mapperDeclarations = resolver.getSymbolsWithAnnotation(
            Foobar::class.java.name
        ).filterIsInstance<KSClassDeclaration>().distinct()
        if (!mapperDeclarations.iterator().hasNext()) return emptyList()

        val file: OutputStream = codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = "dev.yamg.processor",
            fileName = "GeneratedFunctions"
        )
        file += "package com.yamg.lib\n"

        mapperDeclarations.forEach { it.accept(Visitor(file), Unit) }

        file.close()
        val unableToProcess = mapperDeclarations.filterNot { it.validate() }.toList()
        return unableToProcess

    }

    operator fun OutputStream.plusAssign(str: String) {
        this.write(str.toByteArray())
    }

    inner class Visitor(private val file: OutputStream) : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            if (classDeclaration.classKind != ClassKind.INTERFACE) {
                logger.error("Only interface can be annotated with @Function", classDeclaration)
                return
            }

            // Getting the @Function annotation object.
            val annotation: KSAnnotation = classDeclaration.annotations.first {
                it.shortName.asString() == Foobar::class.simpleName
            }

            // Getting the 'name' argument object from the @Function.
            val nameArgument: KSValueArgument = annotation.arguments
                .first { arg -> arg.name?.asString() == "foobar" }

            // Getting the value of the 'name' argument.
            val functionName = nameArgument.value as String

            // Getting the list of member properties of the annotated interface.
            val properties: Sequence<KSPropertyDeclaration> = classDeclaration.getAllProperties()
                .filter { it.validate() }

            // Generating function signature.
            file += "\n"
            if (properties.iterator().hasNext()) {
                file += "fun $functionName(\n"

                // Iterating through each property to translate them to function arguments.
                properties.forEach { prop ->
                    visitPropertyDeclaration(prop, Unit)
                }
                file += ") {\n"

            } else {
                // Otherwise, generating function with no args.
                file += "fun $functionName() {\n"
            }

            // Generating function body
            file += "    println(\"Hello from $functionName\")\n"
            file += "}\n"
        }

        override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        }

        override fun visitTypeArgument(typeArgument: KSTypeArgument, data: Unit) {
        }
    }

}