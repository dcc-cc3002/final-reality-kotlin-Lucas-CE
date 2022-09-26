package cl.uchile.dcc.finalreality.model.weapon.types

import java.util.Objects

/**
 * A `Sword` is a type of [AbstractNormalWeapon].
 *
 * @param name        the weapon's name.
 * @param damage      the weapon's damage.
 * @param weight      the weapon's weight.
 * @constructor Creates a new Sword.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author ~Your name~
 */

class Sword(
    name: String,
    damage: Int,
    weight: Int
): AbstractNormalWeapon(name, damage, weight){

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Sword -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Sword::class, name, damage, weight)

    override fun toString() = "Sword { name: $name, damage: $damage, weight: $weight}"
}