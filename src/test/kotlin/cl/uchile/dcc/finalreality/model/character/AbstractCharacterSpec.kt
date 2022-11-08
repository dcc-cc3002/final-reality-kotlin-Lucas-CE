package cl.uchile.dcc.finalreality.model.character

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
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.LinkedBlockingQueue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    lateinit var blackMage1: BlackMage
    lateinit var engineer1: Engineer
    lateinit var knight1: Knight
    lateinit var thief1: Thief
    lateinit var whiteMage1: WhiteMage
    lateinit var enemy1: Enemy
    lateinit var mageWeapon: GameWeapon
    lateinit var engineerWeapon: GameWeapon
    lateinit var knightWeapon: GameWeapon
    lateinit var thiefWeapon: GameWeapon

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blackMage1 = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer1 = Engineer(NAME, MAX_HP, DEFENSE, queue)
        knight1 = Knight(NAME, MAX_HP, DEFENSE, queue)
        thief1 = Thief(NAME, MAX_HP, DEFENSE, queue)
        whiteMage1 = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        enemy1 = Enemy(NAME, ENEMY_WGT, MAX_HP, DEFENSE, queue)
        mageWeapon = Staff(WEP_NAME, WEP_DMG, WEP_WGT)
        engineerWeapon = Axe(WEP_NAME, WEP_DMG, WEP_WGT)
        knightWeapon = Sword(WEP_NAME, WEP_DMG, WEP_WGT)
        thiefWeapon = Knife(WEP_NAME, WEP_DMG, WEP_WGT)
    }

    test("initializing maxHp with a value less than 1 throws an exception") {
        checkAll(Arb.int(-MAX_HP..0)) {
            maxHp ->
            assertThrows<InvalidStatValueException> {
                val BlackMage2 = BlackMage(NAME, maxHp, MAX_MP, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Engineer2 = Engineer(NAME, maxHp, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Knight2 = Knight(NAME, maxHp, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Thief2 = Thief(NAME, maxHp, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val WhiteMage2 = WhiteMage(NAME, maxHp, MAX_MP, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Enemy2 = Enemy(NAME, ENEMY_WGT, maxHp, DEFENSE, queue)
            }
        }
    }

    test("initializing defense with a value less than 0 throws an exception") {
        checkAll(Arb.int(-DEFENSE..-1)) {
            defense ->
            assertThrows<InvalidStatValueException> {
                val BlackMage3 = BlackMage(NAME, MAX_HP, MAX_MP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Engineer3 = Engineer(NAME, MAX_HP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Knight3 = Knight(NAME, MAX_HP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Thief3 = Thief(NAME, MAX_HP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val WhiteMage3 = WhiteMage(NAME, MAX_HP, MAX_MP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Enemy3 = Enemy(NAME, ENEMY_WGT, MAX_HP, defense, queue)
            }
        }
    }

    test("currentHp initial value is the maxHp value") {
        blackMage1.currentHp shouldBe MAX_HP
        engineer1.currentHp shouldBe MAX_HP
        knight1.currentHp shouldBe MAX_HP
        thief1.currentHp shouldBe MAX_HP
        whiteMage1.currentHp shouldBe MAX_HP
        enemy1.currentHp shouldBe MAX_HP
    }

    test("The currentHp setter change the currentHp value") {
        checkAll(Arb.int(0..MAX_HP)) {
            currentHp ->
            blackMage1.currentHp = currentHp
            blackMage1.currentHp shouldBe currentHp

            engineer1.currentHp = currentHp
            engineer1.currentHp shouldBe currentHp

            knight1.currentHp = currentHp
            knight1.currentHp shouldBe currentHp

            thief1.currentHp = currentHp
            thief1.currentHp shouldBe currentHp

            whiteMage1.currentHp = currentHp
            whiteMage1.currentHp shouldBe currentHp

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
            assertThrows<InvalidStatValueException> { blackMage1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { blackMage1.currentHp = lessMinHp }
            // Engineer
            assertThrows<InvalidStatValueException> { engineer1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { engineer1.currentHp = lessMinHp }
            // Knight
            assertThrows<InvalidStatValueException> { knight1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { knight1.currentHp = lessMinHp }
            // Thief
            assertThrows<InvalidStatValueException> { thief1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { thief1.currentHp = lessMinHp }
            // WhiteMage
            assertThrows<InvalidStatValueException> { whiteMage1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { whiteMage1.currentHp = lessMinHp }
            // Enemy
            assertThrows<InvalidStatValueException> { enemy1.currentHp = greaterMaxHp }
            assertThrows<InvalidStatValueException> { enemy1.currentHp = lessMinHp }
        }
    }

    test("waitTurn method must put in queue the character who is calling the function") {
        blackMage1.equip(mageWeapon)
        whiteMage1.equip(mageWeapon)
        engineer1.equip(engineerWeapon)
        knight1.equip(knightWeapon)
        thief1.equip(thiefWeapon)

        blackMage1.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        engineer1.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        knight1.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        thief1.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        whiteMage1.waitTurn()
        withContext(Dispatchers.IO) { Thread.sleep(100) }
        enemy1.waitTurn()

        withContext(Dispatchers.IO) { Thread.sleep(6000) }

        queue.poll() shouldBe blackMage1
        queue.poll() shouldBe engineer1
        queue.poll() shouldBe knight1
        queue.poll() shouldBe thief1
        queue.poll() shouldBe whiteMage1
        queue.poll() shouldBe enemy1
    }
})
