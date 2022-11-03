package cl.uchile.dcc.finalreality.model.character.player.common

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Sword
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
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

private const val KNIGHT1_NAME = "KNIGHT1"
private const val KNIGHT1_MAX_HP = 100
private const val KNIGHT1_DEFENSE = 10
private const val KNIGHT2_NAME = "KNIGHT2"
private const val KNIGHT2_MAX_HP = 80
private const val KNIGHT2_DEFENSE = 20

class KnightSpec : FunSpec({
    lateinit var knight1: Knight
    lateinit var knight2: Knight
    lateinit var knight12: Knight
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        knight1 = Knight(KNIGHT1_NAME, KNIGHT1_MAX_HP, KNIGHT1_DEFENSE, queue)
        knight2 = Knight(KNIGHT2_NAME, KNIGHT2_MAX_HP, KNIGHT2_DEFENSE, queue)
        knight12 = Knight(KNIGHT1_NAME, KNIGHT1_MAX_HP, KNIGHT1_DEFENSE, queue)
    }

    test("toString must return the Knight description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val knight3 = Knight(name, maxHp, defense, queue)
            knight3.toString() shouldBe "Knight {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Knights with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val knight31 = Knight(name, maxHp, defense, queue)
            val knight32 = Knight(name, maxHp, defense, queue)
            knight31 shouldNotBeSameInstanceAs knight32
            knight31 shouldBe knight32
        }
    }

    test("Two Knights with different parameters are not equals") {
        knight1 shouldNotBeSameInstanceAs knight2
        knight1 shouldNotBe knight2
    }

    test("Two Knights with different parameters have not the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(200), Arb.positiveInt(50)) {
            name, maxHp, defense ->
            val knight31 = Knight(name, maxHp, defense, queue)
            val knight32 = Knight(name, maxHp, defense, queue)
            knight31.hashCode() shouldBe knight32.hashCode()
        }
    }

    test("Two Knights with the same parameters have the same hash code") {
        knight1.hashCode() shouldBe knight12.hashCode()
    }

    test("Only knight weapons can be equipped to knights") {
        val engineerWeapon1 = Axe("axe", 10, 10)
        knight1.equip(engineerWeapon1)
        val engineerWeapon2 = Knife("knife", 10, 10)
        knight1.equip(engineerWeapon2)
        val engineerWeapon3 = Sword("sword", 10, 10)
        knight1.equip(engineerWeapon3)
        val nonKnightWeapon1 = Bow("bow", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { knight1.equip(nonKnightWeapon1) }
        val nonKnightWeapon2 = Staff("staff", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { knight1.equip(nonKnightWeapon2) }
    }
})
