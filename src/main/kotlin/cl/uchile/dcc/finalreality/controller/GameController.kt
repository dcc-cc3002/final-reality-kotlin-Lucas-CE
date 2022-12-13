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
    private val _playerCharacters = mutableListOf<PlayerCharacter>()
    private val _enemyCharacters = mutableListOf<Enemy>()
    private var _state: GameState = IdleState(this)
    private val _paralyzedCharacters = mutableListOf<GameCharacter>()
    private val _poisonedCharacters = mutableListOf<GameCharacter>()
    private val _burnedCharacters = mutableListOf<GameCharacter>()
    private lateinit var _characterSelected: GameCharacter
    val turnsQueue
        get() = _turnsQueue
    val playerCharacters
        get() = _playerCharacters
    val enemyCharacters
        get() = _enemyCharacters
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
        if (!_turnsQueue.isEmpty()) {
            _characterSelected = _turnsQueue.poll()
            _state.toDecidingTheTurnState()
            decideTurn()
        } else {
            println("The queue is still empty")
        }
    }

    private fun applyDamageEffects() {
        if (_characterSelected in _burnedCharacters) {
            _characterSelected.receiveDamage(_characterSelected.magicDamageFire)
            _characterSelected.magicDamageFire = 0
            _burnedCharacters.remove(_characterSelected)
        }
        if (_characterSelected in _poisonedCharacters) {
            _characterSelected.receiveDamage(_characterSelected.magicDamagePoison)
            _characterSelected.magicDamagePoison = 0
            _poisonedCharacters.remove(_characterSelected)
        }
    }

    private fun decideTurn() {
        applyDamageEffects()
        when (_characterSelected) {
            in _paralyzedCharacters -> {
                _characterSelected.waitTurn()
                _state.toIdleState()
            }
            in _playerCharacters -> {
                _state.toPlayerMenuState()
            }
            in _enemyCharacters -> {
                _state.toEnemyMenuState()
            }
        }
    }

    fun createPlayerCharacterEngineer(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val engineer = Engineer(name, hp, defense, _turnsQueue)
        engineer.equip(weapon)
        _playerCharacters.add(engineer)
        _turnsQueue.add(engineer)
        engineer.addListener(this)
    }

    fun createPlayerCharacterKnight(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val knight = Knight(name, hp, defense, _turnsQueue)
        knight.equip(weapon)
        _playerCharacters.add(knight)
        _turnsQueue.add(knight)
        knight.addListener(this)
    }

    fun createPlayerCharacterThief(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val thief = Thief(name, hp, defense, _turnsQueue)
        thief.equip(weapon)
        _playerCharacters.add(thief)
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
        val whiteMage = WhiteMage(name, hp, mp, defense, _turnsQueue)
        whiteMage.equip(weapon)
        whiteMage.equipSpell(spell)
        _playerCharacters.add(whiteMage)
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
        val blackMage = BlackMage(name, hp, mp, defense, _turnsQueue)
        blackMage.equip(weapon)
        blackMage.equipSpell(spell)
        _playerCharacters.add(blackMage)
        _turnsQueue.add(blackMage)
        blackMage.addListener(this)
    }

    fun createEnemy(name: String, hp: Int, defense: Int, weight: Int) {
        val enemy = Enemy(name, weight, hp, defense, _turnsQueue)
        _enemyCharacters.add(enemy)
        _turnsQueue.add(enemy)
        enemy.addListener(this)
    }

    fun attack(target: GameCharacter) {
        _characterSelected.attack(target)
        waitTurn(_characterSelected)
        _state.toIdleState()
    }

    fun useMagic(target: GameCharacter) {
        val attacker = _characterSelected as Mage
        attacker.throwSpell(target)
        waitTurn(attacker)
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
        if (_enemyCharacters.isEmpty()) {
            println("The players wins!")
            _turnsQueue.removeAll(_turnsQueue)
            _playerCharacters.removeAll(_playerCharacters)
            _enemyCharacters.removeAll(_enemyCharacters)
        }
    }

    private fun onEnemyWin() {
        if (_playerCharacters.isEmpty()) {
            println("The enemies wins!")
            _turnsQueue.removeAll(_turnsQueue)
            _playerCharacters.removeAll(_playerCharacters)
            _enemyCharacters.removeAll(_enemyCharacters)
        }
    }

    override fun updateDeathEnemy(enemy: Enemy) {
        _enemyCharacters.remove(enemy)
        _turnsQueue.remove(enemy)
    }

    override fun updateDeathPlayerCharacter(playerCharacter: PlayerCharacter) {
        _playerCharacters.remove(playerCharacter)
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
