package dev.yamg.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yamg.lib.myAmazingFunction
import dev.yamg.core.anotation.Foobar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myAmazingFunction()
    }


//    @Yamg(
//        from = DomainMapperModel::class,
//        to = UiMapperModel::class
//    )
//    interface Foobar : DomainUiModelMapper<DomainMapperModel, UiMapperModel>

    @Foobar("myAmazingFunction")
    interface Foo
}