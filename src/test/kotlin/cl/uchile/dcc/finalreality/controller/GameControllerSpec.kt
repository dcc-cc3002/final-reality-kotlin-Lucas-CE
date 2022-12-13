package cl.uchile.dcc.finalreality.controller

import cl.uchile.dcc.finalreality.controller.gameStates.EnemyMenuState
import cl.uchile.dcc.finalreality.controller.gameStates.IdleState
import cl.uchile.dcc.finalreality.controller.gameStates.PlayerMenuState
import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Sword
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue
import kotlin.random.Random

private const val NAME = "NAME"
private const val MAX_HP = 100
private const val MAX_MP = 100
private const val DEFENSE = 10
private const val DAMAGE = 30
private const val MAGIC_DAMAGE = 60
private const val WEIGHT = 5

class GameControllerSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var axe: Axe
    lateinit var bow: Bow
    lateinit var knife: Knife
    lateinit var staff: Staff
    lateinit var sword: Sword
    lateinit var thunder: Thunder
    lateinit var heal: Heal
    lateinit var paralysis: Paralysis
    lateinit var poison: Poison
    lateinit var fire: Fire
    lateinit var engineer: Engineer
    lateinit var whiteMage: WhiteMage
    lateinit var enemy: Enemy
    beforeEach() {
        gameController = GameController()
        queue = LinkedBlockingQueue<GameCharacter>()
        axe = Axe(NAME, DAMAGE, WEIGHT)
        bow = Bow(NAME, DAMAGE, WEIGHT)
        knife = Knife(NAME, DAMAGE, WEIGHT)
        staff = Staff(NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE)
        sword = Sword(NAME, DAMAGE, WEIGHT)
        thunder = Thunder()
        heal = Heal()
        paralysis = Paralysis()
        poison = Poison()
        fire = Fire()
        engineer = Engineer(NAME, MAX_HP, DEFENSE, queue)
        enemy = Enemy(NAME, WEIGHT, MAX_HP, DEFENSE, queue)
        whiteMage = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer.equip(axe)
        whiteMage.equipSpell(heal)
        whiteMage.equip(staff)
    }
    test("The player character selected is the first character in the queue") {
        gameController.createPlayerCharacterEngineer(NAME, MAX_HP, DEFENSE, axe)
        queue = gameController.turnsQueue
        val engineerInQueue = Engineer(NAME, MAX_HP, DEFENSE, queue)
        gameController.nextTurn()
        gameController.characterSelected shouldBe engineerInQueue
        gameController.state::class shouldBe PlayerMenuState::class
    }
    test("The enemy selected is the first character in the queue") {
        gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
        queue = gameController.turnsQueue
        val enemyInQueue = Enemy(NAME, WEIGHT, MAX_HP, DEFENSE, queue)
        gameController.nextTurn()
        gameController.characterSelected shouldBe enemyInQueue
        gameController.state::class shouldBe EnemyMenuState::class
    }

    test("The game controller will jump the turn of a paralyzed character") {
        gameController.createPlayerCharacterWhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, staff, paralysis)
        gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
        gameController.nextTurn()
        gameController.useMagic(gameController.enemyCharacters[0])
        gameController.paralyzedCharacters.isEmpty() shouldBe false
        gameController.nextTurn() // Go to enemy turn and jump it
        gameController.state::class shouldBe IdleState::class
    }
    test("The enemies that are burning will take damage on their turn.") {
        val random = Random(100)
        repeat(100) {
            gameController = GameController()
            gameController.createPlayerCharacterBlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, staff, fire)
            gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
            gameController.nextTurn() // Black mage turn
            gameController.useMagic(gameController.enemyCharacters[0])
            gameController.enemyCharacters[0].currentHp = MAX_HP
            if (random.nextDouble() < 0.2) {
                gameController.burnedCharacters.isEmpty() shouldBe false
                gameController.nextTurn() // Enemy turn
                gameController.characterSelected?.currentHp shouldBe
                    MAX_HP - staff.magicDmg / 2 + DEFENSE
            }
        }
    }
    test("The enemies that are poison will take damage on their turn.") {
        gameController.createPlayerCharacterWhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, staff, poison)
        gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
        gameController.nextTurn() // White mage turn
        gameController.useMagic(gameController.enemyCharacters[0]) // Enemy poisoned
        gameController.enemyCharacters[0].currentHp = MAX_HP
        gameController.poisonedCharacters.isEmpty() shouldBe false
        gameController.nextTurn() // Enemy turn
        gameController.characterSelected?.currentHp shouldBe MAX_HP - staff.magicDmg / 3 + DEFENSE
    }
    test("Game controller can create engineers") {
        gameController.createPlayerCharacterEngineer(NAME, MAX_HP, DEFENSE, axe)
        queue = gameController.turnsQueue
        val expected = Engineer(NAME, MAX_HP, DEFENSE, queue)
        expected.equip(axe)
        queue.poll() shouldBe expected
    }
    test("Game controller can create knights") {
        gameController.createPlayerCharacterKnight(NAME, MAX_HP, DEFENSE, sword)
        queue = gameController.turnsQueue
        val expected = Knight(NAME, MAX_HP, DEFENSE, queue)
        expected.equip(sword)
        queue.poll() shouldBe expected
    }
    test("Game controller can create thieves") {
        gameController.createPlayerCharacterThief(NAME, MAX_HP, DEFENSE, bow)
        queue = gameController.turnsQueue
        val expected = Thief(NAME, MAX_HP, DEFENSE, queue)
        expected.equip(bow)
        queue.poll() shouldBe expected
    }
    test("Game controller can create black mages") {
        gameController.createPlayerCharacterBlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, knife, thunder)
        queue = gameController.turnsQueue
        val expected = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        expected.equip(knife)
        expected.equipSpell(thunder)
        queue.poll() shouldBe expected
    }
    test("Game controller can create white mages") {
        gameController.createPlayerCharacterWhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, staff, heal)
        queue = gameController.turnsQueue
        val expected = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        expected.equip(staff)
        expected.equipSpell(heal)
        queue.poll() shouldBe expected
    }
    test("Game controller can create enemies") {
        gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
        queue = gameController.turnsQueue
        val expected = Enemy(NAME, WEIGHT, MAX_HP, DEFENSE, queue)
        queue.poll() shouldBe expected
    }
    test("The game controller allows characters to attack other characters") {
        gameController.setState(PlayerMenuState(gameController))
        gameController.attack(engineer, enemy)
        enemy.currentHp shouldBe MAX_HP - (DAMAGE - DEFENSE)
    }
    test("Mages can use magic with other characters") {
        gameController.createPlayerCharacterWhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, staff, heal)
        gameController.createPlayerCharacterEngineer(NAME, MAX_HP, DEFENSE, axe)
        gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
        gameController.playerCharacters[1].currentHp /= 2
        gameController.playerCharacters[1].currentHp shouldBe MAX_HP / 2
        gameController.nextTurn()
        gameController.useMagic(gameController.playerCharacters[1])
        gameController.playerCharacters[1].currentHp shouldBe MAX_HP * 0.8
    }
    test("If a character death must be removed from the character list") {
        // to enemy
        gameController.createEnemy(NAME, MAX_HP, DEFENSE, WEIGHT)
        queue = gameController.turnsQueue
        val enemyInQueue = Enemy(NAME, WEIGHT, MAX_HP, DEFENSE, queue)
        enemyInQueue.addListener(gameController)
        gameController.turnsQueue.contains(enemyInQueue) shouldBe true
        enemyInQueue.currentHp = 0
        enemyInQueue.notifyDeath()
        gameController.turnsQueue.contains(enemyInQueue) shouldBe false

        // to player character
        gameController.createPlayerCharacterEngineer(NAME, MAX_HP, DEFENSE, axe)
        queue = gameController.turnsQueue
        val engineerInQueue = Engineer(NAME, MAX_HP, DEFENSE, queue)
        engineerInQueue.addListener(gameController)
        gameController.turnsQueue.contains(engineerInQueue) shouldBe true
        engineerInQueue.currentHp = 0
        engineerInQueue.notifyDeath()
        gameController.turnsQueue.contains(engineerInQueue) shouldBe false
    }
})
