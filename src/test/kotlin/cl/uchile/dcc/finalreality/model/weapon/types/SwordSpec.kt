package cl.uchile.dcc.finalreality.model.weapon.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val SWRD1_NAME = "SWRD1"
private const val SWRD1_DAMAGE = 10
private const val SWRD1_WEIGHT = 15
private const val SWRD2_NAME = "SWRD2"
private const val SWRD2_DAMAGE = 12
private const val SWRD2_WEIGHT = 30

class SwordSpec : FunSpec ({
    lateinit var Sword1: Sword
    lateinit var Sword2: Sword

    beforeEach {
        Sword1 = Sword(SWRD1_NAME, SWRD1_DAMAGE, SWRD1_WEIGHT)
        Sword2 = Sword(SWRD2_NAME, SWRD2_DAMAGE, SWRD2_WEIGHT)
    }

    test("toString must return the Sword description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Sword3 = Sword(name, damage, weight)
            Sword3.toString() shouldBe "Sword {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Swords with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Sword31 = Sword(name, damage, weight)
            val Sword32 = Sword(name, damage, weight)
            Sword31 shouldNotBeSameInstanceAs Sword32
            Sword31 shouldBe Sword32
        }
    }

    test("Two Swords with different parameters are not equals") {
        Sword1 shouldNotBeSameInstanceAs Sword2
        Sword1 shouldNotBe Sword2
    }

    test("Two Swords with the same parameters have the same hash code"){
        checkAll(Arb.string(), Arb.positiveInt(20),Arb.positiveInt(50))
        { name, damage, weight ->
            val Sword31 = Sword(name, damage, weight)
            val Sword32 = Sword(name, damage, weight)
            Sword31.hashCode() shouldBe Sword32.hashCode()
        }
    }

    test("Two Swords with different parameters have not the same hash code"){
        Sword1.hashCode() shouldNotBe Sword2.hashCode()
    }
})