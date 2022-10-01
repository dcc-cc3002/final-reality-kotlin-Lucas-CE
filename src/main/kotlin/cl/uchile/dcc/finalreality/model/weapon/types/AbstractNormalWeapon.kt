package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.weapon.AbstractWeapon

/**
 * A class that holds all the information of a weapon with physical damage.
 *
 * @param name              the weapon's name.
 * @param physicalDamage    the weapon's damage.
 * @param weight            the weapon's speed.
 * @constructor Creates a new normal weapon.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

abstract class AbstractNormalWeapon(
    name: String,
    val physicalDamage: Int,
    weight: Int
) : AbstractWeapon(name, weight) {
    override val damage = physicalDamage
}
