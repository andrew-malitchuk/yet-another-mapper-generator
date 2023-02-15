package dev.yamg.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.yamg.app.ui.FooUiModel
import dev.yamg.core.anotation.YamgExt
import dev.yamg.core.model.DomainMapperModel
import io.demo.foobar.toFooUiModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fooDomain = FooDomain("",1)
        fooDomain.toFooUiModel()

        Log.d("foo",fooDomain::class.members.toString())

    }
}

@YamgExt(
    targetClass = FooUiModel::class,
//    methodName = "toUiModel",
//    excludeFields = ["fieldTwo"]
)
data class FooDomain(
//    @YamgFieldName("foobarField")
    val fieldOne: String?,
    val fieldTwo: Int?,
) : DomainMapperModel


