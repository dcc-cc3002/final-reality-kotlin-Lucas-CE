package cl.uchile.dcc.finalreality.model.character.player.spells

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedSpellException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.Effect
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage

interface Spell {

    val manaCost: Int
    val effect: Effect

    fun equipSpellToBlackMage(blackMage: BlackMage) {
        throw InvalidEquippedSpellException(this, blackMage)
    }

    fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        throw InvalidEquippedSpellException(this, whiteMage)
    }

    fun applyFromTo(mage: Mages, target: GameCharacter)
}
