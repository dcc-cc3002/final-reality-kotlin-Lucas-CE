package cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.CompositeEffect
import cl.uchile.dcc.finalreality.model.character.player.effects.Paralyzed
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Thunder : Spell {

    override val manaCost = 15
    override val effect = CompositeEffect(listOf(Paralyzed()))

    override fun equipSpellToBlackMage(blackMage: BlackMage) {
        blackMage.equipSpellThunder(this)
    }

    override fun applyFromTo(mage: Mages, target: GameCharacter) {
        target.applyThunder(mage, this)
    }

    fun applyThunderFromTo(mage: Mages, target: GameCharacter) {
        target.currentHp -= mage.equippedWeapon.magicDmg
        if (Math.random() < 0.3) {
            effect.applyEffect(mage, target)
        }
    }
}
