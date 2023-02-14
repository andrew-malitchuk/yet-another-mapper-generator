package dev.yamg.processor.extensions

import com.google.devtools.ksp.symbol.KSValueParameter


@Suppress("UNCHECKED_CAST")
fun <T> KSValueParameter.getAnnotation(fieldName: String): T? {
    return annotations.firstOrNull()?.arguments?.first {
        it.name?.getShortName() == fieldName
    }?.value as? T
}