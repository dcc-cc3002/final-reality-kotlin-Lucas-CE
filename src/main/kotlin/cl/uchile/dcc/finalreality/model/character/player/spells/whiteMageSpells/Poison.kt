package cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.CompositeEffect
import cl.uchile.dcc.finalreality.model.character.player.effects.Poisoned
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Poison : Spell {

    override val manaCost = 40
    override val effects = CompositeEffect(listOf(Poisoned()))

    override fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipSpellPoison(this)
    }

    override fun applyFromTo(from: Mage, target: GameCharacter) {
        target.applyPoison(from, this)
    }

    fun applyPoisonFromTo(from: Mage, target: GameCharacter) {
        effects.applyEffect(from, target)
    }
}
