import dev.yamg.app.FooDomain
import dev.yamg.app.ui.FooUiModel
import io.kotest.core.spec.style.FunSpec


class YamgItemTest : FunSpec({

    lateinit var source: FooDomain
    lateinit var expectedResult: FooUiModel
    lateinit var mappingResult: FooUiModel

    var mapper = FoobarDomainFoobarUiMapper()

    beforeTest {
        source = FooDomain(
            "field-value",
            10,
            true,
            100
        )
        expectedResult = FooUiModel(
            "field-value",
            10,
            true,
            100
        )
//        mappingResult = mapper.

    }





})