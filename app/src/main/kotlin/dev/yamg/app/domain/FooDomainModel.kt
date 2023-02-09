package dev.yamg.app.domain

import dev.yamg.app.FooUiModel
import dev.yamg.core.anotation.Foobar
import dev.yamg.core.model.DomainMapperModel

@Foobar( targetClass = FooUiModel::class )
data class FooDomainModel(var foobar: String) : DomainMapperModel