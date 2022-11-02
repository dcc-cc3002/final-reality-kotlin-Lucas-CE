package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
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
    damage: Int,
    weight: Int
) : AbstractMagicWeapon(name, damage, weight), BlackMageWeapon {

    override fun equipItToEngineer(engineer: Engineer) {
        throw AssertionError("This weapon cannot be equipped to an Engineer")
    }

    override fun equipItToKnight(knight: Knight) {
        throw AssertionError("This weapon cannot be equipped to a Knight")
    }

    override fun equipItToThief(thief: Thief) {
        throw AssertionError("This weapon cannot be equipped to a Thief")
    }

    override fun equipItToBlackMage(blackMage: BlackMage) {
        blackMage.equipWeapon(this)
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Staff -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Staff::class, name, damage, weight)

    override fun toString() = "Staff {name='$name', damage='$damage', weight='$weight'}"
}
