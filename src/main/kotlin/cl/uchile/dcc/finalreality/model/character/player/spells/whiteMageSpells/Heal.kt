package cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.NullEffect
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

class Heal : Spell {

    override val manaCost = 15
    override val effects = NullEffect()

    override fun equipSpellToWhiteMage(whiteMage: WhiteMage) {
        whiteMage.equipSpellHeal(this)
    }

    override fun applyFromTo(from: Mage, target: GameCharacter) {
        target.applyHeal(from, this)
    }

    fun applyHealFromTo(from: Mage, target: GameCharacter) {
        target.receiveCure((target.currentHp * 0.3).toInt())
        effects.applyEffect(from, target)
    }
}
