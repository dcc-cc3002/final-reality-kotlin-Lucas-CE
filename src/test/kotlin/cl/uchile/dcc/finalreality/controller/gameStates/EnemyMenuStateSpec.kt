package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EnemyMenuStateSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var enemyMenuState: EnemyMenuState
    lateinit var idleState: IdleState
    beforeEach() {
        gameController = GameController()
        enemyMenuState = EnemyMenuState(gameController)
        idleState = IdleState(gameController)
    }
    test("enemy menu state could change the context state to idle state") {
        gameController.setState(enemyMenuState)
        enemyMenuState.toIdleState()
        gameController.state::class shouldBe idleState::class
    }
})
