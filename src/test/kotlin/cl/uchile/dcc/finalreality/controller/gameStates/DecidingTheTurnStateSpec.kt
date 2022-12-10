package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DecidingTheTurnStateSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var decidingTheTurnState: DecidingTheTurnState
    lateinit var playerMenuState: PlayerMenuState
    lateinit var enemyMenuState: EnemyMenuState
    lateinit var idleState: IdleState
    beforeEach() {
        gameController = GameController()
        decidingTheTurnState = DecidingTheTurnState(gameController)
        playerMenuState = PlayerMenuState(gameController)
        enemyMenuState = EnemyMenuState(gameController)
        idleState = IdleState(gameController)
    }
    test("deciding turn state could change the context state to player menu state") {
        gameController.setState(decidingTheTurnState)
        decidingTheTurnState.toPlayerMenuState()
        gameController.state::class shouldBe playerMenuState::class
    }
    test("deciding turn state could change the context state to enemy menu state") {
        gameController.setState(decidingTheTurnState)
        decidingTheTurnState.toEnemyMenuState()
        gameController.state::class shouldBe enemyMenuState::class
    }
    test("deciding turn state could change the context state to idle state") {
        gameController.setState(decidingTheTurnState)
        decidingTheTurnState.toIdleState()
        gameController.state::class shouldBe idleState::class
    }
})
