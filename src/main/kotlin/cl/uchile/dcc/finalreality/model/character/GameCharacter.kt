package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.controller.CharacterObserver
import cl.uchile.dcc.finalreality.exceptions.InvalidSpellTargetException
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @property name
 *    The name of the character.
 * @property maxHp
 *    The maximum health points of the character.
 * @property defense
 *    The defense of the character.
 * @property currentHp
 *    The current health points of the character.
 * @property characterListeners
 *    The listeners of the character.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
interface GameCharacter {
    val name: String
    val maxHp: Int
    var currentHp: Int
    val defense: Int
    val characterListeners: List<CharacterObserver>

    /**
     * Notify the death of the character to the listeners.
     */
    fun notifyDeath()

    /**
     * Adds an observer to the listener list.
     */
    fun addListener(characterObserver: CharacterObserver)

    /**
     * Apply damage to another game character.
     */
    fun attack(gameCharacter: GameCharacter)

    /**
     * Modifies the current hp of the character according to the damage received.
     */
    fun recieveDamage(damage: Int)

    /**
     * Verifies if the character is death. If the character is death, notify the death to the
     * listeners.
     */
    fun verifyDeath()

    /**
     * Sets a scheduled executor to make this character (thread) wait for `speed / 10`
     * seconds before adding the character to the queue.
     */
    fun waitTurn()

    fun applyFire(mage: Mages, fire: Fire) {
        throw InvalidSpellTargetException(fire, this)
    }

    fun applyThunder(mage: Mages, thunder: Thunder) {
        throw InvalidSpellTargetException(thunder, this)
    }

    fun applyHeal(mage: Mages, heal: Heal) {
        throw InvalidSpellTargetException(heal, this)
    }

    fun applyParalysis(mage: Mages, paralysis: Paralysis) {
        throw InvalidSpellTargetException(paralysis, this)
    }

    fun applyPoison(mage: Mages, poison: Poison) {
        throw InvalidSpellTargetException(poison, this)
    }
}
