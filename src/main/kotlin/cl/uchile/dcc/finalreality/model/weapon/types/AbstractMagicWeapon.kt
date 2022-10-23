package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.weapon.AbstractWeapon

/**
 * A class that holds all the information of a weapon with magic damage.
 *
 * @param name           the weapon's name.
 * @param damage    the weapon's magicDamage.
 * @param weight         the weapon's speed.
 * @constructor Creates a new magic weapon.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

abstract class AbstractMagicWeapon(
    name: String,
    override val damage: Int,
    weight: Int
) : AbstractWeapon(name, weight)
