package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.controller.CharacterObserver
import cl.uchile.dcc.finalreality.exceptions.InvalidSpellTargetException
import cl.uchile.dcc.finalreality.exceptions.Require
import cl.uchile.dcc.finalreality.model.character.player.effects.Effect
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @property name
 *    The name of the character.
 * @property maxHp
 *    The maximum health points of the character.
 * @property defense
 *    The defense of the character.
 * @property turnsQueue
 *    The queue with the characters waiting for their turn.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
abstract class AbstractCharacter(
    override val name: String,
    maxHp: Int,
    defense: Int,
    private val turnsQueue: BlockingQueue<GameCharacter>
) : GameCharacter {

    override val characterListeners = mutableListOf<CharacterObserver>()

    private lateinit var scheduledExecutor: ScheduledExecutorService

    override val maxHp = Require.Stat(maxHp, "Max Hp") atLeast 1

    override var currentHp = maxHp
        set(value) {
            field = Require.Stat(value, "Current Hp") inRange 0..maxHp
        }

    override val defense = Require.Stat(defense, "Defense") atLeast 0

    override var magicDamageFire = 0

    override var magicDamagePoison = 0

    override fun addListener(characterObserver: CharacterObserver) {
        characterListeners.add(characterObserver)
    }

    override fun receiveDamage(damage: Int) {
        if (damage in defense..currentHp + defense) {
            currentHp = currentHp - damage + defense
        } else if (damage - defense > 0) {
            currentHp = 0
            this.notifyDeath()
        }
    }

    override fun receiveCure(cure: Int) {
        if (cure + currentHp <= maxHp) {
            currentHp += cure
        } else {
            currentHp = maxHp
        }
    }

    override fun waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor()
        this.waitTheirTurn(scheduledExecutor)
    }

    /**
     * Abstraction of waitTurn to define it in each different AbstractCharacter
     */
    protected abstract fun waitTheirTurn(scheduledExecutor: ScheduledExecutorService)

    /**
     * Adds this character to the turns queue.
     */
    protected fun addToQueue() {
        turnsQueue.put(this)
        scheduledExecutor.shutdown()
    }

    override fun applyFire(from: Mage, fire: Fire) {
        throw InvalidSpellTargetException(fire, this)
    }

    override fun applyThunder(from: Mage, thunder: Thunder) {
        throw InvalidSpellTargetException(thunder, this)
    }

    override fun applyHeal(from: Mage, heal: Heal) {
        throw InvalidSpellTargetException(heal, this)
    }

    override fun applyParalysis(from: Mage, paralysis: Paralysis) {
        throw InvalidSpellTargetException(paralysis, this)
    }

    override fun applyPoison(from: Mage, poison: Poison) {
        throw InvalidSpellTargetException(poison, this)
    }

    override fun effectApplied(effect: Effect) {
        effect.notifyEffectApplied(this, characterListeners)
    }
}
