package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage

class NullEffect : Effect {
    override fun applyEffect(from: Mage, target: GameCharacter) {
    }
}