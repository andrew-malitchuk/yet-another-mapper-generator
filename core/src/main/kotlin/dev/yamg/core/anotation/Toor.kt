package dev.yamg.core.anotation

import kotlin.reflect.KClass


@MustBeDocumented
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
public annotation class ExtensiveModel(
    val type: KClass<*>,
    val name: String = ""
) {

    public companion object {
        /** The name of the `type` parameter. */
        public const val PARAM_TYPE: String = "type"

        /** The name of the `name` parameter. */
        public const val PARAM_NAME: String = "name"
    }
}