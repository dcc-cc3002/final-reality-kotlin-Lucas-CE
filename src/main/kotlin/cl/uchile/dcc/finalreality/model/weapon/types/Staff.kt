package cl.uchile.dcc.finalreality.model.weapon.types

import java.util.Objects

/**
 * A `Staff` is a type of [AbstractMagicWeapon].
 *
 * @param name        the weapon's name.
 * @param damage      the weapon's damage.
 * @param weight      the weapon's weight.
 * @constructor Creates a new Staff.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Staff(
    name: String,
    magicDamage: Int,
    weight: Int
) : AbstractMagicWeapon(name, magicDamage, weight) {

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Staff -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.magicDamage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Staff::class, name, magicDamage, weight)

    override fun toString() = "Staff { name: $name, damage: $magicDamage, weight: $weight}"
}
