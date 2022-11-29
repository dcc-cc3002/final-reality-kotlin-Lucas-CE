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
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.StaffWeapon
import java.util.Objects
import java.util.concurrent.BlockingQueue

/**
 * A White Mage is a type of [PlayerCharacter] that can cast white magic.
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
class WhiteMage(
    name: String,
    maxHp: Int,
    maxMp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractMages(name, maxHp, maxMp, defense, turnsQueue) {

    override fun equip(weapon: GameWeapon) {
        weapon.equipItToWhiteMage(this)
    }

    override fun equipStaff(staff: StaffWeapon) {
        _equippedWeapon = staff
    }

    fun equipSpell(spell: Spell) {
        spell.equipSpellToWhiteMage(this)
    }

    override fun equipSpellHeal(heal: Heal) {
        _spell = heal
    }

    override fun equipSpellParalysis(paralysis: Paralysis) {
        _spell = paralysis
    }

    override fun equipSpellPoison(poison: Poison) {
        _spell = poison
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is WhiteMage -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        maxHp != other.maxHp -> false
        maxMp != other.maxMp -> false
        defense != other.defense -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(WhiteMage::class, name, maxHp, maxMp, defense)

    override fun toString() =
        "WhiteMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', defense='$defense'}"
}
