package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val NAME = "ENGINEER1"
private const val MAX_HP = 100
private const val DEFENSE = 10

class InvalidSpellTargetExceptionSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var player1: Engineer
    lateinit var spell1: Spell

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        player1 = Engineer(NAME, MAX_HP, DEFENSE, queue)
        spell1 = Thunder()
    }

    test(
        "An invalid spell target exception can be thrown with a spell class name, " +
            "and a game character class name"
    ) {
        assertThrows<InvalidSpellTargetException> {
            throw InvalidSpellTargetException(spell1, player1)
        }.message shouldBe "The $spell1 class cannot target a $player1 class"
    }
})
