package cl.uchile.dcc.finalreality.model.weapon.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val KNF1_NAME = "KNF1"
private const val KNF1_DAMAGE = 10
private const val KNF1_WEIGHT = 15
private const val KNF2_NAME = "KNF2"
private const val KNF2_DAMAGE = 12
private const val KNF2_WEIGHT = 30

class KnifeSpec : FunSpec ({
    lateinit var Knife1: Knife
    lateinit var Knife2: Knife

    beforeEach {
        Knife1 = Knife(KNF1_NAME, KNF1_DAMAGE, KNF1_WEIGHT)
        Knife2 = Knife(KNF2_NAME, KNF2_DAMAGE, KNF2_WEIGHT)
    }

    test("toString must return the Knife description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Knife3 = Knife(name, damage, weight)
            Knife3.toString() shouldBe "Knife {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Knives with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Knife31 = Knife(name, damage, weight)
            val Knife32 = Knife(name, damage, weight)
            Knife31 shouldNotBeSameInstanceAs Knife32
            Knife31 shouldBe Knife32
        }
    }

    test("Two Knives with different parameters are not equals") {
        Knife1 shouldNotBeSameInstanceAs Knife2
        Knife1 shouldNotBe Knife2
    }

    test("Two Knives with the same parameters have the same hash code"){
        checkAll(Arb.string(), Arb.positiveInt(20),Arb.positiveInt(50))
        { name, damage, weight ->
            val Knife31 = Knife(name, damage, weight)
            val Knife32 = Knife(name, damage, weight)
            Knife31.hashCode() shouldBe Knife32.hashCode()
        }
    }

    test("Two Knives with different parameters have not the same hash code"){
        Knife1.hashCode() shouldNotBe Knife2.hashCode()
    }
})