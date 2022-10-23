package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val BLMG_NAME = "BLMG"
private const val BLMG_MAXHP = 100
private const val BLMG_MAXMP = 30
private const val BLMG_DEFENSE = 10
private const val WHMG_NAME = "WHMG"
private const val WHMG_MAXHP = 80
private const val WHMG_MAXMP = 40
private const val WHMG_DEFENSE = 20

class AbstractMagesSpec : FunSpec ({
    lateinit var BlackMage1: BlackMage
    lateinit var WhiteMage1: WhiteMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        BlackMage1 = BlackMage(BLMG_NAME, BLMG_MAXHP, BLMG_MAXMP, BLMG_DEFENSE, queue)
        WhiteMage1 = WhiteMage(WHMG_NAME, WHMG_MAXHP, WHMG_MAXMP, WHMG_DEFENSE, queue)
    }

    test("The currentMp initial value is the maxMp value"){
        BlackMage1.currentMp shouldBe BLMG_MAXMP
        WhiteMage1.currentMp shouldBe WHMG_MAXMP
    }

    test("The currentMp setter change the currentMp value"){
        checkAll(Arb.int(0..BLMG_MAXMP))
        { currentMp ->
            BlackMage1.currentMp = currentMp
            BlackMage1.currentMp shouldBe currentMp
        }

        checkAll(Arb.int(0..WHMG_MAXMP))
        { currentMp ->
            WhiteMage1.currentMp = currentMp
            WhiteMage1.currentMp shouldBe currentMp
        }
    }

    test("The currentMp setter throws an exception when the value is out of range (0,maxMp)"){

        //Test blackMage
        checkAll(
            Arb.int(BLMG_MAXMP+1..2*BLMG_MAXMP),
            Arb.int(-WHMG_MAXMP..-1)
        )
        { greaterMaxMp, lessMinMp ->
            assertThrows<InvalidStatValueException> {
                BlackMage1.currentMp = greaterMaxMp
            }
            assertThrows<InvalidStatValueException> {
                BlackMage1.currentMp = lessMinMp
            }
        }

        //Test whiteMage
        checkAll(
            Arb.int(WHMG_MAXMP+1..2*WHMG_MAXMP),
            Arb.int(-WHMG_MAXMP..-1)
        )
        { greaterMaxMp, lessMinMp ->
            assertThrows<InvalidStatValueException> {
                WhiteMage1.currentMp = greaterMaxMp
            }
            assertThrows<InvalidStatValueException> {
                WhiteMage1.currentMp = lessMinMp
            }
        }
    }

})