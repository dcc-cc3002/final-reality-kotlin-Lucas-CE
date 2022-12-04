/*
 * "Final Reality" (c) by R8V and Lucase
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */
package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.AbstractCharacter
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.AxeWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.BowWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.KnifeWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.SwordWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.StaffWeapon
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * A character controlled by the user.
 *
 * @property equippedWeapon
 *    the weapon that the character is currently using
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
interface PlayerCharacter : GameCharacter {
    val equippedWeapon: GameWeapon

    /**
     * Equips a weapon to the character.
     */
    fun equip(weapon: GameWeapon)

    /**
     * Equip an Axe to a character that can equip an Axe, if the character can't,
     * throws an exception
     */
    fun equipAxe(axe: AxeWeapon)

    /**
     * Equip a Bow to a character that can equip a Bow, if the character can't,
     * throws an exception
     */
    fun equipBow(bow: BowWeapon)

    /**
     * Equip a Knife to a character that can equip a Knife, if the character can't,
     * throws an exception
     */
    fun equipKnife(knife: KnifeWeapon)

    /**
     * Equip a Staff to a character that can equip a Staff, if the character can't,
     * throws an exception
     */
    fun equipStaff(staff: StaffWeapon)

    /**
     * Equip a Sword to a character that can equip a Sword, if the character can't,
     * throws an exception
     */
    fun equipSword(sword: SwordWeapon)
}

/**
 * A class that holds all the information of a player-controlled character in the game.
 *
 * @param name        the character's name
 * @param maxHp       the character's maximum health points
 * @param defense     the character's defense
 * @param turnsQueue  the queue with the characters waiting for their turn
 * @constructor Creates a new playable character.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
abstract class AbstractPlayerCharacter(
    name: String,
    maxHp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractCharacter(name, maxHp, defense, turnsQueue), PlayerCharacter {

    protected lateinit var _equippedWeapon: GameWeapon
    override val equippedWeapon: GameWeapon
        get() = _equippedWeapon

    override fun attack(gameCharacter: GameCharacter) {
        gameCharacter.recieveDamage(this.equippedWeapon.damage)
    }

    override fun notifyDeath() {
        for (listener in characterListeners) {
            listener.updateDeathPlayerCharacter(this)
        }
    }

    override fun waitTheirTurn(scheduledExecutor: ScheduledExecutorService) {
        scheduledExecutor.schedule(
            /* command = */ ::addToQueue,
            /* delay = */ (this.equippedWeapon.weight / 10).toLong(),
            /* unit = */ TimeUnit.SECONDS
        )
    }

    override fun equipAxe(axe: AxeWeapon) {
        throw InvalidEquippedWeaponException(axe, this)
    }

    override fun equipBow(bow: BowWeapon) {
        throw InvalidEquippedWeaponException(bow, this)
    }

    override fun equipKnife(knife: KnifeWeapon) {
        throw InvalidEquippedWeaponException(knife, this)
    }

    override fun equipSword(sword: SwordWeapon) {
        throw InvalidEquippedWeaponException(sword, this)
    }

    override fun equipStaff(staff: StaffWeapon) {
        throw InvalidEquippedWeaponException(staff, this)
    }

    override fun applyHeal(from: Mage, heal: Heal) {
        heal.applyHealFromTo(from, this)
    }
}
