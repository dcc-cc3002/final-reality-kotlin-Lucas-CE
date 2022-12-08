package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class PullingCharacterState(context: GameController) : GameState(context) {

    override fun toEnemyMenuState() {
        context.setState(EnemyMenuState(context))
    }
    override fun toPlayerMenuState() {
        context.setState(PlayerMenuState(context))
    }
}
