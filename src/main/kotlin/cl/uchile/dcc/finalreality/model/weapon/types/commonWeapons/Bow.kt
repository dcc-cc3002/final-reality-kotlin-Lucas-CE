package cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.AbstractNormalWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.EngineerWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.ThiefWeapon
import java.util.Objects

/**
 * A `Bow` is a type of [AbstractNormalWeapon].
 *
 * @param name              the weapon's name.
 * @param damage            the weapon's damage.
 * @param weight            the weapon's weight.
 * @constructor Creates a new Bow.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Bow(
    name: String,
    damage: Int,
    weight: Int
) : AbstractNormalWeapon(name, damage, weight), EngineerWeapon, ThiefWeapon {

    override fun equipItToEngineer(engineer: Engineer) {
        engineer.equipWeapon(this)
    }

    override fun equipItToKnight(knight: Knight) {
        throw InvalidEquippedWeaponException("Bow", "Knight")
    }

    override fun equipItToThief(thief: Thief) {
        thief.equipWeapon(this)
    }

    override fun equipItToBlackMage(blackMage: BlackMage) {
        throw InvalidEquippedWeaponException("Bow", "Black Mage")
    }

    override fun equipItToWhiteMage(whiteMage: WhiteMage) {
        throw InvalidEquippedWeaponException("Bow", "White Mage")
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Bow -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Bow::class, name, damage, weight)

    override fun toString() = "Bow {name='$name', damage='$damage', weight='$weight'}"
}
