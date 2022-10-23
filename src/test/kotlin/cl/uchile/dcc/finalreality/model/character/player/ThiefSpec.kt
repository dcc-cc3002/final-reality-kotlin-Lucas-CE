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

private const val THF1_NAME = "THF1"
private const val THF1_MAXHP = 100
private const val THF1_DEFENSE = 10
private const val THF2_NAME = "THF2"
private const val THF2_MAXHP = 80
private const val THF2_DEFENSE = 20

class ThiefSpec : FunSpec ({
    lateinit var Thf1: Thief
    lateinit var Thf2: Thief
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        Thf1 = Thief(THF1_NAME, THF1_MAXHP, THF1_DEFENSE, queue)
        Thf2 = Thief(THF2_NAME, THF2_MAXHP, THF2_DEFENSE, queue)
    }

    test("toString must return the Thief description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100))
        { name, maxHp, defense ->
            val Thf3 = Thief(name, maxHp, defense, queue)
            Thf3.toString() shouldBe "Thief {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Thiefs with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100))
        { name, maxHp, defense ->
            val Thf31 = Thief(name, maxHp, defense, queue)
            val Thf32 = Thief(name, maxHp, defense, queue)
            Thf31 shouldNotBeSameInstanceAs Thf32
            Thf31 shouldBe Thf32
        }
    }

    test("Two Thiefs with different parameters are not equals") {
        Thf1 shouldNotBeSameInstanceAs Thf2
        Thf1 shouldNotBe Thf2
    }
})