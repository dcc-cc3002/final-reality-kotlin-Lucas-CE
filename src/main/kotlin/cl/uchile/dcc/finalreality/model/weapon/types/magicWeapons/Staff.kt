package cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons

import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.AbstractMagicWeapon
import java.util.Objects

/**
 * A `Staff` is a type of [AbstractMagicWeapon].
 *
 * @param name        the weapon's name.
 * @param damage      the weapon's damage.
 * @param weight      the weapon's weight.
 * @param magicDmg    the weapon's magic damage
 * @constructor Creates a new Staff.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Staff(
    name: String,
    damage: Int,
    weight: Int,
    magicDmg: Int
) : AbstractMagicWeapon(name, damage, weight, magicDmg), StaffWeapon {

    override fun equipItToBlackMage(blackMage: BlackMage) {
        blackMage.equipStaff(this)
    }

    override fun equipItToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipStaff(this)
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Staff -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        magicDmg != other.magicDmg -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Staff::class, name, damage, weight, magicDmg)

    override fun toString() = "Staff {name='$name', damage='$damage', weight='$weight', magicDmg='$magicDmg'}"
}
