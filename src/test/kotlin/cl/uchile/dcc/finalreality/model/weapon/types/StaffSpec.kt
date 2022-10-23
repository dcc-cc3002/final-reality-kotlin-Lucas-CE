package cl.uchile.dcc.finalreality.model.weapon.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val STFF1_NAME = "STFF1"
private const val STFF1_DAMAGE = 10
private const val STFF1_WEIGHT = 15
private const val STFF2_NAME = "STFF2"
private const val STFF2_DAMAGE = 12
private const val STFF2_WEIGHT = 30

class StaffSpec : FunSpec ({
    lateinit var Staff1: Staff
    lateinit var Staff2: Staff

    beforeEach {
        Staff1 = Staff(STFF1_NAME, STFF1_DAMAGE, STFF1_WEIGHT)
        Staff2 = Staff(STFF2_NAME, STFF2_DAMAGE, STFF2_WEIGHT)
    }

    test("toString must return the Staff description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Staff3 = Staff(name, damage, weight)
            Staff3.toString() shouldBe "Staff {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Staffs with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100))
        { name, damage, weight ->
            val Staff31 = Staff(name, damage, weight)
            val Staff32 = Staff(name, damage, weight)
            Staff31 shouldNotBeSameInstanceAs Staff32
            Staff31 shouldBe Staff32
        }
    }

    test("Two Staffs with different parameters are not equals") {
        Staff1 shouldNotBeSameInstanceAs Staff2
        Staff1 shouldNotBe Staff2
    }
})