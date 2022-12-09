package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerMenuStateSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var playerMenuState: PlayerMenuState
    lateinit var idleState: IdleState
    beforeEach() {
        gameController = GameController()
        playerMenuState = PlayerMenuState(gameController)
        idleState = IdleState(gameController)
    }
    test("player menu state could change the context state to idle state") {
        gameController.setState(playerMenuState)
        playerMenuState.toIdleState()
        gameController.state::class shouldBe idleState::class
    }
})