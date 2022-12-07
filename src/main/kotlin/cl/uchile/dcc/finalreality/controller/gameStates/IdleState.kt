package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class IdleState(context: GameController) : GameState(context) {
    override fun toPullingCharacterState() {
        context.setState(PullingCharacterState(context))
    }
}
