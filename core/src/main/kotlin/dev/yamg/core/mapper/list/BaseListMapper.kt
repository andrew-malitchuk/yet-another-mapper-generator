package dev.yamg.core.mapper.list

import dev.yamg.core.model.BaseMapperModel

interface BaseListMapper<From : BaseMapperModel, To : BaseMapperModel> {
    fun mapTo(from: List<From>): List<To>
    fun mapFrom(to: List<To>): List<From>
}
