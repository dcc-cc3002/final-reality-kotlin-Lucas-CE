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
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.LinkedBlockingQueue

private const val MAGE_NAME = "MAGE1"
private const val MAGE_MAX_HP = 100
private const val MAGE_MAX_MP = 30
private const val MAGE_DEFENSE = 10

class AbstractMagesSpec : FunSpec({
    lateinit var blackMage1: BlackMage
    lateinit var whiteMage1: WhiteMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        blackMage1 = BlackMage(MAGE_NAME, MAGE_MAX_HP, MAGE_MAX_MP, MAGE_DEFENSE, queue)
        whiteMage1 = WhiteMage(MAGE_NAME, MAGE_MAX_HP, MAGE_MAX_MP, MAGE_DEFENSE, queue)
    }

    test("The currentMp initial value is the maxMp value") {
        blackMage1.currentMp shouldBe MAGE_MAX_MP
        whiteMage1.currentMp shouldBe MAGE_MAX_MP
    }

    test("The currentMp setter change the currentMp value") {
        checkAll(Arb.int(0..MAGE_MAX_MP)) {
            currentMp ->
            blackMage1.currentMp = currentMp
            blackMage1.currentMp shouldBe currentMp
        }

        checkAll(Arb.int(0..MAGE_MAX_MP)) {
            currentMp ->
            whiteMage1.currentMp = currentMp
            whiteMage1.currentMp shouldBe currentMp
        }
    }

    test("The currentMp setter throws an exception when the value is out of range (0,maxMp)") {

        checkAll(
            Arb.int(MAGE_MAX_MP + 1..2 * MAGE_MAX_MP),
            Arb.int(-MAGE_MAX_MP..-1)
        ) { greaterMaxMp, lessMinMp ->
            // BlackMage
            assertThrows<InvalidStatValueException> { blackMage1.currentMp = greaterMaxMp }
            assertThrows<InvalidStatValueException> { blackMage1.currentMp = lessMinMp }
            // WhiteMage
            assertThrows<InvalidStatValueException> { whiteMage1.currentMp = greaterMaxMp }
            assertThrows<InvalidStatValueException> { whiteMage1.currentMp = lessMinMp }
        }
    }
})
