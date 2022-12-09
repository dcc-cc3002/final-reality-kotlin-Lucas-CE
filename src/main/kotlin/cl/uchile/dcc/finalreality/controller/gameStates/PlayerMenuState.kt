package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class PlayerMenuState(context: GameController) : GameState(context) {
    override fun toIdleState() {
        context.setState(IdleState(context))
        context.verifyWin()
    }
}
