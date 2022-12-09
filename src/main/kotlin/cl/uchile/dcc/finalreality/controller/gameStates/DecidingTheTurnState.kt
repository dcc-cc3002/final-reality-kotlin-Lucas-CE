package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class DecidingTheTurnState(context: GameController) : GameState(context) {
    init {
        context.decideTurn()
    }
    override fun toEnemyMenuState() {
        context.setState(EnemyMenuState(context))
    }
    override fun toPlayerMenuState() {
        context.setState(PlayerMenuState(context))
    }
}
