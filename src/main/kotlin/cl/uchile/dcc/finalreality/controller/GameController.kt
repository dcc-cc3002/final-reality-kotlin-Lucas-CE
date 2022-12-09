package cl.uchile.dcc.finalreality.controller

import cl.uchile.dcc.finalreality.controller.gameStates.GameState
import cl.uchile.dcc.finalreality.controller.gameStates.IdleState
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
    private val _turnsQueue = LinkedBlockingQueue<GameCharacter>()
    private val playerCharacters = mutableListOf<PlayerCharacter>()
    private val enemyCharacters = mutableListOf<Enemy>()
    private var _state: GameState = IdleState(this)
    private val _paralyzedCharacters = mutableListOf<GameCharacter>()
    private val _poisonedCharacters = mutableListOf<GameCharacter>()
    private val _burnedCharacters = mutableListOf<GameCharacter>()
    private var _characterSelected : GameCharacter? = null
    val turnsQueue
        get() = _turnsQueue
    val paralyzedCharacters
        get() = _paralyzedCharacters
    val poisonedCharacters
        get() = _poisonedCharacters
    val burnedCharacters
        get() = _burnedCharacters
    val state
        get() = _state
    val characterSelected
        get() = _characterSelected

    fun nextTurn() {
        _characterSelected = _turnsQueue.poll()
        _state.toDecidingTheTurnState()
    }

    fun decideTurn() {
        if (_characterSelected in playerCharacters) {
            _state.toPlayerMenuState()
        }
        else if (_characterSelected in enemyCharacters) {
            _state.toEnemyMenuState()
        }
    }


    fun createPlayerCharacterEngineer(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val engineer = Engineer(name, hp, defense, turnsQueue)
        engineer.equip(weapon)
        playerCharacters.add(engineer)
        _turnsQueue.add(engineer)
        engineer.addListener(this)
    }

    fun createPlayerCharacterKnight(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val knight = Knight(name, hp, defense, turnsQueue)
        knight.equip(weapon)
        playerCharacters.add(knight)
        _turnsQueue.add(knight)
        knight.addListener(this)
    }

    fun createPlayerCharacterThief(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val thief = Thief(name, hp, defense, turnsQueue)
        thief.equip(weapon)
        playerCharacters.add(thief)
        _turnsQueue.add(thief)
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
        _turnsQueue.add(whiteMage)
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
        _turnsQueue.add(blackMage)
        blackMage.addListener(this)
    }

    fun createEnemy(name: String, hp: Int, defense: Int, weight: Int) {
        val enemy = Enemy(name, weight, hp, defense, turnsQueue)
        enemyCharacters.add(enemy)
        _turnsQueue.add(enemy)
        enemy.addListener(this)
    }

    fun attack(attacker: GameCharacter, target: GameCharacter) {
        attacker.attack(target)
        waitTurn(attacker)
        _state.toIdleState()
    }

    fun useMagic(attacker: Mage, target: GameCharacter) {
        attacker.throwSpell(target)
        _state.toIdleState()
    }

    private fun waitTurn(character: GameCharacter) {
        character.waitTurn()
    }

    fun verifyWin() {
        onEnemyWin()
        onPlayerWin()
    }

    private fun onPlayerWin() {
        if (enemyCharacters.isEmpty()) {
            println("The players wins!")
            _turnsQueue.removeAll(_turnsQueue)
            playerCharacters.removeAll(playerCharacters)
            enemyCharacters.removeAll(enemyCharacters)
        }
    }

    private fun onEnemyWin() {
        if (playerCharacters.isEmpty()) {
            println("The enemies wins!")
            _turnsQueue.removeAll(_turnsQueue)
            playerCharacters.removeAll(playerCharacters)
            enemyCharacters.removeAll(enemyCharacters)
        }
    }

    override fun updateDeathEnemy(enemy: Enemy) {
        enemyCharacters.remove(enemy)
        _turnsQueue.remove(enemy)
    }

    override fun updateDeathPlayerCharacter(playerCharacter: PlayerCharacter) {
        playerCharacters.remove(playerCharacter)
        _turnsQueue.remove(playerCharacter)
    }

    override fun updateBurnedEffect(gameCharacter: GameCharacter) {
        _burnedCharacters.add(gameCharacter)
    }

    override fun updatePoisonedEffect(gameCharacter: GameCharacter) {
        _poisonedCharacters.add(gameCharacter)
    }

    override fun updateParalyzedEffect(gameCharacter: GameCharacter) {
        _paralyzedCharacters.add(gameCharacter)
    }

    fun setState(gameState: GameState) {
        _state = gameState
    }
}
