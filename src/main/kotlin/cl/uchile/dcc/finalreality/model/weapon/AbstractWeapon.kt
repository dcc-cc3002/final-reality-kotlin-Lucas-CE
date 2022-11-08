package cl.uchile.dcc.finalreality.model.weapon

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage

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
) : GameWeapon {

    override fun equipItToBlackMage(blackMage: BlackMage) {
        throw InvalidEquippedWeaponException(this, blackMage)
    }

    override fun equipItToEngineer(engineer: Engineer) {
        throw InvalidEquippedWeaponException(this, engineer)
    }

    override fun equipItToKnight(knight: Knight) {
        throw InvalidEquippedWeaponException(this, knight)
    }

    override fun equipItToThief(thief: Thief) {
        throw InvalidEquippedWeaponException(this, thief)
    }

    override fun equipItToWhiteMage(whiteMage: WhiteMage) {
        throw InvalidEquippedWeaponException(this, whiteMage)
    }
}
