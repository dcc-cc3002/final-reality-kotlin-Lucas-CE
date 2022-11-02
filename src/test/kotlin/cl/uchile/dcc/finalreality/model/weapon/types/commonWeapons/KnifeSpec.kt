package cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val KNIFE1_NAME = "KNIFE1"
private const val KNIFE1_DAMAGE = 10
private const val KNIFE1_WEIGHT = 15
private const val KNIFE2_NAME = "KNIFE2"
private const val KNIFE2_DAMAGE = 12
private const val KNIFE2_WEIGHT = 30

class KnifeSpec : FunSpec({
    lateinit var knife1: Knife
    lateinit var knife2: Knife

    beforeEach {
        knife1 = Knife(KNIFE1_NAME, KNIFE1_DAMAGE, KNIFE1_WEIGHT)
        knife2 = Knife(KNIFE2_NAME, KNIFE2_DAMAGE, KNIFE2_WEIGHT)
    }

    test("toString must return the Knife description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val knife3 = Knife(name, damage, weight)
            knife3.toString() shouldBe "Knife {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Knives with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val knife31 = Knife(name, damage, weight)
            val knife32 = Knife(name, damage, weight)
            knife31 shouldNotBeSameInstanceAs knife32
            knife31 shouldBe knife32
        }
    }

    test("Two Knives with different parameters are not equals") {
        knife1 shouldNotBeSameInstanceAs knife2
        knife1 shouldNotBe knife2
    }

    test("Two Knives with the same parameters have the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(20), Arb.positiveInt(50)) {
            name, damage, weight ->
            val knife31 = Knife(name, damage, weight)
            val knife32 = Knife(name, damage, weight)
            knife31.hashCode() shouldBe knife32.hashCode()
        }
    }

    test("Two Knives with different parameters have not the same hash code") {
        knife1.hashCode() shouldNotBe knife2.hashCode()
    }
})
