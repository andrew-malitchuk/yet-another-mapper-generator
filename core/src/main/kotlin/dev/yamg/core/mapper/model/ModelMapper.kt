package dev.yamg.core.mapper.model

import dev.yamg.core.model.BaseMapperModel


interface ModelMapper<From : BaseMapperModel, To : BaseMapperModel> {
    fun mapTo(from: From): To
    fun mapFrom(to: To): From
}
