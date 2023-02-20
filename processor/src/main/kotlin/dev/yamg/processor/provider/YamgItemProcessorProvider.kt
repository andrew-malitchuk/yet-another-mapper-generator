package dev.yamg.processor.provider

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class YamgItemProcessorProvider  : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        YamgItemSymbolProcessor(
            codeGenerator = environment.codeGenerator,
            options=environment.options,
            logger = environment.logger
        )

}
