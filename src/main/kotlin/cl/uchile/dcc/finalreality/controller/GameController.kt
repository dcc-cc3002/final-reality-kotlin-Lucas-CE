package cl.uchile.dcc.finalreality.controller

import cl.uchile.dcc.finalreality.controller.gameStates.GameState
import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.Mage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import java.util.concurrent.LinkedBlockingQueue

class GameController : CharacterObserver {
    private val turnsQueue = LinkedBlockingQueue<GameCharacter>()
    private val playerCharacters = mutableListOf<PlayerCharacter>()
    private val enemyCharacters = mutableListOf<Enemy>()
    private var state: GameState = GameState(this)

    init {
    }

    fun initGame() {
    }

    fun createPlayerCharacterEngineer(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val engineer = Engineer(name, hp, defense, turnsQueue)
        engineer.equip(weapon)
        playerCharacters.add(engineer)
        engineer.addListener(this)
    }

    fun createPlayerCharacterKnight(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val knight = Knight(name, hp, defense, turnsQueue)
        knight.equip(weapon)
        playerCharacters.add(knight)
        knight.addListener(this)
    }

    fun createPlayerCharacterThief(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val thief = Thief(name, hp, defense, turnsQueue)
        thief.equip(weapon)
        playerCharacters.add(thief)
        thief.addListener(this)
    }

    fun createPlayerCharacterWhiteMage(
        name: String,
        hp: Int,
        mp: Int,
        defense: Int,
        weapon: GameWeapon,
        spell: Spell
    ) {
        val whiteMage = WhiteMage(name, hp, mp, defense, turnsQueue)
        whiteMage.equip(weapon)
        whiteMage.equipSpell(spell)
        playerCharacters.add(whiteMage)
        whiteMage.addListener(this)
    }

    fun createPlayerCharacterBlackMage(
        name: String,
        hp: Int,
        mp: Int,
        defense: Int,
        weapon: GameWeapon,
        spell: Spell
    ) {
        val blackMage = BlackMage(name, hp, mp, defense, turnsQueue)
        blackMage.equip(weapon)
        blackMage.equipSpell(spell)
        playerCharacters.add(blackMage)
        blackMage.addListener(this)
    }

    fun createEnemy(name: String, hp: Int, defense: Int, weight: Int) {
        val enemy = Enemy(name, hp, defense, weight, turnsQueue)
        enemyCharacters.add(enemy)
        enemy.addListener(this)
    }

    fun attack(attacker: GameCharacter, target: GameCharacter) {
        attacker.attack(target)
        onEnemyWin()
        onPlayerWin()
    }

    fun useMagic(attacker: Mage, target: GameCharacter) {
        attacker.throwSpell(target)
    }

    fun detectDeathPlayerCharacter(playerCharacter: PlayerCharacter) {
        if (playerCharacter.currentHp == 0) {
            playerCharacters.remove(playerCharacter)
        }
    }

    fun detectDeathEnemies(enemy: Enemy) {
        if (enemy.currentHp == 0) {
            enemyCharacters.remove(enemy)
        }
    }

    fun waitTurn(character: GameCharacter) {
        character.waitTurn()
    }

    private fun onPlayerWin() {
        if (enemyCharacters.isEmpty()) {
            println("The players wins!")
        }
    }

    private fun onEnemyWin() {
        if (playerCharacters.isEmpty()) {
            println("The enemies wins!")
        }
    }

    override fun updateDeathEnemy(enemy: Enemy) {
        enemyCharacters.remove(enemy)
    }

    override fun updateDeathPlayerCharacter(playerCharacter: PlayerCharacter) {
        playerCharacters.remove(playerCharacter)
    }

    fun setState(gameState: GameState) {
        state = gameState
    }
}
