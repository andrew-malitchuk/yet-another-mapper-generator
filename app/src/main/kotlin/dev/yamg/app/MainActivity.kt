package dev.yamg.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.yamg.app.ui.FooUiModel
import dev.yamg.core.anotation.YamgExt
import dev.yamg.core.anotation.YamgItem
import dev.yamg.core.model.BaseMapperModel
import dev.yamg.core.model.DomainMapperModel
import io.demo.foobar.toFooUiModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fooDomain = FooDomain("", 1, false, 1)
        fooDomain.toFooUiModel()

        Log.d("foo", fooDomain::class.members.toString())

    }
}

@YamgExt(
    targetClass = FooUiModel::class,
)
data class FooDomain(
    val fieldOne: String?,
    val fieldTwo: Int?,
    val fieldThree: Boolean,
    val fieldFour: Short?,
) : DomainMapperModel


@YamgItem(
    fromClass = From::class,
    toClass = To::class
)
data class Foo(val a: String)


data class From(val a:String):BaseMapperModel
data class To(val a:String):BaseMapperModel