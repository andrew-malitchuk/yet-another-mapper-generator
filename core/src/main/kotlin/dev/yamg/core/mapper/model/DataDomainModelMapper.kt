package dev.yamg.core.mapper.model

import dev.yamg.core.model.DataMapperModel
import dev.yamg.core.model.DomainMapperModel


interface DataDomainModelMapper<From : DataMapperModel, To : DomainMapperModel> :
    BaseModelMapper<From, To>