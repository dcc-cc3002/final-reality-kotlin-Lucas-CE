package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class GameFinishedState(context: GameController) : GameState(context) {
    override fun toIdleState() {
        context.setState(IdleState(context))
    }
}
