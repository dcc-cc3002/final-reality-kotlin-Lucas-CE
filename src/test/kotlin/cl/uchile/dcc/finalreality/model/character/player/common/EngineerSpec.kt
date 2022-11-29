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

private const val ENGINEER1_NAME = "ENGINEER1"
private const val ENGINEER1_MAX_HP = 100
private const val ENGINEER1_DEFENSE = 10
private const val ENGINEER2_NAME = "ENGINEER2"
private const val ENGINEER2_MAX_HP = 80
private const val ENGINEER2_DEFENSE = 20
private const val WEAPON_NAME = "WEAPON"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 10

class EngineerSpec : FunSpec({
    lateinit var eng1: Engineer
    lateinit var eng2: Engineer
    lateinit var eng12: Engineer
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var engineerWeapon1: GameWeapon
    lateinit var engineerWeapon2: GameWeapon
    lateinit var nonEngineerWeapon1: GameWeapon
    lateinit var nonEngineerWeapon2: GameWeapon
    lateinit var nonEngineerWeapon3: GameWeapon

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        eng1 = Engineer(ENGINEER1_NAME, ENGINEER1_MAX_HP, ENGINEER1_DEFENSE, queue)
        eng2 = Engineer(ENGINEER2_NAME, ENGINEER2_MAX_HP, ENGINEER2_DEFENSE, queue)
        eng12 = Engineer(ENGINEER1_NAME, ENGINEER1_MAX_HP, ENGINEER1_DEFENSE, queue)
        engineerWeapon1 = Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        engineerWeapon2 = Bow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonEngineerWeapon1 = Knife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonEngineerWeapon2 = Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonEngineerWeapon3 = Sword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
    }

    test("toString must return the Engineer description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val eng3 = Engineer(name, maxHp, defense, queue)
            eng3.toString() shouldBe "Engineer {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Engineers with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100)) {
            name, maxHp, defense ->
            val eng31 = Engineer(name, maxHp, defense, queue)
            val eng32 = Engineer(name, maxHp, defense, queue)
            eng31 shouldNotBeSameInstanceAs eng32
            eng31 shouldBe eng32
        }
    }

    test("Two Engineers with different parameters are not equals") {
        eng1 shouldNotBeSameInstanceAs eng2
        eng1 shouldNotBe eng2
    }

    test("Two Engineers with different parameters have not the same hash code") {
        checkAll(Arb.string(), Arb.positiveInt(200), Arb.positiveInt(50)) {
            name, maxHp, defense ->
            val eng31 = Engineer(name, maxHp, defense, queue)
            val eng32 = Engineer(name, maxHp, defense, queue)
            eng31.hashCode() shouldBe eng32.hashCode()
        }
    }

    test("Two Engineers with the same parameters have the same hash code") {
        eng1.hashCode() shouldBe eng12.hashCode()
    }

    test("Only engineer weapons can be equipped to engineer") {
        eng1.equip(engineerWeapon1)
        eng1.equip(engineerWeapon2)
        assertThrows<InvalidEquippedWeaponException> { eng1.equip(nonEngineerWeapon1) }
        eng1.equippedWeapon shouldNotBe nonEngineerWeapon1
        assertThrows<InvalidEquippedWeaponException> { eng1.equip(nonEngineerWeapon2) }
        eng1.equippedWeapon shouldNotBe nonEngineerWeapon2
        assertThrows<InvalidEquippedWeaponException> { eng1.equip(nonEngineerWeapon3) }
        eng1.equippedWeapon shouldNotBe nonEngineerWeapon3
    }
})
