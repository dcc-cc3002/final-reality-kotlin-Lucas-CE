package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages

class CompositeEffect(val effects: List<Effect>) : Effect {
    override fun applyEffect(mages: Mages, target: GameCharacter) {
        effects.forEach { it.applyEffect(mages, target) }
    }
}
