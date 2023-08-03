package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.controller.gameStates.EnemyMenuState
import cl.uchile.dcc.finalreality.controller.gameStates.GameState
import cl.uchile.dcc.finalreality.controller.gameStates.IdleState
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class IllegalStateTransitionExceptionSpec : FunSpec({
    lateinit var context: GameController
    lateinit var actual: GameState
    lateinit var target: GameState

    beforeEach {
        context = GameController()
        actual = IdleState(context)
        target = EnemyMenuState(context)
    }

    test(
        "An illegal game sate transition exception can be thrown with the actual game state " +
            "class name, and with the target game state class name"
    ) {
        assertThrows<IllegalStateTransitionException> {
            throw IllegalStateTransitionException(actual, target)
        }.message shouldBe "$actual state does not have a transition to $target state"
    }
})