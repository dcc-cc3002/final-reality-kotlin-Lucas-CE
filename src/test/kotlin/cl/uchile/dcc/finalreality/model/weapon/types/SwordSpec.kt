package cl.uchile.dcc.finalreality.model.weapon.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val SWORD1_NAME = "SWORD1"
private const val SWORD1_DAMAGE = 10
private const val SWORD1_WEIGHT = 15
private const val SWORD2_NAME = "SWORD2"
private const val SWORD2_DAMAGE = 12
private const val SWORD2_WEIGHT = 30

class SwordSpec : FunSpec({
    lateinit var sword1: Sword
    lateinit var sword2: Sword

    beforeEach {
        sword1 = Sword(SWORD1_NAME, SWORD1_DAMAGE, SWORD1_WEIGHT)
        sword2 = Sword(SWORD2_NAME, SWORD2_DAMAGE, SWORD2_WEIGHT)
    }

    test("toString must return the Sword description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val sword3 = Sword(name, damage, weight)
            sword3.toString() shouldBe "Sword {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Swords with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val sword31 = Sword(name, damage, weight)
            val sword32 = Sword(name, damage, weight)
            sword31 shouldNotBeSameInstanceAs sword32
            sword31 shouldBe sword32
        }
    }

    test("Two Swords with different parameters are not equals") {
        sword1 shouldNotBeSameInstanceAs sword2
        sword1 shouldNotBe sword2
    }

    test("Two Swords with the same parameters have the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(20), Arb.positiveInt(50)) {
            name, damage, weight ->
            val sword31 = Sword(name, damage, weight)
            val sword32 = Sword(name, damage, weight)
            sword31.hashCode() shouldBe sword32.hashCode()
        }
    }

    test("Two Swords with different parameters have not the same hash code") {
        sword1.hashCode() shouldNotBe sword2.hashCode()
    }
})
