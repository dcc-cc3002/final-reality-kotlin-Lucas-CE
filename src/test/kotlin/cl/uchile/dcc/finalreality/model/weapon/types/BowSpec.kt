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

class BowSpec : FunSpec({
    lateinit var bow1: Bow
    lateinit var bow2: Bow

    beforeEach {
        bow1 = Bow(BOW1_NAME, BOW1_DAMAGE, BOW1_WEIGHT)
        bow2 = Bow(BOW2_NAME, BOW2_DAMAGE, BOW2_WEIGHT)
    }

    test("toString must return the Bow description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val bow3 = Bow(name, damage, weight)
            bow3.toString() shouldBe "Bow {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Bows with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val bow31 = Bow(name, damage, weight)
            val bow32 = Bow(name, damage, weight)
            bow31 shouldNotBeSameInstanceAs bow32
            bow31 shouldBe bow32
        }
    }

    test("Two Bows with different parameters are not equals") {
        bow1 shouldNotBeSameInstanceAs bow2
        bow1 shouldNotBe bow2
    }

    test("Two Bows with the same parameters have the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(20), Arb.positiveInt(50)) {
            name, damage, weight ->
            val bow31 = Bow(name, damage, weight)
            val bow32 = Bow(name, damage, weight)
            bow31.hashCode() shouldBe bow32.hashCode()
        }
    }

    test("Two Bows with different parameters have not the same hash code") {
        bow1.hashCode() shouldNotBe bow2.hashCode()
    }
})
