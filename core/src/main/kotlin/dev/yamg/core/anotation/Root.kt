package dev.yamg.core.anotation

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
public annotation class ExtensiveSealed(
    val models: Array<ExtensiveModel> = []
) {

    public companion object {
        /** The name of the `models` parameter. */
        public const val PARAM_MODELS: String = "models"
    }
}