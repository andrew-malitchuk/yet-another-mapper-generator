package dev.yamg.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.yamg.app.ui.FooUiModel
import dev.yamg.core.anotation.Foo
import dev.yamg.core.model.DomainMapperModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fooDomain = FooDomain("","")
        fooDomain.toUiModel()
    }
}

@Foo(
    targetClass = FooUiModel::class,
    methodName = "toUiModel",
    excludeFields = ["fieldTwo"]
)
data class FooDomain(
//    @YamgFieldName("foobarField")
    val fieldOne: String,
    val fieldTwo: String,
) : DomainMapperModel


