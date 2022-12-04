package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedSpellException
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.LinkedBlockingQueue

private const val MAGE_NAME = "MAGE1"
private const val MAGE_MAX_HP = 100
private const val MAGE_MAX_MP = 30
private const val MAGE_DEFENSE = 10

class AbstractMagesSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var blackMage1: BlackMage
    lateinit var whiteMage1: WhiteMage
    lateinit var healSpell: Heal
    lateinit var paralysisSpell: Paralysis
    lateinit var poisonSpell: Poison
    lateinit var fireSpell: Fire
    lateinit var thunderSpell: Thunder

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blackMage1 = BlackMage(MAGE_NAME, MAGE_MAX_HP, MAGE_MAX_MP, MAGE_DEFENSE, queue)
        whiteMage1 = WhiteMage(MAGE_NAME, MAGE_MAX_HP, MAGE_MAX_MP, MAGE_DEFENSE, queue)
        healSpell = Heal()
        paralysisSpell = Paralysis()
        poisonSpell = Poison()
        fireSpell = Fire()
        thunderSpell = Thunder()

        whiteMage1.equipSpell(healSpell)
        blackMage1.equipSpell(fireSpell)
    }

    test("The currentMp initial value is the maxMp value") {
        blackMage1.currentMp shouldBe MAGE_MAX_MP
        whiteMage1.currentMp shouldBe MAGE_MAX_MP
    }

    test("The currentMp setter change the currentMp value") {
        checkAll(Arb.int(0..MAGE_MAX_MP)) {
                currentMp ->
            blackMage1.currentMp = currentMp
            blackMage1.currentMp shouldBe currentMp
        }

        checkAll(Arb.int(0..MAGE_MAX_MP)) {
                currentMp ->
            whiteMage1.currentMp = currentMp
            whiteMage1.currentMp shouldBe currentMp
        }
    }

    test("The currentMp setter throws an exception when the value is out of range (0,maxMp)") {
        checkAll(
            Arb.int(MAGE_MAX_MP + 1..2 * MAGE_MAX_MP),
            Arb.int(-MAGE_MAX_MP..-1)
        ) { greaterMaxMp, lessMinMp ->
            // BlackMage
            assertThrows<InvalidStatValueException> { blackMage1.currentMp = greaterMaxMp }
            assertThrows<InvalidStatValueException> { blackMage1.currentMp = lessMinMp }
            // WhiteMage
            assertThrows<InvalidStatValueException> { whiteMage1.currentMp = greaterMaxMp }
            assertThrows<InvalidStatValueException> { whiteMage1.currentMp = lessMinMp }
        }
    }

    test("Trying to equip spells to wrong mages classes throws an exception and not change spell") {
        // Using equipSpell
        assertThrows<InvalidEquippedSpellException> { whiteMage1.equipSpell(fireSpell) }
        whiteMage1.spell shouldNotBe fireSpell
        assertThrows<InvalidEquippedSpellException> { whiteMage1.equipSpell(thunderSpell) }
        whiteMage1.spell shouldNotBe thunderSpell
        assertThrows<InvalidEquippedSpellException> { blackMage1.equipSpell(healSpell) }
        blackMage1.spell shouldNotBe healSpell
        assertThrows<InvalidEquippedSpellException> { blackMage1.equipSpell(paralysisSpell) }
        blackMage1.spell shouldNotBe paralysisSpell
        assertThrows<InvalidEquippedSpellException> { blackMage1.equipSpell(poisonSpell) }
        blackMage1.spell shouldNotBe poisonSpell

        // Using equip{SpellName}
        assertThrows<InvalidEquippedSpellException> { whiteMage1.equipSpellFire(fireSpell) }
        whiteMage1.spell shouldNotBe fireSpell
        assertThrows<InvalidEquippedSpellException> { whiteMage1.equipSpellThunder(thunderSpell) }
        whiteMage1.spell shouldNotBe thunderSpell
        assertThrows<InvalidEquippedSpellException> { blackMage1.equipSpellHeal(healSpell) }
        blackMage1.spell shouldNotBe healSpell
        assertThrows<InvalidEquippedSpellException> { blackMage1.equipSpellParalysis(paralysisSpell) }
        blackMage1.spell shouldNotBe paralysisSpell
        assertThrows<InvalidEquippedSpellException> { blackMage1.equipSpellPoison(poisonSpell) }
        blackMage1.spell shouldNotBe poisonSpell
    }
})
