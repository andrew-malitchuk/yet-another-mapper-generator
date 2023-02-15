import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.startWith

class MyTests : StringSpec({
    "length should return size of string" {
        "hello".length shouldBe 5
    }
    "startsWith should test for a prefix" {
        "world" should startWith("wor")
    }
})

class MyFirstTestClass : FunSpec({

    test("my first test") {
        1 + 2 shouldBe 3
    }

    test("is it `foobar`?"){
        "foobar" shouldBe "bar"
    }

})

class NestedTestExamples : DescribeSpec({

    describe("an outer test") {

        it("an inner test") {
            1 + 2 shouldNotBe  3
        }

        it("an inner test too!") {
            3 + 4 shouldBe 7
        }
    }

})

class MyFeatureTests : FeatureSpec({
    feature("the can of coke") {
        scenario("should be fizzy when I shake it") {
            // test here
        }
        scenario("and should be tasty") {
            // test here
        }
    }
})