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

private const val KNGT1_NAME = "KNGT1"
private const val KNGT1_MAXHP = 100
private const val KNGT1_DEFENSE = 10
private const val KNGT2_NAME = "KNGT2"
private const val KNGT2_MAXHP = 80
private const val KNGT2_DEFENSE = 20

class KnightSpec : FunSpec ({
    lateinit var Kngt1: Knight
    lateinit var Kngt2: Knight
    lateinit var Kngt12: Knight
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        Kngt1 = Knight(KNGT1_NAME, KNGT1_MAXHP, KNGT1_DEFENSE, queue)
        Kngt2 = Knight(KNGT2_NAME, KNGT2_MAXHP, KNGT2_DEFENSE, queue)
        Kngt12 = Knight(KNGT1_NAME, KNGT1_MAXHP, KNGT1_DEFENSE, queue)
    }

    test("toString must return the Knight description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100))
        { name, maxHp, defense ->
            val Knight3 = Knight(name, maxHp, defense, queue)
            Knight3.toString() shouldBe "Knight {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Knights with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100))
        { name, maxHp, defense ->
            val Kngt31 = Knight(name, maxHp, defense, queue)
            val Kngt32 = Knight(name, maxHp, defense, queue)
            Kngt31 shouldNotBeSameInstanceAs Kngt32
            Kngt31 shouldBe Kngt32
        }
    }

    test("Two Knights with different parameters are not equals") {
        Kngt1 shouldNotBeSameInstanceAs Kngt2
        Kngt1 shouldNotBe Kngt2
    }

    test("Two Knights with different parameters have not the same hash code"){
        Kngt1.hashCode() shouldNotBe Kngt2.hashCode()
    }

    test("Two Knights with the same parameters have the same hash code"){
        Kngt1.hashCode() shouldBe Kngt12.hashCode()
    }
})