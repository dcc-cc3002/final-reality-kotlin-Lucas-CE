package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
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

private const val WHMG1_NAME = "WHMG1"
private const val WHMG1_MAX_HP = 100
private const val WHMG1_MAX_MP = 30
private const val WHMG1_DEFENSE = 10
private const val WHMG2_NAME = "WHMG2"
private const val WHMG2_MAX_HP = 80
private const val WHMG2_MAX_MP = 20
private const val WHMG2_DEFENSE = 20
private const val WEAPON_NAME = "WEAPON"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 10

class WhiteMageSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var whmg1: WhiteMage
    lateinit var whmg2: WhiteMage
    lateinit var whmg12: WhiteMage
    lateinit var whiteMageWeapon1: GameWeapon
    lateinit var nonWhiteMageWeapon1: GameWeapon
    lateinit var nonWhiteMageWeapon2: GameWeapon
    lateinit var nonWhiteMageWeapon3: GameWeapon
    lateinit var nonWhiteMageWeapon4: GameWeapon
    lateinit var healSpell: Heal
    lateinit var paralysisSpell: Paralysis
    lateinit var poisonSpell: Poison


    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        whmg1 = WhiteMage(WHMG1_NAME, WHMG1_MAX_HP, WHMG1_MAX_MP, WHMG1_DEFENSE, queue)
        whmg2 = WhiteMage(WHMG2_NAME, WHMG2_MAX_HP, WHMG2_MAX_MP, WHMG2_DEFENSE, queue)
        whmg12 = WhiteMage(WHMG1_NAME, WHMG1_MAX_HP, WHMG1_MAX_MP, WHMG1_DEFENSE, queue)
        whiteMageWeapon1 = Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT, WEAPON_DAMAGE)
        nonWhiteMageWeapon1 = Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonWhiteMageWeapon2 = Bow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonWhiteMageWeapon3 = Knife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        nonWhiteMageWeapon4 = Sword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
        healSpell = Heal()
        paralysisSpell = Paralysis()
        poisonSpell = Poison()

        whmg1.equip(whiteMageWeapon1)
        whmg12.equip(whiteMageWeapon1)
        whmg2.equip(whiteMageWeapon1)
        whmg1.equipSpell(healSpell)
        whmg12.equipSpell(paralysisSpell)
        whmg2.equipSpell(poisonSpell)
    }

    test("toString must return the White mage description") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val whmg3 = WhiteMage(name, maxHp, maxMp, defense, queue)
            whmg3.toString() shouldBe "WhiteMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', " +
                "defense='$defense'}"
        }
    }

    test("Two White mages with the same parameters are equals") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val whmg31 = WhiteMage(name, maxHp, maxMp, defense, queue)
            val whmg32 = WhiteMage(name, maxHp, maxMp, defense, queue)
            whmg31 shouldNotBeSameInstanceAs whmg32
            whmg31 shouldBe whmg32
        }
    }

    test("Two White mages with different parameters are not equals") {
        whmg1 shouldNotBeSameInstanceAs whmg2
        whmg1 shouldNotBe whmg2
    }

    test("Two White mages with different parameters have not the same hash code") {
        checkAll(
            Arb.string(), Arb.positiveInt(200), Arb.positiveInt(100),
            Arb.positiveInt(50)
        ) { name, maxHp, maxMp, defense ->
            val whmg31 = WhiteMage(name, maxHp, maxMp, defense, queue)
            val whmg32 = WhiteMage(name, maxHp, maxMp, defense, queue)
            whmg31.hashCode() shouldBe whmg32.hashCode()
        }
    }

    test("Two White mages with the same parameters have the same hash code") {
        whmg1.hashCode() shouldBe whmg12.hashCode()
    }

    test("Only white mage weapons can be equipped to white mages") {
        whmg1.equip(whiteMageWeapon1)
        assertThrows<InvalidEquippedWeaponException> { whmg1.equip(nonWhiteMageWeapon1) }
        whmg1.equippedWeapon shouldNotBe nonWhiteMageWeapon1
        assertThrows<InvalidEquippedWeaponException> { whmg1.equip(nonWhiteMageWeapon2) }
        whmg1.equippedWeapon shouldNotBe nonWhiteMageWeapon2
        assertThrows<InvalidEquippedWeaponException> { whmg1.equip(nonWhiteMageWeapon3) }
        whmg1.equippedWeapon shouldNotBe nonWhiteMageWeapon3
        assertThrows<InvalidEquippedWeaponException> { whmg1.equip(nonWhiteMageWeapon4) }
        whmg1.equippedWeapon shouldNotBe nonWhiteMageWeapon4
    }

    test("equip a spell change the spell to white magic spells") {
        //Using equipSpell
        whmg1.equipSpell(healSpell)
        whmg1.spell shouldBe healSpell
        whmg1.equipSpell(paralysisSpell)
        whmg1.spell shouldBe paralysisSpell
        whmg1.equipSpell(poisonSpell)
        whmg1.spell shouldBe poisonSpell

        //Using equip{SpellName}
        whmg1.equipSpellHeal(healSpell)
        whmg1.spell shouldBe healSpell
        whmg1.equipSpellParalysis(paralysisSpell)
        whmg1.spell shouldBe paralysisSpell
        whmg1.equipSpellPoison(poisonSpell)
        whmg1.spell shouldBe poisonSpell
    }
})
