package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.exceptions.Require
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import java.util.Objects
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @param name The name of this enemy.
 * @property weight The weight of this enemy.
 * @param turnsQueue The queue with the characters waiting for their turn.
 * @param maxHp The maximum health points of this enemy.
 * @param defense The defense of this enemy.
 *
 * @constructor Creates a new enemy with a name, a weight and the queue with the characters ready to
 *  play.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */
class Enemy(
    name: String,
    weight: Int,
    maxHp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractCharacter(name, maxHp, defense, turnsQueue) {

    val weight = Require.Stat(weight, "Weight") atLeast 1

    override fun attack(gameCharacter: GameCharacter) {
        gameCharacter.receiveDamage(this.weight / 2)
    }

    override fun notifyDeath() {
        for (listener in characterListeners) {
            listener.updateDeathEnemy(this)
        }
    }

    override fun waitTheirTurn(scheduledExecutor: ScheduledExecutorService) {
        scheduledExecutor.schedule(
            /* command = */ ::addToQueue,
            /* delay = */ (this.weight / 10).toLong(),
            /* unit = */ TimeUnit.SECONDS
        )
    }

    override fun applyFire(from: Mage, fire: Fire) {
        fire.applyFireFromTo(from, this)
    }

    override fun applyThunder(from: Mage, thunder: Thunder) {
        thunder.applyThunderFromTo(from, this)
    }

    override fun applyParalysis(from: Mage, paralysis: Paralysis) {
        paralysis.applyParalysisFromTo(from, this)
    }

    override fun applyPoison(from: Mage, poison: Poison) {
        poison.applyPoisonFromTo(from, this)
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Enemy -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        weight != other.weight -> false
        maxHp != other.maxHp -> false
        defense != other.defense -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Enemy::class, name, weight, maxHp, defense)
    override fun toString() =
        "Enemy {name='$name', weight='$weight', maxHp='$maxHp', defense='$defense'}"
}
