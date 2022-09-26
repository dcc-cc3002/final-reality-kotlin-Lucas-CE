package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import java.util.Objects

/**
 * A `Axe` is a type of [AbstractNormalWeapon].
 *
 * @param name              the weapon's name.
 * @param physicalDamage    the weapon's physicalDamage.
 * @param weight            the weapon's weight.
 * @constructor Creates a new Axe.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Axe(
    name: String,
    physicalDamage: Int,
    weight: Int
): AbstractNormalWeapon(name, physicalDamage, weight){

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Axe -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.physicalDamage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Axe::class, name, physicalDamage, weight)

    override fun toString() = "Axe { name: $name, damage: $physicalDamage, weight: $weight}"
}
