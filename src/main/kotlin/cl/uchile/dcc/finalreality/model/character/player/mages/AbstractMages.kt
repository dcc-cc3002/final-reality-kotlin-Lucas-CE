package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedSpellException
import cl.uchile.dcc.finalreality.exceptions.Require
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.AbstractPlayerCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import java.util.concurrent.BlockingQueue

/**
 *  A abstract class that holds all the information of mage character.
 *
 * @param name        the character's name
 * @param maxHp       the character's maximum health points
 * @property maxMp       the character's maximum magic points
 * @param defense     the character's defense
 * @param turnsQueue  the queue with the characters waiting for their turn
 * @constructor Creates a new Mage.
 *
 * @property currentMp The current MP of the character.
 * @property currentHp The current HP of the character.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

abstract class AbstractMages(
    name: String,
    maxHp: Int,
    maxMp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractPlayerCharacter(name, maxHp, defense, turnsQueue), Mages {
    override val maxMp = Require.Stat(maxMp, "Max MP") atLeast 0
    var currentMp: Int = maxMp
        set(value) {
            field = Require.Stat(value, "Current MP") inRange 0..maxMp
        }

    protected lateinit var _spell: Spell
    override val spell: Spell
        get() = _spell

    override fun equipSpellFire(fire: Fire) {
        throw InvalidEquippedSpellException(fire, this)
    }

    override fun equipSpellThunder(thunder: Thunder) {
        throw InvalidEquippedSpellException(thunder, this)
    }

    override fun equipSpellHeal(heal: Heal) {
        throw InvalidEquippedSpellException(heal, this)
    }

    override fun equipSpellParalysis(paralysis: Paralysis) {
        throw InvalidEquippedSpellException(paralysis, this)
    }

    override fun equipSpellPoison(poison: Poison) {
        throw InvalidEquippedSpellException(poison, this)
    }
}
