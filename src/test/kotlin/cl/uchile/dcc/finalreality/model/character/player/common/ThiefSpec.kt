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

private const val THIEF1_NAME = "THIEF1"
private const val THIEF1_MAX_HP = 100
private const val THIEF1_DEFENSE = 10
private const val THIEF2_NAME = "THIEF2"
private const val THIEF2_MAX_HP = 80
private const val THIEF2_DEFENSE = 20
private const val WEAPON_NAME = "WEAPON"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 10

class ThiefSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var thief1: Thief
    lateinit var thief12: Thief
    lateinit var thief2: Thief
    lateinit var thiefWeapon1: GameWeapon
    lateinit var thiefWeapon2: GameWeapon
    lateinit var thiefWeapon3: GameWeapon
    lateinit var nonThiefWeapon1: GameWeapon
    lateinit var nonThiefWeapon2: GameWeapon

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        thief1 = Thief(THIEF1_NAME, THIEF1_MAX_HP, THIEF1_DEFENSE, queue)
        thief12 = Thief(THIEF1_NAME, THIEF1_MAX_HP, THIEF1_DEFENSE, queue)
        thief2 = Thief(THIEF2_NAME, THIEF2_MAX_HP, THIEF2_DEFENSE, queue)
        thiefWeapon1 = Bow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        thiefWeapon2 = Knife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        thiefWeapon3 = Sword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonThiefWeapon1 = Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonThiefWeapon2 = Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT, WEAPON_DAMAGE)
    }

    test("toString must return the Thief description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val thief3 = Thief(name, maxHp, defense, queue)
            thief3.toString() shouldBe "Thief {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Thieves with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val thief31 = Thief(name, maxHp, defense, queue)
            val thief32 = Thief(name, maxHp, defense, queue)
            thief31 shouldNotBeSameInstanceAs thief32
            thief31 shouldBe thief32
        }
    }

    test("Two Thieves with different parameters are not equals") {
        thief1 shouldNotBeSameInstanceAs thief2
        thief1 shouldNotBe thief2
    }

    test("Two Thieves with different parameters have not the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(200), Arb.positiveInt(50)) {
            name, maxHp, defense ->
            val thief31 = Thief(name, maxHp, defense, queue)
            val thief32 = Thief(name, maxHp, defense, queue)
            thief31.hashCode() shouldBe thief32.hashCode()
        }
    }

    test("Two Thieves with the same parameters have the same hash code") {
        thief1.hashCode() shouldBe thief12.hashCode()
    }

    test("Only thief weapons can be equipped to thieves") {
        thief1.equip(thiefWeapon1)
        thief1.equip(thiefWeapon2)
        thief1.equip(thiefWeapon3)
        assertThrows<InvalidEquippedWeaponException> { thief1.equip(nonThiefWeapon1) }
        thief1.equippedWeapon shouldNotBe nonThiefWeapon1
        assertThrows<InvalidEquippedWeaponException> { thief1.equip(nonThiefWeapon2) }
        thief1.equippedWeapon shouldNotBe nonThiefWeapon2
    }
})
