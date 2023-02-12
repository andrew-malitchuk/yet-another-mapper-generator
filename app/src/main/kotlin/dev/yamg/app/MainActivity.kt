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

//data class FooUiModel(var foobar: String) : UiMapperModel
//
//
//@dev.yamg.core.anotation.Foobar(
//    targetClass = DomainMapperModel::class
//)
//abstract class Foobar : DomainUiModelMapper<DomainMapperModel, UiMapperModel>



@Foo(
//    targetClass = FooDomainModel::class
    targetClass = FooUiModel::class
)
//@ExtensiveSealed(
//    models = [
//        ExtensiveModel(type=FooUiModel::class),
//    ]
//)
data class FooDomain(val foo: String):DomainMapperModel


