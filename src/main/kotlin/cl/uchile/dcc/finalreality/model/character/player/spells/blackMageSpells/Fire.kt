package cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.Burned
import cl.uchile.dcc.finalreality.model.character.player.effects.CompositeEffect
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mages
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

/**
 * A fire is a type of spell that can be thrown by a mage.
 *
 * @constructor Creates a new fire spell.
 *
 * @property manaCost The cost in mana when a mage thrown the spell.
 * @property effect The adverse effect that can affect the target when receiving the spell.
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Fire : Spell {

    override val manaCost = 15
    override val effect = CompositeEffect(listOf(Burned()))

    override fun equipSpellToBlackMage(blackMage: BlackMage) {
        blackMage.equipSpellFire(this)
    }

    override fun applyFromTo(mage: Mages, target: GameCharacter) {
        target.applyFire(mage, this)
    }

    fun applyFireFromTo(mage: Mages, target: GameCharacter) {
        target.currentHp -= mage.equippedWeapon.magicDmg
        if (Math.random() < 0.2) {
            effect.applyEffect(target)
        }
    }
}
