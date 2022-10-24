package cl.uchile.dcc.finalreality.model.weapon.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val AXE1_NAME = "AXE1"
private const val AXE1_DAMAGE = 10
private const val AXE1_WEIGHT = 15
private const val AXE2_NAME = "AXE2"
private const val AXE2_DAMAGE = 12
private const val AXE2_WEIGHT = 30

class AxeSpec : FunSpec ({
    lateinit var Axe1: Axe
    lateinit var Axe2: Axe

    beforeEach {
        Axe1 = Axe(AXE1_NAME, AXE1_DAMAGE, AXE1_WEIGHT)
        Axe2 = Axe(AXE2_NAME, AXE2_DAMAGE, AXE2_WEIGHT)
    }

    test("toString must return the Axe description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Axe3 = Axe(name, damage, weight)
            Axe3.toString() shouldBe "Axe {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Axes with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Axe31 = Axe(name, damage, weight)
            val Axe32 = Axe(name, damage, weight)
            Axe31 shouldNotBeSameInstanceAs Axe32
            Axe31 shouldBe Axe32
        }
    }

    test("Two Axes with different parameters are not equals") {
        Axe1 shouldNotBeSameInstanceAs Axe2
        Axe1 shouldNotBe Axe2
    }

    test("Two Axes with the same parameters have the same hash code"){
        checkAll(Arb.string(), Arb.positiveInt(20),Arb.positiveInt(50))
        { name, damage, weight ->
            val Axe31 = Axe(name, damage, weight)
            val Axe32 = Axe(name, damage, weight)
            Axe31.hashCode() shouldBe Axe32.hashCode()
        }
    }

    test("Two Axes with different parameters have not the same hash code"){
        Axe1.hashCode() shouldNotBe Axe2.hashCode()
    }
})