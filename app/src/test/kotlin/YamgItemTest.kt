import dev.yamg.app.FoobarDomain
import dev.yamg.app.FoobarUi
import io.demo.foobar.FoobarDomainFoobarUiMapper
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf


class YamgItemTest : FunSpec({

    lateinit var source: FoobarDomain
    lateinit var expectedResult: FoobarUi
    lateinit var mappingResult: FoobarUi

    var mapper = FoobarDomainFoobarUiMapper()

    beforeTest {
        source = FoobarDomain(
            "field-value",
        )
        expectedResult = FoobarUi(
            "field-value",
        )
        mappingResult = mapper.mapTo(source)

    }

    test("shouldBeEqualToComparingFields") {
        mappingResult shouldBeEqualToComparingFields expectedResult
    }

    test("shouldBe") {
        mappingResult shouldBe expectedResult
    }

    test("shouldBeTypeOf `FooUiModel`") {
        mappingResult.shouldBeTypeOf<FoobarUi>()
    }

    test("shouldBe the same class") {
        mappingResult::class.shouldBe(expectedResult::class)
    }

    test("should contain the same members") {
        @Suppress("NO_REFLECTION_IN_CLASS_PATH")
        mappingResult::class.members.shouldBe(expectedResult::class.members)
    }

    test("`toString()` results should be the same") {
        mappingResult.toString().shouldBe(expectedResult.toString())
    }


})