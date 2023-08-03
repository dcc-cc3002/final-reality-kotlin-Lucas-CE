package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.controller.CharacterObserver
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage

class Paralyzed : Effect {
    override fun applyEffect(from: Mage, target: GameCharacter) {
        target.effectApplied(this)
    }

    override fun notifyEffectApplied(
        gameCharacter: GameCharacter,
        listeners: MutableList<CharacterObserver>
    ) {
        for (listener in listeners) {
            listener.updateParalyzedEffect(gameCharacter)
        }
    }
}
