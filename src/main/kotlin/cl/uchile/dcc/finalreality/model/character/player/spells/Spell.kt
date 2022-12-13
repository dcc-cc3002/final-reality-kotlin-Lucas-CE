package cl.uchile.dcc.finalreality.model.character.player.spells

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedSpellException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.Effect
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage

/**
 * This represents a spell from the game.
 * A spell can be equipped by [Mage].
 *
 * @property manaCost
 *    The mana points it costs to cast the spell.
 *
 * @property effects
 *    The adverse effects that the spell can produce.
 *
 */

interface Spell {

    val manaCost: Int
    val effects: Effect

    /**
     * Equip this spell to a black mage if it is a black magic spell, else throw exception.
     */
    fun equipSpellToBlackMage(blackMage: BlackMage) {
        throw InvalidEquippedSpellException(this, blackMage)
    }

    /**
     * Equip this spell to a white mage if it is a white magic spell, else throw exception.
     */
    fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        throw InvalidEquippedSpellException(this, whiteMage)
    }

    /**
     * Apply this spell from a mage, to a game character.
     */
    fun applyFromTo(from: Mage, target: GameCharacter)
}
