package cl.uchile.dcc.finalreality

import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.Spell
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import java.util.concurrent.LinkedBlockingQueue

class GameController {
    private val turnsQueue = LinkedBlockingQueue<GameCharacter>()
    private val playerCharacters = mutableListOf<PlayerCharacter>()
    private val enemyCharacters = mutableListOf<Enemy>()

    init {
    }

    fun createPlayerCharacterEngineer(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val engineer = Engineer(name, hp, defense, turnsQueue)
        engineer.equip(weapon)
        playerCharacters.add(engineer)
    }

    fun createPlayerCharacterKnight(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val knight = Knight(name, hp, defense, turnsQueue)
        knight.equip(weapon)
        playerCharacters.add(knight)
    }

    fun createPlayerCharacterThief(name: String, hp: Int, defense: Int, weapon: GameWeapon) {
        val thief = Thief(name, hp, defense, turnsQueue)
        thief.equip(weapon)
        playerCharacters.add(thief)
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
    }

    fun createEnemy(name: String, hp: Int, defense: Int, weight: Int) {
        val enemy = Enemy(name, hp, defense, weight, turnsQueue)
        enemyCharacters.add(enemy)
    }

    fun attack(attacker: GameCharacter, target: GameCharacter) {
        // TODO: Attack a target
    }

    fun useMagic(attacker: GameCharacter, target: GameCharacter) {
        // TODO: Use magic on a target
    }

    fun waitTurn(character: GameCharacter) {
        character.waitTurn()
    }

    fun onPlayerWin() {
        // TODO: Handle the player winning the game
    }

    fun onEnemyWin() {
        // TODO: Handle the enemy winning the game
    }
}