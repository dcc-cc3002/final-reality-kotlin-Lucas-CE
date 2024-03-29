/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */
package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.KnifeWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.StaffWeapon
import java.util.Objects
import java.util.concurrent.BlockingQueue

/**
 * A Black Mage is a type of [AbstractMage] that can cast black magic.
 *
 * @param name        the character's name
 * @param maxHp       the character's maximum health points
 * @param maxMp       the character's maximum magic points
 * @param defense     the character's defense
 * @param turnsQueue  the queue with the characters waiting for their turn
 * @constructor Creates a new Black Mage.
 *
 * @property currentMp The current MP of the character.
 * @property currentHp The current HP of the character.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
class BlackMage(
    name: String,
    maxHp: Int,
    maxMp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractMage(name, maxHp, maxMp, defense, turnsQueue) {

    override fun equip(weapon: GameWeapon) {
        weapon.equipItToBlackMage(this)
    }

    override fun equipKnife(knife: KnifeWeapon) {
        _equippedWeapon = knife
    }

    override fun equipStaff(staff: StaffWeapon) {
        _equippedWeapon = staff
    }

    fun equipSpell(spell: Spell) {
        spell.equipSpellToBlackMage(this)
    }

    override fun equipSpellFire(fire: Fire) {
        _spell = fire
    }

    override fun equipSpellThunder(thunder: Thunder) {
        _spell = thunder
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is BlackMage -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        maxHp != other.maxHp -> false
        maxMp != other.maxMp -> false
        defense != other.defense -> false
        else -> true
    }

    override fun hashCode() =
        Objects.hash(BlackMage::class, name, maxHp, maxMp, defense)

    override fun toString() =
        "BlackMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', defense='$defense'}"
}
