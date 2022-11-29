package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
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

private const val BLMG1_NAME = "BLMG1"
private const val BLMG1_MAX_HP = 100
private const val BLMG1_MAX_MP = 30
private const val BLMG1_DEFENSE = 10
private const val BLMG2_NAME = "BLMG2"
private const val BLMG2_MAX_HP = 80
private const val BLMG2_MAX_MP = 20
private const val BLMG2_DEFENSE = 20
private const val WEAPON_NAME = "WEAPON"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 10

class BlackMageSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var blmg1: BlackMage
    lateinit var blmg12: BlackMage
    lateinit var blmg2: BlackMage
    lateinit var blackMageWeapon1: GameWeapon
    lateinit var blackMageWeapon2: GameWeapon
    lateinit var nonBlackMageWeapon1: GameWeapon
    lateinit var nonBlackMageWeapon2: GameWeapon
    lateinit var nonBlackMageWeapon3: GameWeapon
    lateinit var fireSpell: Fire
    lateinit var thunderSpell: Thunder

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blmg1 = BlackMage(BLMG1_NAME, BLMG1_MAX_HP, BLMG1_MAX_MP, BLMG1_DEFENSE, queue)
        blmg2 = BlackMage(BLMG2_NAME, BLMG2_MAX_HP, BLMG2_MAX_MP, BLMG2_DEFENSE, queue)
        blmg12 = BlackMage(BLMG1_NAME, BLMG1_MAX_HP, BLMG1_MAX_MP, BLMG1_DEFENSE, queue)
        blackMageWeapon1 = Knife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        blackMageWeapon2 = Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT, WEAPON_DAMAGE)
        nonBlackMageWeapon1 = Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonBlackMageWeapon2 = Bow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonBlackMageWeapon3 = Sword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        fireSpell = Fire()
        thunderSpell = Thunder()

        blmg1.equip(blackMageWeapon1)
        blmg12.equip(blackMageWeapon1)
        blmg2.equip(blackMageWeapon2)
        blmg1.equipSpell(fireSpell)
        blmg12.equipSpell(fireSpell)
        blmg2.equipSpell(thunderSpell)
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
        blmg1.equip(blackMageWeapon1)
        blmg1.equip(blackMageWeapon2)
        assertThrows<InvalidEquippedWeaponException> { blmg1.equip(nonBlackMageWeapon1) }
        blmg1.equippedWeapon shouldNotBe nonBlackMageWeapon1
        assertThrows<InvalidEquippedWeaponException> { blmg1.equip(nonBlackMageWeapon2) }
        blmg1.equippedWeapon shouldNotBe nonBlackMageWeapon2
        assertThrows<InvalidEquippedWeaponException> { blmg1.equip(nonBlackMageWeapon3) }
        blmg1.equippedWeapon shouldNotBe nonBlackMageWeapon3
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
