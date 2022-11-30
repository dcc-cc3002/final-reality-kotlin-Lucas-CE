package cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.NullEffect
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Heal : Spell {

    override val manaCost = 15
    override val effect = NullEffect()

    override fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipSpellHeal(this)
    }

    override fun applyFromTo(mage: Mages, target: GameCharacter) {
        target.applyHeal(mage, this)
    }

    fun applyHealFromTo(target: GameCharacter) {
        target.currentHp += (target.currentHp * 0.3).toInt()
        effect.applyEffect(target)
    }
}
