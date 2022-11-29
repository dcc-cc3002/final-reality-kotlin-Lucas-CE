package cl.uchile.dcc.finalreality.model.character.player.common

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
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
private const val WEAPON_NAME = "WEAPON"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 10

class KnightSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var knight1: Knight
    lateinit var knight12: Knight
    lateinit var knight2: Knight
    lateinit var knightWeapon1: GameWeapon
    lateinit var knightWeapon2: GameWeapon
    lateinit var knightWeapon3: GameWeapon
    lateinit var nonKnightWeapon1: GameWeapon
    lateinit var nonKnightWeapon2: GameWeapon

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        knight1 = Knight(KNIGHT1_NAME, KNIGHT1_MAX_HP, KNIGHT1_DEFENSE, queue)
        knight12 = Knight(KNIGHT1_NAME, KNIGHT1_MAX_HP, KNIGHT1_DEFENSE, queue)
        knight2 = Knight(KNIGHT2_NAME, KNIGHT2_MAX_HP, KNIGHT2_DEFENSE, queue)
        knightWeapon1 = Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        knightWeapon2 = Knife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        knightWeapon3 = Sword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonKnightWeapon1 = Bow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonKnightWeapon2 = Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT, WEAPON_DAMAGE)

        knight1.equip(knightWeapon1)
        knight12.equip(knightWeapon2)
        knight2.equip(knightWeapon3)
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
        knight1.equip(knightWeapon1)
        knight1.equip(knightWeapon2)
        knight1.equip(knightWeapon3)
        assertThrows<InvalidEquippedWeaponException> { knight1.equip(nonKnightWeapon1) }
        knight1.equippedWeapon shouldNotBe nonKnightWeapon1
        assertThrows<InvalidEquippedWeaponException> { knight1.equip(nonKnightWeapon2) }
        knight1.equippedWeapon shouldNotBe nonKnightWeapon2
    }
})
