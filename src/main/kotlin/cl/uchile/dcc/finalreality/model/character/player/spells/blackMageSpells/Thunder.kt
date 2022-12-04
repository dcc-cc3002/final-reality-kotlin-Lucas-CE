package cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.CompositeEffect
import cl.uchile.dcc.finalreality.model.character.player.effects.Paralyzed
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Thunder : Spell {

    override val manaCost = 15
    override val effects = CompositeEffect(listOf(Paralyzed()))

    override fun equipSpellToBlackMage(blackMage: BlackMage) {
        blackMage.equipSpellThunder(this)
    }

    override fun applyFromTo(from: Mage, target: GameCharacter) {
        target.applyThunder(from, this)
    }

    fun applyThunderFromTo(from: Mage, target: GameCharacter) {
        target.recieveDamage(from.equippedWeapon.magicDmg)
        if (Math.random() < 0.3) {
            effects.applyEffect(from, target)
        }
    }
}
