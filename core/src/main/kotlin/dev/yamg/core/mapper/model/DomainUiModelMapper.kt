package dev.yamg.core.mapper.model

import dev.yamg.core.model.DomainMapperModel
import dev.yamg.core.model.UiMapperModel


interface DomainUiModelMapper<From : DomainMapperModel, To : UiMapperModel> :
    BaseModelMapper<From, To>