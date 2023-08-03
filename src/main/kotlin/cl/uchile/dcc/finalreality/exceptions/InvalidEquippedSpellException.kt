package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

/**
 * This error is used to represent equipping an invalid spell to a mage.
 *
 * @constructor Creates a new `InvalidEquippedSpellException` with a spell, and a mage.
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class InvalidEquippedSpellException(spell: Spell, mage: Mage) :
    Exception("The $spell class cannot be equipped to a $mage class")
