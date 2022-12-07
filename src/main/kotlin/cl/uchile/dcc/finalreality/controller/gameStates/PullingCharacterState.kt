package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController

class PullingCharacterState(context: GameController) : GameState(context) {
    override fun toEnemyMenu() {
        context.setState(EnemyMenuState(context))
    }
    override fun toPlayerMenu() {
        context.setState(PlayerMenuState(context))
    }
}
