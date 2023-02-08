package dev.yamg.core.mapper.list

import dev.yamg.core.model.DataMapperModel
import dev.yamg.core.model.DomainMapperModel

interface DataDomainListMapper<From : DataMapperModel, To : DomainMapperModel> :
    BaseListMapper<From, To>
