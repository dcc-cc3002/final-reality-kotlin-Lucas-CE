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

private const val THIEF1_NAME = "THIEF1"
private const val THIEF1_MAX_HP = 100
private const val THIEF1_DEFENSE = 10
private const val THIEF2_NAME = "THIEF2"
private const val THIEF2_MAX_HP = 80
private const val THIEF2_DEFENSE = 20

class ThiefSpec : FunSpec({
    lateinit var thief1: Thief
    lateinit var thief2: Thief
    lateinit var thief12: Thief
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        thief1 = Thief(THIEF1_NAME, THIEF1_MAX_HP, THIEF1_DEFENSE, queue)
        thief2 = Thief(THIEF2_NAME, THIEF2_MAX_HP, THIEF2_DEFENSE, queue)
        thief12 = Thief(THIEF1_NAME, THIEF1_MAX_HP, THIEF1_DEFENSE, queue)
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
        val engineerWeapon1 = Bow("bow", 10, 10)
        thief1.equip(engineerWeapon1)
        val engineerWeapon2 = Knife("knife", 10, 10)
        thief1.equip(engineerWeapon2)
        val engineerWeapon3 = Sword("sword", 10, 10)
        thief1.equip(engineerWeapon3)
        val nonKnightWeapon1 = Axe("axe", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { thief1.equip(nonKnightWeapon1) }
        thief1.equippedWeapon shouldNotBe nonKnightWeapon1
        val nonKnightWeapon2 = Staff("staff", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { thief1.equip(nonKnightWeapon2) }
        thief1.equippedWeapon shouldNotBe nonKnightWeapon2
    }
})
