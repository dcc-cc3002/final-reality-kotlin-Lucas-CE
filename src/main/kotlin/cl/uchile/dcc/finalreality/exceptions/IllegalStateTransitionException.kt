package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.controller.gameStates.GameState

/**
 * This error is used to represent a wrong transition state.
 *
 * @constructor Creates a new `IllegalStateTransitionException` with two Game states.
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class IllegalStateTransitionException(actual: GameState, target: GameState) :
    Exception("$actual state does not have a transition to $target state")
