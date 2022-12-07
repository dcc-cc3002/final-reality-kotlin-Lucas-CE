package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Sword
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.assertThrows

private const val NAME = "NAME"
private const val MAX_HP = 100
private const val MAX_MP = 30
private const val DEFENSE = 10
private const val ENEMY_WGT = 10
private const val WEP_NAME = "WEP"
private const val WEP_DMG = 10
private const val WEP_WGT = ENEMY_WGT

class AbstractCharacterSpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var blackMage: BlackMage
    lateinit var engineer: Engineer
    lateinit var knight: Knight
    lateinit var thief: Thief
    lateinit var whiteMage: WhiteMage
    lateinit var enemy1: Enemy
    lateinit var mageWeapon: GameWeapon
    lateinit var engineerWeapon: GameWeapon
    lateinit var knightWeapon: GameWeapon
    lateinit var thiefWeapon: GameWeapon
    lateinit var gameController: GameController

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blackMage = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer = Engineer(NAME, MAX_HP, DEFENSE, queue)
        knight = Knight(NAME, MAX_HP, DEFENSE, queue)
        thief = Thief(NAME, MAX_HP, DEFENSE, queue)
        whiteMage = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        enemy1 = Enemy(NAME, ENEMY_WGT, MAX_HP, DEFENSE, queue)
        mageWeapon = Staff(WEP_NAME, WEP_DMG, WEP_WGT, WEP_DMG)
        engineerWeapon = Axe(WEP_NAME, WEP_DMG, WEP_WGT)
        knightWeapon = Sword(WEP_NAME, WEP_DMG, WEP_WGT)
        thiefWeapon = Knife(WEP_NAME, WEP_DMG, WEP_WGT)
        gameController = GameController()
    }

    test("initializing maxHp with a value less than 1 throws an exception") {
        checkAll(Arb.int(-MAX_HP..0)) {
            maxHp ->
            assertThrows<InvalidStatValueException> {
                val blackMageError = BlackMage(NAME, maxHp, MAX_MP, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val engineerError = Engineer(NAME, maxHp, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val knightError = Knight(NAME, maxHp, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val thiefError = Thief(NAME, maxHp, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val whiteMageError = WhiteMage(NAME, maxHp, MAX_MP, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val enemyError = Enemy(NAME, ENEMY_WGT, maxHp, DEFENSE, queue)
            }
        }
    }

    test("initializing defense with a value less than 0 throws an exception") {
        checkAll(Arb.int(-DEFENSE..-1)) {
            defense ->
            assertThrows<InvalidStatValueException> {
                val blackMageError = BlackMage(NAME, MAX_HP, MAX_MP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val engineerError = Engineer(NAME, MAX_HP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val knightError = Knight(NAME, MAX_HP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val thiefError = Thief(NAME, MAX_HP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val whiteMageError = WhiteMage(NAME, MAX_HP, MAX_MP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val enemyError = Enemy(NAME, ENEMY_WGT, MAX_HP, defense, queue)
            }
        }
    }

    test("currentHp initial value is the maxHp value") {
        blackMage.currentHp shouldBe MAX_HP
        engineer.currentHp shouldBe MAX_HP
        knight.currentHp shouldBe MAX_HP
        thief.currentHp shouldBe MAX_HP
        whiteMage.currentHp shouldBe MAX_HP
        enemy1.currentHp shouldBe MAX_HP
    }

    test("The currentHp setter change the currentHp value") {
        checkAll(Arb.int(0..MAX_HP)) {
            currentHp ->
            blackMage.currentHp = currentHp
            blackMage.currentHp shouldBe currentHp

            engineer.currentHp = currentHp
            engineer.currentHp shouldBe currentHp

            knight.currentHp = currentHp
            knight.currentHp shouldBe currentHp

            thief.currentHp = currentHp
            thief.currentHp shouldBe currentHp

            whiteMage.currentHp = currentHp
            whiteMage.currentHp shouldBe currentHp

            enemy1.currentHp = currentHp
            enemy1.currentHp shouldBe currentHp
        }
    }

    test("The currentHp setter throws an exception when the value is out of range (0,maxHp)") {
        checkAll(
            Arb.int(MAX_HP + 1..2 * MAX_HP),
            Arb.int(-MAX_HP..-1)
        ) { greaterMaxHp, lessMinHp ->
            // BlackMage
            assertThrows<InvalidStatValueException> { blackMage.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { blackMage.currentHp = lessMinHp }
            // Engineer
            assertThrows<InvalidStatValueException> { engineer.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { engineer.currentHp = lessMinHp }
            // Knight
            assertThrows<InvalidStatValueException> { knight.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { knight.currentHp = lessMinHp }
            // Thief
            assertThrows<InvalidStatValueException> { thief.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { thief.currentHp = lessMinHp }
            // WhiteMage
            assertThrows<InvalidStatValueException> { whiteMage.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { whiteMage.currentHp = lessMinHp }
            // Enemy
            assertThrows<InvalidStatValueException> { enemy1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { enemy1.currentHp = lessMinHp }
        }
    }

    test("addListener method should the observer to the listeners list") {
        blackMage.addListener(gameController)
        blackMage.characterListeners.contains(gameController) shouldBe true
    }

    test(
        "When a character takes damage greater than his defense, but less than " +
            "their hp + defense, their life should decrease in damage less defense points."
    ) {
        checkAll(
            Arb.int(blackMage.defense..MAX_HP + blackMage.defense)
        ) { damage ->
            blackMage = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
            blackMage.recieveDamage(damage)
            blackMage.currentHp shouldBe MAX_HP + blackMage.defense - damage
        }
    }

    test(
        "When a character takes damage greater than his defense + hp, their life should " +
            "decrease in damage less defense points."
    ) {
        checkAll(
            Arb.int(min = blackMage.currentHp + blackMage.defense)
        ) { damage ->
            blackMage = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
            blackMage.recieveDamage(damage)
            blackMage.currentHp shouldBe 0
        }
    }

    test(
        "When a character takes less damage than his defense, their current hp " +
            "does not change."
    ) {
        checkAll(Arb.positiveInt(blackMage.defense)) { damage ->
            blackMage = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
            val initialHp = blackMage.currentHp
            blackMage.recieveDamage(damage)
            blackMage.currentHp shouldBe initialHp
        }
    }

    test("waitTurn method must put in queue the character who is calling the function") {
        blackMage.equip(mageWeapon)
        whiteMage.equip(mageWeapon)
        engineer.equip(engineerWeapon)
        knight.equip(knightWeapon)
        thief.equip(thiefWeapon)

        blackMage.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        engineer.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        knight.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        thief.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        whiteMage.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        enemy1.waitTurn()

        withContext(Dispatchers.IO) { Thread.sleep(6000) }

        queue.poll() shouldBe blackMage
        queue.poll() shouldBe engineer
        queue.poll() shouldBe knight
        queue.poll() shouldBe thief
        queue.poll() shouldBe whiteMage
        queue.poll() shouldBe enemy1
    }
})
