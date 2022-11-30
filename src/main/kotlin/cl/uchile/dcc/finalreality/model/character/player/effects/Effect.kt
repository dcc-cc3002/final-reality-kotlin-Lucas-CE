package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.model.character.GameCharacter

interface Effect {
    fun applyEffect(target: GameCharacter)
}
