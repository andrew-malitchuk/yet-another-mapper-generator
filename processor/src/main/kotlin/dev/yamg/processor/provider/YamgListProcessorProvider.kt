package dev.yamg.processor.provider

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import dev.yamg.processor.processing.YamgListSymbolProcessor

class YamgListProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        YamgListSymbolProcessor(
            codeGenerator = environment.codeGenerator,
            options = environment.options,
            logger = environment.logger
        )

}
