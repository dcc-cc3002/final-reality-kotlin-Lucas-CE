package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage

class CompositeEffect(val effects: List<Effect>) : Effect {
    override fun applyEffect(from: Mage, target: GameCharacter) {
        effects.forEach { it.applyEffect(from, target) }
    }
}
