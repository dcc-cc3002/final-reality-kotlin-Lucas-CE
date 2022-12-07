package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class EnemyMenuState(context: GameController) : GameState(context) {
    override fun toPullingCharacterState() {
        context.setState(PullingCharacterState(context))
    }
    override fun toGameFinishedState() {
        context.setState(GameFinishedState(context))
    }
}
