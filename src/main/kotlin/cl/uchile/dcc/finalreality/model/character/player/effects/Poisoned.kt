package cl.uchile.dcc.finalreality.model.character.player.effects

import cl.uchile.dcc.finalreality.controller.CharacterObserver
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage

class Poisoned : Effect {
    override fun applyEffect(from: Mage, target: GameCharacter) {
        target.magicDamagePoison = from.equippedWeapon.magicDmg / 3
        target.effectApplied(this)
    }

    override fun notifyEffectApplied(
        gameCharacter: GameCharacter,
        listeners: MutableList<CharacterObserver>
    ) {
        for (listener in listeners) {
            listener.updatePoisonedEffect(gameCharacter)
        }
    }
}
