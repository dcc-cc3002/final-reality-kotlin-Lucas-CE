package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import java.util.Objects

/**
 * A `Axe` is a type of [AbstractNormalWeapon].
 *
 * @param name              the weapon's name.
 * @param damage            the weapon's damage.
 * @param weight            the weapon's weight.
 * @constructor Creates a new Axe.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Axe(
    name: String,
    damage: Int,
    weight: Int
) : AbstractNormalWeapon(name, damage, weight), EngineerWeapon, KnightWeapon {

    override fun equipItToEngineer(engineer: Engineer) {
        engineer.equipWeapon(this)
    }

    override fun equipItToKnight(knight: Knight) {
        knight.equipWeapon(this)
    }

    override fun equipItToThief(thief: Thief) {
        throw AssertionError("This weapon cannot be equipped to a Thief")
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Axe -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Axe::class, name, damage, weight)

    override fun toString() = "Axe {name='$name', damage='$damage', weight='$weight'}"
}
