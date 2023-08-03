package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison

/**
 * This represents a mage from the game.
 * A mage is a type of player character that can equip and use magic.
 *
 * @property maxMp
 *    The maximum mana points of the character.
 *
 * @property spell
 *    The spell that the mage is currently using
 *
 */

interface Mage : PlayerCharacter {

    val maxMp: Int
    val spell: Spell

    /**
     * Apply the spell effects against a character.
     */
    fun throwSpell(target: GameCharacter)

    /**
     * Equip a fire spell to a mage that can equip fire, if the mage can't,
     * throws an invalid equipped spell exception.
     */
    fun equipSpellFire(fire: Fire)

    /**
     * Equip a thunder spell to a mage that can equip thunder, if the mage can't,
     * throws an invalid equipped spell exception.
     */
    fun equipSpellThunder(thunder: Thunder)

    /**
     * Equip a healing spell to a mage that can equip healing spell, if the mage can't,
     * throws an invalid equipped spell exception.
     */
    fun equipSpellHeal(heal: Heal)

    /**
     * Equip a paralysis spell to a mage that can equip paralysis, if the mage can't,
     * throws an invalid equipped spell exception.
     */
    fun equipSpellParalysis(paralysis: Paralysis)

    /**
     * Equip a poison spell to a mage that can equip poison, if the mage can't,
     * throws an invalid equipped spell exception.
     */
    fun equipSpellPoison(poison: Poison)
}
