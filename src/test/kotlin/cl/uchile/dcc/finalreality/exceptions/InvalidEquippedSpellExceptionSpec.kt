package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val NAME = "MAGE1"
private const val MAX_HP = 100
private const val MAX_MP = 50
private const val DEFENSE = 10
private const val WEAPON_NAME = "WEAPON1"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 5
private const val WEAPON_MAGIC_DAMAGE = 10

class InvalidEquippedSpellExceptionSpec : FunSpec({
    lateinit var mage1: BlackMage
    lateinit var weapon1: Staff
    lateinit var spell1: Spell
    lateinit var queue: LinkedBlockingQueue<GameCharacter>

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        mage1 = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        weapon1 = Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT, WEAPON_MAGIC_DAMAGE)
        spell1 = Heal()
    }

    test(
        "An invalid equipped spell exception can be thrown with a spell class name, " +
            "and a mage class name"
    ) {
        assertThrows<InvalidEquippedSpellException> {
            throw InvalidEquippedSpellException(spell1, mage1)
        }.message shouldBe "The $spell1 class cannot be equipped to a $mage1 class"
    }
})