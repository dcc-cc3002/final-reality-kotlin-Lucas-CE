package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
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

private const val BLMG1_NAME = "BLMG1"
private const val BLMG1_MAX_HP = 100
private const val BLMG1_MAX_MP = 30
private const val BLMG1_DEFENSE = 10
private const val BLMG2_NAME = "BLMG2"
private const val BLMG2_MAX_HP = 80
private const val BLMG2_MAX_MP = 20
private const val BLMG2_DEFENSE = 20

class BlackMageSpec : FunSpec({
    lateinit var blmg1: BlackMage
    lateinit var blmg2: BlackMage
    lateinit var blmg12: BlackMage
    lateinit var fireSpell: Fire
    lateinit var thunderSpell: Thunder
    lateinit var queue: LinkedBlockingQueue<GameCharacter>

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blmg1 = BlackMage(BLMG1_NAME, BLMG1_MAX_HP, BLMG1_MAX_MP, BLMG1_DEFENSE, queue)
        blmg2 = BlackMage(BLMG2_NAME, BLMG2_MAX_HP, BLMG2_MAX_MP, BLMG2_DEFENSE, queue)
        blmg12 = BlackMage(BLMG1_NAME, BLMG1_MAX_HP, BLMG1_MAX_MP, BLMG1_DEFENSE, queue)
        fireSpell = Fire()
        thunderSpell = Thunder()

        blmg1.equipSpell(fireSpell)
    }

    test("toString must return the Black mage description") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val blmg3 = BlackMage(name, maxHp, maxMp, defense, queue)
            blmg3.toString() shouldBe "BlackMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', " +
                "defense='$defense'}"
        }
    }

    test("Two Black mages with the same parameters are equals") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val blmg31 = BlackMage(name, maxHp, maxMp, defense, queue)
            val blmg32 = BlackMage(name, maxHp, maxMp, defense, queue)
            blmg31 shouldNotBeSameInstanceAs blmg32
            blmg31 shouldBe blmg32
        }
    }

    test("Two Black mages with different parameters are not equals") {
        blmg1 shouldNotBeSameInstanceAs blmg2
        blmg1 shouldNotBe blmg2
    }

    test("Two Black mages with different parameters have not the same hash code") {
        checkAll(
            Arb.string(), Arb.positiveInt(200), Arb.positiveInt(100),
            Arb.positiveInt(50)
        ) { name, maxHp, maxMp, defense ->
            val blmg31 = BlackMage(name, maxHp, maxMp, defense, queue)
            val blmg32 = BlackMage(name, maxHp, maxMp, defense, queue)
            blmg31.hashCode() shouldBe blmg32.hashCode()
        }
    }

    test("Two Black mages with the same parameters have the same hash code") {
        blmg1.hashCode() shouldBe blmg12.hashCode()
    }

    test("Only black mage weapons can be equipped to black mages") {
        val engineerWeapon1 = Knife("knife", 10, 10)
        blmg1.equip(engineerWeapon1)
        val engineerWeapon2 = Staff("staff", 10, 10, 10)
        blmg1.equip(engineerWeapon2)
        val nonKnightWeapon1 = Axe("axe", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { blmg1.equip(nonKnightWeapon1) }
        blmg1.equippedWeapon shouldNotBe nonKnightWeapon1
        val nonKnightWeapon2 = Bow("bow", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { blmg1.equip(nonKnightWeapon2) }
        blmg1.equippedWeapon shouldNotBe nonKnightWeapon2
        val nonKnightWeapon3 = Sword("sword", 10, 10)
        assertThrows<InvalidEquippedWeaponException> { blmg1.equip(nonKnightWeapon3) }
        blmg1.equippedWeapon shouldNotBe nonKnightWeapon3
    }

    test("equipSpell change the spell to black magic spells") {
        //Using equipSpell
        blmg1.equipSpell(fireSpell)
        blmg1.spell shouldBe fireSpell
        blmg1.equipSpell(thunderSpell)
        blmg1.spell shouldBe thunderSpell

        //Using equip{SpellName}
        blmg1.equipSpellFire(fireSpell)
        blmg1.spell shouldBe fireSpell
        blmg1.equipSpellThunder(thunderSpell)
        blmg1.spell shouldBe thunderSpell
    }
})
