package cl.uchile.dcc.finalreality.model.character.player.common

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

private const val ENG1_NAME = "ENG1"
private const val ENG1_MAXHP = 100
private const val ENG1_DEFENSE = 10
private const val ENG2_NAME = "ENG2"
private const val ENG2_MAXHP = 80
private const val ENG2_DEFENSE = 20


class EngineerSpec : FunSpec({
    lateinit var Eng1: Engineer
    lateinit var Eng2: Engineer
    lateinit var Eng12: Engineer
    lateinit var queue: LinkedBlockingQueue<GameCharacter>

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        Eng1 = Engineer(ENG1_NAME, ENG1_MAXHP, ENG1_DEFENSE, queue)
        Eng2 = Engineer(ENG2_NAME, ENG2_MAXHP, ENG2_DEFENSE, queue)
        Eng12 = Engineer(ENG1_NAME, ENG1_MAXHP, ENG1_DEFENSE, queue)
    }

    test("toString must return the Engineer description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100))
        { name, maxHp, defense ->
            val Eng3 = Engineer(name, maxHp, defense, queue)
            Eng3.toString() shouldBe "Engineer {name='$name', maxHp='$maxHp', defense='$defense'}"
        }
    }

    test("Two Engineers with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(100))
        { name, maxHp, defense ->
            val Eng31 = Engineer(name, maxHp, defense, queue)
            val Eng32 = Engineer(name, maxHp, defense, queue)
            Eng31 shouldNotBeSameInstanceAs Eng32
            Eng31 shouldBe Eng32
        }
    }

    test("Two Engineers with different parameters are not equals") {
        Eng1 shouldNotBeSameInstanceAs Eng2
        Eng1 shouldNotBe Eng2
    }

    test("Two Engineers with different parameters have not the same hash code"){
        checkAll(Arb.string(), Arb.positiveInt(200), Arb.positiveInt(50))
        { name, maxHp, defense ->
            val Eng31 = Engineer(name, maxHp, defense, queue)
            val Eng32 = Engineer(name, maxHp, defense, queue)
            Eng31.hashCode() shouldBe Eng32.hashCode()
        }
    }

    test("Two Engineers with the same parameters have the same hash code"){
        Eng1.hashCode() shouldBe Eng12.hashCode()
    }
})