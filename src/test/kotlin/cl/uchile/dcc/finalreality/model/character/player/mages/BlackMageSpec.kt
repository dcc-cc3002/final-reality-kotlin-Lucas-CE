package cl.uchile.dcc.finalreality.model.character.player.mages

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

private const val BLMG1_NAME = "BLMG1"
private const val BLMG1_MAX_HP = 100
private const val BLMG1_MAX_MP = 30
private const val BLMG1_DEFENSE = 10
private const val BLMG2_NAME = "BLMG2"
private const val BLMG2_MAX_HP = 80
private const val BLMG2_MAX_MP = 20
private const val BLMG2_DEFENSE = 20

class BlackMageSpec : FunSpec({
    lateinit var blmg1: BlackMage
    lateinit var blmg2: BlackMage
    lateinit var blmg12: BlackMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        blmg1 = BlackMage(BLMG1_NAME, BLMG1_MAX_HP, BLMG1_MAX_MP, BLMG1_DEFENSE, queue)
        blmg2 = BlackMage(BLMG2_NAME, BLMG2_MAX_HP, BLMG2_MAX_MP, BLMG2_DEFENSE, queue)
        blmg12 = BlackMage(BLMG1_NAME, BLMG1_MAX_HP, BLMG1_MAX_MP, BLMG1_DEFENSE, queue)
    }

    test("toString must return the Black mage description") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val blmg3 = BlackMage(name, maxHp, maxMp, defense, queue)
            blmg3.toString() shouldBe "BlackMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', " +
                "defense='$defense'}"
        }
    }

    test("Two Black mages with the same parameters are equals") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val blmg31 = BlackMage(name, maxHp, maxMp, defense, queue)
            val blmg32 = BlackMage(name, maxHp, maxMp, defense, queue)
            blmg31 shouldNotBeSameInstanceAs blmg32
            blmg31 shouldBe blmg32
        }
    }

    test("Two Black mages with different parameters are not equals") {
        blmg1 shouldNotBeSameInstanceAs blmg2
        blmg1 shouldNotBe blmg2
    }

    test("Two Black mages with different parameters have not the same hash code") {
        checkAll(
            Arb.string(), Arb.positiveInt(200), Arb.positiveInt(100),
            Arb.positiveInt(50)
        ) { name, maxHp, maxMp, defense ->
            val blmg31 = BlackMage(name, maxHp, maxMp, defense, queue)
            val blmg32 = BlackMage(name, maxHp, maxMp, defense, queue)
            blmg31.hashCode() shouldBe blmg32.hashCode()
        }
    }

    test("Two Black mages with the same parameters have the same hash code") {
        blmg1.hashCode() shouldBe blmg12.hashCode()
    }
})
