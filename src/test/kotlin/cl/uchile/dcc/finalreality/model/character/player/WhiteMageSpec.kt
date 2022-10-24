package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue

private const val WHMG1_NAME = "WHMG1"
private const val WHMG1_MAXHP = 100
private const val WHMG1_MAXMP = 30
private const val WHMG1_DEFENSE = 10
private const val WHMG2_NAME = "WHMG2"
private const val WHMG2_MAXHP = 80
private const val WHMG2_MAXMP = 20
private const val WHMG2_DEFENSE = 20

class WhiteMageSpec : FunSpec ({
    lateinit var Whmg1: WhiteMage
    lateinit var Whmg2: WhiteMage
    lateinit var Whmg12: WhiteMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        Whmg1 = WhiteMage(WHMG1_NAME, WHMG1_MAXHP, WHMG1_MAXMP, WHMG1_DEFENSE, queue)
        Whmg2 = WhiteMage(WHMG2_NAME, WHMG2_MAXHP, WHMG2_MAXMP, WHMG2_DEFENSE, queue)
        Whmg12 = WhiteMage(WHMG1_NAME, WHMG1_MAXHP, WHMG1_MAXMP, WHMG1_DEFENSE, queue)
    }

    test("toString must return the White mage description") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100))
        { name, maxHp, maxMp, defense ->
            val Whmg3 = WhiteMage(name, maxHp, maxMp, defense, queue)
            Whmg3.toString() shouldBe "WhiteMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', " +
                "defense='$defense'}"
        }
    }

    test("Two White mages with the same parameters are equals") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100))
        { name, maxHp, maxMp, defense ->
            val Whmg31 = WhiteMage(name, maxHp, maxMp, defense, queue)
            val Whmg32 = WhiteMage(name, maxHp, maxMp, defense, queue)
            Whmg31 shouldNotBeSameInstanceAs Whmg32
            Whmg31 shouldBe Whmg32
        }
    }

    test("Two White mages with different parameters are not equals") {
        Whmg1 shouldNotBeSameInstanceAs Whmg2
        Whmg1 shouldNotBe Whmg2
    }

    test("Two White mages with different parameters have not the same hash code"){
        Whmg1.hashCode() shouldNotBe Whmg2.hashCode()
    }

    test("Two White mages with the same parameters have the same hash code"){
        Whmg1.hashCode() shouldBe Whmg12.hashCode()
    }
})