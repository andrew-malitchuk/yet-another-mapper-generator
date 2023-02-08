package dev.yamg.core.mapper.list

import dev.yamg.core.model.DomainMapperModel
import dev.yamg.core.model.UiMapperModel


interface DomainUiListMapper<From : DomainMapperModel, To : UiMapperModel> :
    BaseListMapper<From, To>
