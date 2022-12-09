package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.exceptions.IllegalStateTransitionException

open class GameState(protected val context: GameController) {
    init {
        context.setState(this)
    }
    private fun transitionError(gameState: GameState) {
        throw IllegalStateTransitionException(this, gameState)
    }
    open fun toIdleState() {
        transitionError(IdleState(context))
    }
    open fun toPlayerMenuState() {
        transitionError(PlayerMenuState(context))
    }
    open fun toEnemyMenuState() {
        transitionError(EnemyMenuState(context))
    }
    open fun toDecidingTheTurnState() {
        transitionError(DecidingTheTurnState(context))
    }
}
