package cl.uchile.dcc.finalreality.controller

import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter

interface CharacterObserver {
    fun updateDeathEnemy(enemy: Enemy)
    fun updateDeathPlayerCharacter(playerCharacter: PlayerCharacter)
}