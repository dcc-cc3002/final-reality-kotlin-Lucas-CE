package cl.uchile.dcc.finalreality.model.weapon

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
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

interface GameWeapon {
    val name: String
    val damage: Int
    val weight: Int
}
