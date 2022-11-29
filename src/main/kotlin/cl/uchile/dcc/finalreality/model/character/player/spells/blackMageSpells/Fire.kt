package cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells

import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Fire : Spell {

    override val manaCost = 15

    override fun equipSpellToBlackMage(blackMage: BlackMage) {
        blackMage.equipSpellFire(this)
    }
}
