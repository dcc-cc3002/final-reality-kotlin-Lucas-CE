package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.assertThrows

private const val NAME = "NAME"
private const val MAXHP = 100
private const val MAXMP = 30
private const val DEFENSE = 10
private const val ENEM_WGT = 10
private const val WEP_NAME = "WEP"
private const val WEP_DMG = 10
private const val WEP_WGT = ENEM_WGT

class AbstractCharacterSpec : FunSpec ({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var BlackMage1: BlackMage
    lateinit var Engineer1: Engineer
    lateinit var Knight1: Knight
    lateinit var Thief1: Thief
    lateinit var WhiteMage1: WhiteMage
    lateinit var Enemy1: Enemy
    lateinit var normalWeapon: Axe
    lateinit var magicWeapon: Staff

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        BlackMage1 = BlackMage(NAME, MAXHP, MAXMP, DEFENSE, queue)
        Engineer1 = Engineer(NAME, MAXHP, DEFENSE, queue)
        Knight1 = Knight(NAME, MAXHP, DEFENSE, queue)
        Thief1 = Thief(NAME, MAXHP, DEFENSE, queue)
        WhiteMage1 = WhiteMage(NAME, MAXHP, MAXMP, DEFENSE, queue)
        Enemy1 = Enemy(NAME, ENEM_WGT, MAXHP, DEFENSE, queue)
        normalWeapon = Axe(WEP_NAME, WEP_DMG, WEP_WGT)
        magicWeapon = Staff(WEP_NAME, WEP_DMG, WEP_WGT)
    }

    test("initializing maxHp with a value less than 1 throws an exception"){
        checkAll(Arb.int(-MAXHP..0))
        { maxHp ->
            assertThrows<InvalidStatValueException> {
                val BlackMage2 = BlackMage(NAME, maxHp, MAXMP, DEFENSE, queue)
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
                val WhiteMage2 = WhiteMage(NAME, maxHp, MAXMP, DEFENSE, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Enemy2 = Enemy(NAME, ENEM_WGT, maxHp, DEFENSE, queue)
            }
        }
    }

    test("initializing defense with a value less than 0 throws an exception"){
        checkAll(Arb.int(-DEFENSE..-1))
        { defense ->
            assertThrows<InvalidStatValueException> {
                val BlackMage3 = BlackMage(NAME, MAXHP, MAXMP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Engineer3 = Engineer(NAME, MAXHP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Knight3 = Knight(NAME, MAXHP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Thief3 = Thief(NAME, MAXHP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val WhiteMage3 = WhiteMage(NAME, MAXHP, MAXMP, defense, queue)
            }
            assertThrows<InvalidStatValueException> {
                val Enemy3 = Enemy(NAME, ENEM_WGT, MAXHP, defense, queue)
            }
        }
    }

    test("currentHp initial value is the maxHp value"){
        BlackMage1.currentHp shouldBe MAXHP
        Engineer1.currentHp shouldBe MAXHP
        Knight1.currentHp shouldBe MAXHP
        Thief1.currentHp shouldBe MAXHP
        WhiteMage1.currentHp shouldBe MAXHP
        Enemy1.currentHp shouldBe MAXHP
    }

    test("The currentHp setter change the currentHp value"){
        checkAll(Arb.int(0..MAXHP))
        { currentHp ->
            BlackMage1.currentHp = currentHp
            BlackMage1.currentHp shouldBe currentHp

            Engineer1.currentHp = currentHp
            Engineer1.currentHp shouldBe currentHp

            Knight1.currentHp = currentHp
            Knight1.currentHp shouldBe currentHp

            Thief1.currentHp = currentHp
            Thief1.currentHp shouldBe currentHp

            WhiteMage1.currentHp = currentHp
            WhiteMage1.currentHp shouldBe currentHp

            Enemy1.currentHp = currentHp
            Enemy1.currentHp shouldBe currentHp
        }
    }

    test("The currentHp setter throws an exception when the value is out of range (0,maxHp)"){
        checkAll(
            Arb.int(MAXHP +1..2* MAXHP),
            Arb.int(-MAXHP..-1)
        )
        { greaterMaxHp, lessMinHp ->
            //BlackMage
            assertThrows<InvalidStatValueException> {BlackMage1.currentHp = greaterMaxHp}
            assertThrows<InvalidStatValueException> {BlackMage1.currentHp = lessMinHp}
            //Engineer
            assertThrows<InvalidStatValueException> {Engineer1.currentHp = greaterMaxHp}
            assertThrows<InvalidStatValueException> {Engineer1.currentHp = lessMinHp}
            //Knight
            assertThrows<InvalidStatValueException> {Knight1.currentHp = greaterMaxHp}
            assertThrows<InvalidStatValueException> {Knight1.currentHp = lessMinHp}
            //Thief
            assertThrows<InvalidStatValueException> {Thief1.currentHp = greaterMaxHp}
            assertThrows<InvalidStatValueException> {Thief1.currentHp = lessMinHp}
            //WhiteMage
            assertThrows<InvalidStatValueException> {WhiteMage1.currentHp = greaterMaxHp}
            assertThrows<InvalidStatValueException> {WhiteMage1.currentHp = lessMinHp}
            //Enemy
            assertThrows<InvalidStatValueException> {Enemy1.currentHp = greaterMaxHp}
            assertThrows<InvalidStatValueException> {Enemy1.currentHp = lessMinHp}
        }
    }

    test("waitTurn method must put in queue the character who is calling the function"){
        BlackMage1.equip(magicWeapon)
        WhiteMage1.equip(magicWeapon)
        Engineer1.equip(normalWeapon)
        Knight1.equip(normalWeapon)
        Thief1.equip(normalWeapon)

        BlackMage1.waitTurn()
        Thread.sleep(100)
        Engineer1.waitTurn()
        Thread.sleep(100)
        Knight1.waitTurn()
        Thread.sleep(100)
        Thief1.waitTurn()
        Thread.sleep(100)
        WhiteMage1.waitTurn()
        Thread.sleep(100)
        Enemy1.waitTurn()

        withContext(Dispatchers.IO) {
            Thread.sleep(6000)
        }
        queue.poll() shouldBe BlackMage1
        queue.poll() shouldBe Engineer1
        queue.poll() shouldBe Knight1
        queue.poll() shouldBe Thief1
        queue.poll() shouldBe WhiteMage1
        queue.poll() shouldBe Enemy1
    }
})