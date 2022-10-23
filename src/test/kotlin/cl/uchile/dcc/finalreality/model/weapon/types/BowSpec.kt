package cl.uchile.dcc.finalreality.model.weapon.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val BOW1_NAME = "BOW1"
private const val BOW1_DAMAGE = 10
private const val BOW1_WEIGHT = 15
private const val BOW2_NAME = "BOW2"
private const val BOW2_DAMAGE = 12
private const val BOW2_WEIGHT = 30

class BowSpec : FunSpec ({
    lateinit var Bow1: Bow
    lateinit var Bow2: Bow

    beforeEach {
        Bow1 = Bow(BOW1_NAME, BOW1_DAMAGE, BOW1_WEIGHT)
        Bow2 = Bow(BOW2_NAME, BOW2_DAMAGE, BOW2_WEIGHT)
    }

    test("toString must return the Bow description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Bow3 = Bow(name, damage, weight)
            Bow3.toString() shouldBe "Bow {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Bows with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Bow31 = Bow(name, damage, weight)
            val Bow32 = Bow(name, damage, weight)
            Bow31 shouldNotBeSameInstanceAs Bow32
            Bow31 shouldBe Bow32
        }
    }

    test("Two Bows with different parameters are not equals") {
        Bow1 shouldNotBeSameInstanceAs Bow2
        Bow1 shouldNotBe Bow2
    }
})