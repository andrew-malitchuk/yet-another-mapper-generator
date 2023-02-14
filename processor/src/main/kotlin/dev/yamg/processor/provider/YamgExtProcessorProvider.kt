package dev.yamg.processor.provider

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import dev.yamg.processor.processing.YamgExtSymbolProcessor

class YamgExtProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        YamgExtSymbolProcessor(
            codeGenerator = environment.codeGenerator,
            options=environment.options,
            logger = environment.logger
        )

}
