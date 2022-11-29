package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

/**
 * This error is used to represent equipping an invalid weapon.
 *
 * @constructor Creates a new `InvalidEquippedWeaponException` with a weapon, and a player character
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class InvalidEquippedSpellException(spell: Spell, player: PlayerCharacter) :
    Exception("The $spell class cannot be equipped to a $player class")
