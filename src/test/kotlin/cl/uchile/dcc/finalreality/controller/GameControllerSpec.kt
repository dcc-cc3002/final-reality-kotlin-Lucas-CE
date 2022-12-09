package cl.uchile.dcc.finalreality.controller

import cl.uchile.dcc.finalreality.controller.gameStates.PlayerMenuState
import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Sword
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue

private const val NAME = "NAME"
private const val MAX_HP = 100
private const val MAX_MP = 100
private const val DEFENSE = 20
private const val DAMAGE = 30
private const val MAGIC_DAMAGE = 30
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
    lateinit var engineer: Engineer
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
        engineer = Engineer(NAME, MAX_HP, DEFENSE, queue)
        enemy = Enemy(NAME, WEIGHT, MAX_HP, DEFENSE, queue)
        engineer.equip(axe)
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
})
