package cl.uchile.dcc.finalreality.model.weapon

import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @property name
 *    The name of the weapon.
 * @property damage
 *    The damage to the weapon.
 * @property weight
 *    The weight of this weapon.
 * @property magicDmg
 *    The magic damage to the weapon.
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

interface GameWeapon {
    val name: String
    val damage: Int
    val weight: Int
    val magicDmg: Int

    /**
     * Equips this weapon to an engineer, if this weapon is EngineerWeapon
     */
    fun equipItToEngineer(engineer: Engineer)

    /**
     * Equips this weapon to a knight, if this weapon is KnightWeapon
     */
    fun equipItToKnight(knight: Knight)

    /**
     * Equips this weapon to a thief, if this weapon is ThiefWeapon
     */
    fun equipItToThief(thief: Thief)

    /**
     * Equips this weapon to a black mage, if this weapon is BlackMageWeapon
     */
    fun equipItToBlackMage(blackMage: BlackMage)

    /**
     * Equips this weapon to a white mage, if this weapon is WhiteMageWeapon
     */
    fun equipItToWhiteMage(whiteMage: WhiteMage)
}
