package cl.uchile.dcc.finalreality.model.character.player.common

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.weapon.types.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.Staff
import cl.uchile.dcc.finalreality.model.weapon.types.Sword
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val KNGT1_NAME = "KNGT1"
private const val KNGT1_MAXHP = 100
private const val KNGT1_DEFENSE = 10
private const val KNGT2_NAME = "KNGT2"
private const val KNGT2_MAXHP = 80
private const val KNGT2_DEFENSE = 20

class KnightSpec : FunSpec({
    lateinit var kngt1: Knight
    lateinit var kngt2: Knight
    lateinit var kngt12: Knight
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        kngt1 = Knight(KNGT1_NAME, KNGT1_MAXHP, KNGT1_DEFENSE, queue)
        kngt2 = Knight(KNGT2_NAME, KNGT2_MAXHP, KNGT2_DEFENSE, queue)
        kngt12 = Knight(KNGT1_NAME, KNGT1_MAXHP, KNGT1_DEFENSE, queue)
    }

    test("toString must return the Knight description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val Knight3 = Knight(name, maxHp, defense, queue)
            Knight3.toString() shouldBe "Knight {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Knights with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val kngt31 = Knight(name, maxHp, defense, queue)
            val kngt32 = Knight(name, maxHp, defense, queue)
            kngt31 shouldNotBeSameInstanceAs kngt32
            kngt31 shouldBe kngt32
        }
    }

    test("Two Knights with different parameters are not equals") {
        kngt1 shouldNotBeSameInstanceAs kngt2
        kngt1 shouldNotBe kngt2
    }

    test("Two Knights with different parameters have not the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(200), Arb.positiveInt(50)) {
            name, maxHp, defense ->
            val kngt31 = Knight(name, maxHp, defense, queue)
            val kngt32 = Knight(name, maxHp, defense, queue)
            kngt31.hashCode() shouldBe kngt32.hashCode()
        }
    }

    test("Two Knights with the same parameters have the same hash code") {
        kngt1.hashCode() shouldBe kngt12.hashCode()
    }

    test("Only knight weapons can be equipped to knights") {
        val engineerWeapon1 = Axe("axe", 10, 10)
        kngt1.equip(engineerWeapon1)
        val engineerWeapon2 = Knife("knife", 10, 10)
        kngt1.equip(engineerWeapon2)
        val engineerWeapon3 = Sword("sword", 10, 10)
        kngt1.equip(engineerWeapon3)
        val nonKnightWeapon1 = Bow("bow", 10, 10)
        assertThrows<AssertionError> { kngt1.equip(nonKnightWeapon1) }
        val nonKnightWeapon2 = Staff("staff", 10, 10)
        assertThrows<AssertionError> { kngt1.equip(nonKnightWeapon2) }
    }
})
