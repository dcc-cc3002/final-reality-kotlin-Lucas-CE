package cl.uchile.dcc.finalreality

import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import java.util.concurrent.LinkedBlockingQueue

class GameController {
    private val turnsQueue = LinkedBlockingQueue<GameCharacter>()
    private val playerCharacters = mutableListOf<PlayerCharacter>()
    private val enemyCharacters = mutableListOf<Enemy>()

    init {
    }

    fun createPlayer(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        // TODO: Create a player character
    }

    fun createEnemy(name: String, hp: Int, defense: Int, weight: Int) {
        // TODO: Create an enemy character
    }

    fun attack(attacker: GameCharacter, target: GameCharacter) {
        // TODO: Attack a target
    }

    fun useMagic(attacker: GameCharacter, target: GameCharacter) {
        // TODO: Use magic on a target
    }

    fun waitTurn(character: GameCharacter) {
        // TODO: Call the waitTurn method of the character
    }

    fun onPlayerWin() {
        // TODO: Handle the player winning the game
    }

    fun onEnemyWin() {
        // TODO: Handle the enemy winning the game
    }
}