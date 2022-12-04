package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage

interface Effect {
    fun applyEffect(from: Mage, target: GameCharacter)
}
