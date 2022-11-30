package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.model.character.GameCharacter

class CompositeEffect(val effects: List<Effect>) : Effect {
    override fun applyEffect(target: GameCharacter) {
        effects.forEach { it.applyEffect(target) }
    }
}
