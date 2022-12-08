package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.controller.CharacterObserver
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage

interface Effect {
    fun applyEffect(from: Mage, target: GameCharacter) {
        target.effectApplied(this)
    }
    fun notifyEffectApplied(gameCharacter: GameCharacter, listeners: MutableList<CharacterObserver>)
}
