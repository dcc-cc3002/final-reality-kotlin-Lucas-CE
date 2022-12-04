package cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.effects.Burned
import cl.uchile.dcc.finalreality.model.character.player.effects.CompositeEffect
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell

/**
 * A fire is a type of spell that can be thrown by a mage.
 *
 * @constructor Creates a new fire spell.
 *
 * @property manaCost The cost in mana when a mage thrown the spell.
 * @property effects The adverse effect that can affect the target when receiving the spell.
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class Fire : Spell {

    override val manaCost = 15
    override val effects = CompositeEffect(listOf(Burned()))

    override fun equipSpellToBlackMage(blackMage: BlackMage) {
        blackMage.equipSpellFire(this)
    }

    override fun applyFromTo(from: Mage, target: GameCharacter) {
        target.applyFire(from, this)
    }

    fun applyFireFromTo(from: Mage, target: GameCharacter) {
        target.recieveDamage(from.equippedWeapon.magicDmg)
        if (Math.random() < 0.2) {
            effects.applyEffect(from, target)
        }
    }
}
