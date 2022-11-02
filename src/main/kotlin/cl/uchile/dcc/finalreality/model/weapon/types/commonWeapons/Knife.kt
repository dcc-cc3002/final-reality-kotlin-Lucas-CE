package cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons

import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.AbstractNormalWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.BlackMageWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.KnightWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.ThiefWeapon
import java.util.Objects

/**
 * A `Knife` is a type of [AbstractNormalWeapon].
 *
 * @param name              the weapon's name.
 * @param damage    the weapon's damage.
 * @param weight            the weapon's weight.
 * @constructor Creates a new Knife.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Knife(
    name: String,
    damage: Int,
    weight: Int
) : AbstractNormalWeapon(name, damage, weight), KnightWeapon, ThiefWeapon, BlackMageWeapon {

    override fun equipItToEngineer(engineer: Engineer) {
        throw AssertionError("This weapon cannot be equipped to an Engineer")
    }

    override fun equipItToKnight(knight: Knight) {
        knight.equipWeapon(this)
    }

    override fun equipItToThief(thief: Thief) {
        thief.equipWeapon(this)
    }

    override fun equipItToBlackMage(blackMage: BlackMage) {
        blackMage.equipWeapon(this)
    }

    override fun equipItToWhiteMage(whiteMage: WhiteMage) {
        throw AssertionError("This weapon cannot be equipped to a White mage")
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Knife -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Knife::class, name, damage, weight)

    override fun toString() = "Knife {name='$name', damage='$damage', weight='$weight'}"
}
