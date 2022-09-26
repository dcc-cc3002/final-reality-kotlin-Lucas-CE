package cl.uchile.dcc.finalreality.model.weapon

/**
 * A class that holds all the information of a weapon.
 *
 * @property name String
 *     The name of the weapon.
 * @property damage Int
 *     The base damage done by the weapon.
 * @property weight Int
 *     The weight is the speed of a gun.
 *
 * @constructor Creates a weapon with a name, a base damage, speed.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
abstract class AbstractWeapon(
    override val name: String,
    override val damage: Int,
    override val weight: Int
) : GameWeapon
