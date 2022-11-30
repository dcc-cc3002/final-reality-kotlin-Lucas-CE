package cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.Effect
import cl.uchile.dcc.finalreality.model.character.player.effects.Paralyzed
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Paralysis : Spell {

    override val effect: Effect = Paralyzed()
    override val manaCost = 25

    override fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipSpellParalysis(this)
    }

    override fun applyFromTo(mage: Mages, target: GameCharacter) {
        target.applyParalysis(mage, this)
    }

    fun applyParalysisFromTo(target: GameCharacter) {
        effect.applyEffect(target)
    }
}
