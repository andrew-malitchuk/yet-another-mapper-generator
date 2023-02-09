package dev.yamg.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.yamg.core.model.UiMapperModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}

data class FooUiModel(var foobar: String) : UiMapperModel



