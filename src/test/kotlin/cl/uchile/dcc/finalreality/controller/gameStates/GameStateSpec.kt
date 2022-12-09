package cl.uchile.dcc.finalreality.controller.gameStates

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.exceptions.IllegalStateTransitionException
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.assertThrows

class GameStateSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var idleState: IdleState
    lateinit var decidingTheTurnState: DecidingTheTurnState
    lateinit var playerMenuState: PlayerMenuState
    lateinit var enemyMenuState: EnemyMenuState
    beforeEach() {
        gameController = GameController()
        idleState = IdleState(gameController)
        decidingTheTurnState = DecidingTheTurnState(gameController)
        playerMenuState = PlayerMenuState(gameController)
        enemyMenuState = EnemyMenuState(gameController)
    }
    test("Transitions to illegal states throws exception") {
        // From idleState
        assertThrows<IllegalStateTransitionException> { idleState.toPlayerMenuState() }
        assertThrows<IllegalStateTransitionException> { idleState.toEnemyMenuState() }
        assertThrows<IllegalStateTransitionException> { idleState.toIdleState() }
        // From decidingTheTurnState
        assertThrows<IllegalStateTransitionException> { decidingTheTurnState.toIdleState() }
        assertThrows<IllegalStateTransitionException> { decidingTheTurnState.toDecidingTheTurnState() }
        // From playerMenuState
        assertThrows<IllegalStateTransitionException> { playerMenuState.toEnemyMenuState() }
        assertThrows<IllegalStateTransitionException> { playerMenuState.toDecidingTheTurnState() }
        assertThrows<IllegalStateTransitionException> { playerMenuState.toPlayerMenuState() }
        // From enemyMenuState
        assertThrows<IllegalStateTransitionException> { enemyMenuState.toPlayerMenuState() }
        assertThrows<IllegalStateTransitionException> { enemyMenuState.toDecidingTheTurnState() }
        assertThrows<IllegalStateTransitionException> { enemyMenuState.toEnemyMenuState() }
    }
})