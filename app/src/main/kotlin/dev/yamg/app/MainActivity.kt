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
    }
}

@Foo(
    targetClass = FooUiModel::class,
    methodName = "toUiModel"
)
data class FooDomain(val foo: String) : DomainMapperModel


