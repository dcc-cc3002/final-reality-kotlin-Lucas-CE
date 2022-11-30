package cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.Poisoned
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Poison : Spell {

    override val manaCost = 40
    override val effect = Poisoned()

    override fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipSpellPoison(this)
    }

    override fun applyFromTo(mage: Mages, target: GameCharacter) {
        target.applyPoison(mage, this)
    }

    fun applyPoisonFromTo(target: GameCharacter) {
        effect.applyEffect(target)
    }
}
