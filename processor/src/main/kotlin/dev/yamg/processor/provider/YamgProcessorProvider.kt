package dev.yamg.processor.provider

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import dev.yamg.processor.processing.YamgSymbolProcessor

class YamgProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        YamgSymbolProcessor(
            codeGenerator = environment.codeGenerator,
            logger = environment.logger
        )

}