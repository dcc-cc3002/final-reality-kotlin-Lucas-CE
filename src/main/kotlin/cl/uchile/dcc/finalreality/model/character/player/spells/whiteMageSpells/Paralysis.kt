package cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.CompositeEffect
import cl.uchile.dcc.finalreality.model.character.player.effects.Paralyzed
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Paralysis : Spell {

    override val effects = CompositeEffect(listOf(Paralyzed()))
    override val manaCost = 25

    override fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipSpellParalysis(this)
    }

    override fun applyFromTo(from: Mage, target: GameCharacter) {
        target.applyParalysis(from, this)
    }

    fun applyParalysisFromTo(from: Mage, target: GameCharacter) {
        effects.applyEffect(from, target)
    }
}
