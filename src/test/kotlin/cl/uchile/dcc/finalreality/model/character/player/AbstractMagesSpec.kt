package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val MAGE_NAME = "MAGE1"
private const val MAGE_MAXHP = 100
private const val MAGE_MAXMP = 30
private const val MAGE_DEFENSE = 10

class AbstractMagesSpec : FunSpec ({
    lateinit var BlackMage1: BlackMage
    lateinit var WhiteMage1: WhiteMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        BlackMage1 = BlackMage(MAGE_NAME, MAGE_MAXHP, MAGE_MAXMP, MAGE_DEFENSE, queue)
        WhiteMage1 = WhiteMage(MAGE_NAME, MAGE_MAXHP, MAGE_MAXMP, MAGE_DEFENSE, queue)
    }

    test("The currentMp initial value is the maxMp value"){
        BlackMage1.currentMp shouldBe MAGE_MAXMP
        WhiteMage1.currentMp shouldBe MAGE_MAXMP
    }

    test("The currentMp setter change the currentMp value"){
        checkAll(Arb.int(0..MAGE_MAXMP))
        { currentMp ->
            BlackMage1.currentMp = currentMp
            BlackMage1.currentMp shouldBe currentMp
        }

        checkAll(Arb.int(0..MAGE_MAXMP))
        { currentMp ->
            WhiteMage1.currentMp = currentMp
            WhiteMage1.currentMp shouldBe currentMp
        }
    }

    test("The currentMp setter throws an exception when the value is out of range (0,maxMp)"){

        //Test blackMage
        checkAll(
            Arb.int(MAGE_MAXMP+1..2*MAGE_MAXMP),
            Arb.int(-MAGE_MAXMP..-1)
        )
        { greaterMaxMp, lessMinMp ->
            //BlackMage
            assertThrows<InvalidStatValueException> {
                BlackMage1.currentMp = greaterMaxMp
            }
            assertThrows<InvalidStatValueException> {
                BlackMage1.currentMp = lessMinMp
            }
            //WhiteMage
            assertThrows<InvalidStatValueException> {
                WhiteMage1.currentMp = greaterMaxMp
            }
            assertThrows<InvalidStatValueException> {
                WhiteMage1.currentMp = lessMinMp
            }
        }
    }

})