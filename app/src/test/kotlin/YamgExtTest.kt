import dev.yamg.app.FooDomain
import dev.yamg.app.ui.FooUiModel
import io.demo.foobar.toFooUiModel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf


class YamgExtTest : FunSpec({

    lateinit var source: FooDomain
    lateinit var expectedResult: FooUiModel
    lateinit var mappingResult: FooUiModel

    beforeTest {
        source = FooDomain(
            "field-value",
            10
        )
        expectedResult = FooUiModel(
            "field-value",
            10
        )
        mappingResult = source.toFooUiModel()

    }

    test("shouldBeEqualToComparingFields") {
        mappingResult shouldBeEqualToComparingFields expectedResult
    }

    test("shouldBe") {
        mappingResult shouldBe expectedResult
    }

    test("shouldBeTypeOf FooUiModel"){
        mappingResult.shouldBeTypeOf<FooUiModel>()
    }

    test("shouldHaveAnnotation"){
        mappingResult::class.shouldBe(expectedResult::class)
    }

    test("foo"){
        mappingResult::class.members.shouldBe(expectedResult::class.members)
    }

    test("bar"){
        mappingResult.toString().shouldBe(expectedResult.toString())
    }

})