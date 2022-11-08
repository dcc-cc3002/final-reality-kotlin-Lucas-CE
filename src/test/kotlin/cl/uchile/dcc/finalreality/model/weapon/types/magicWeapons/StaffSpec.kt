package cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

private const val STAFF1_NAME = "STAFF1"
private const val STAFF1_DAMAGE = 10
private const val STAFF1_WEIGHT = 15
private const val STAFF2_NAME = "STAFF2"
private const val STAFF2_DAMAGE = 12
private const val STAFF2_WEIGHT = 30

class StaffSpec : FunSpec({
    lateinit var staff1: Staff
    lateinit var staff2: Staff

    beforeEach {
        staff1 = Staff(STAFF1_NAME, STAFF1_DAMAGE, STAFF1_WEIGHT)
        staff2 = Staff(STAFF2_NAME, STAFF2_DAMAGE, STAFF2_WEIGHT)
    }

    test("toString must return the Staff description") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val staff3 = Staff(name, damage, weight)
            staff3.toString() shouldBe "Staff {name='$name', damage='$damage', weight='$weight'}"
        }
    }

    test("Two Staffs with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(100), Arb.positiveInt(100)) {
            name, damage, weight ->
            val staff31 = Staff(name, damage, weight)
            val staff32 = Staff(name, damage, weight)
            staff31 shouldNotBeSameInstanceAs staff32
            staff31 shouldBe staff32
        }
    }

    test("Two Staffs with different parameters are not equals") {
        staff1 shouldNotBeSameInstanceAs staff2
        staff1 shouldNotBe staff2
    }

    test("Two Staffs with the same parameters have the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(20), Arb.positiveInt(50)) {
            name, damage, weight ->
            val staff31 = Staff(name, damage, weight)
            val staff32 = Staff(name, damage, weight)
            staff31.hashCode() shouldBe staff32.hashCode()
        }
    }

    test("Two Staffs with different parameters have not the same hash code") {
        staff1.hashCode() shouldNotBe staff2.hashCode()
    }
})
