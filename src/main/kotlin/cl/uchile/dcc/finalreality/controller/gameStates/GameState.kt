package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.exceptions.IllegalStateTransitionException

open class GameState(protected val context: GameController) {
    init {
        context.setState(this)
    }
    private fun transitionError(gameState: String) {
        throw IllegalStateTransitionException(this, gameState)
    }
    open fun toIdleState() {
        transitionError("IdleState")
    }
    open fun toPlayerMenu() {
        transitionError("PlayerMenu")
    }
    open fun toEnemyMenu() {
        transitionError("EnemyMenu")
    }
    open fun toPullingCharacterState() {
        transitionError("PullingCharacter")
    }
    open fun toGameFinishedState() {
        transitionError("GameFinishedState")
    }
}
