package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class DecidingTheTurnState(context: GameController) : GameState(context) {
    override fun toEnemyMenuState() {
        EnemyMenuState(context)
    }
    override fun toPlayerMenuState() {
        PlayerMenuState(context)
    }
    override fun toIdleState() {
        IdleState(context)
    }
}
