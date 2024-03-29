/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */
package cl.uchile.dcc.finalreality.model.character.player.common

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.AxeWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.BowWeapon
import java.util.Objects
import java.util.concurrent.BlockingQueue

/**
 * An `Engineer` is a type of [PlayerCharacter] that can equip `Axe`s and
 * `Bow`s.
 *
 * @param name        the character's name
 * @param maxHp       the character's maximum health points
 * @param defense     the character's defense
 * @param turnsQueue  the queue with the characters waiting for their turn
 * @constructor Creates a new Engineer.
 *
 * @property currentHp The current HP of the character.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
class Engineer(
    name: String,
    maxHp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractCommonCharacter(name, maxHp, defense, turnsQueue) {

    override fun equip(weapon: GameWeapon) {
        weapon.equipItToEngineer(this)
    }

    override fun equipAxe(axe: AxeWeapon) {
        _equippedWeapon = axe
    }

    override fun equipBow(bow: BowWeapon) {
        _equippedWeapon = bow
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Engineer -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        maxHp != other.maxHp -> false
        defense != other.defense -> false
        else -> true
    }

    override fun hashCode() =
        Objects.hash(Engineer::class, name, maxHp, defense)

    override fun toString() =
        "Engineer {name='$name', maxHp='$maxHp', defense='$defense'}"
}
