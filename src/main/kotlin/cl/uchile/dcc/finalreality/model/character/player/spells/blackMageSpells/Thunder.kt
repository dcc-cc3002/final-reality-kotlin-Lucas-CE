package cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Thunder : Spell {

    override val manaCost = 15

    override fun equipSpellToBlackMage(blackMage: BlackMage) {
        blackMage.equipSpellThunder(this)
    }

    override fun applyFromTo(mage: Mages, target: GameCharacter) {
        target.currentHp -= mage.equippedWeapon.magicDmg

    }
}
