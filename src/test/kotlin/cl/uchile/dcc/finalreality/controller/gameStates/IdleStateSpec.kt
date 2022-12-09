package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class IdleStateSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var idleState: IdleState
    lateinit var decidingTheTurnState: DecidingTheTurnState
    beforeEach() {
        gameController = GameController()
        idleState = IdleState(gameController)
        decidingTheTurnState = DecidingTheTurnState(gameController)
    }
    test("idle state could change the context state to deciding turn state") {
        gameController.setState(idleState)
        idleState.toDecidingTheTurnState()
        gameController.state::class shouldBe decidingTheTurnState::class
    }
})