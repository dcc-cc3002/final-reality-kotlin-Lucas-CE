package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

/**
 * This error is used to represent throw a spell to a invalid target.
 *
 * @constructor Creates a new `InvalidSpellTargetException` with a spell, and a game character
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class InvalidSpellTargetException(spell: Spell, target: GameCharacter) :
    Exception("The $spell class cannot target a $target class")
