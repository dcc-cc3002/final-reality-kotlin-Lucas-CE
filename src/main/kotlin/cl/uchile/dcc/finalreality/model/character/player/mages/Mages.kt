package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison

interface Mages : PlayerCharacter {

    val maxMp: Int

    val spell: Spell

    fun equipSpellFire(fire: Fire)

    fun equipSpellThunder(thunder: Thunder)

    fun equipSpellHeal(heal: Heal)

    fun equipSpellParalysis(paralysis: Paralysis)

    fun equipSpellPoison(poison: Poison)

}