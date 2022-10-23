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

private const val BLMG1_NAME = "BLMG1"
private const val BLMG1_MAXHP = 100
private const val BLMG1_MAXMP = 30
private const val BLMG1_DEFENSE = 10
private const val BLMG2_NAME = "BLMG2"
private const val BLMG2_MAXHP = 80
private const val BLMG2_MAXMP = 20
private const val BLMG2_DEFENSE = 20


class BlackMageSpec : FunSpec ({
    lateinit var Blmg1: BlackMage
    lateinit var Blmg2: BlackMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        Blmg1 = BlackMage(BLMG1_NAME, BLMG1_MAXHP, BLMG1_MAXMP, BLMG1_DEFENSE, queue)
        Blmg2 = BlackMage(BLMG2_NAME, BLMG2_MAXHP, BLMG2_MAXMP, BLMG2_DEFENSE, queue)
    }

    test("toString must return the Black mage description") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
                 Arb.positiveInt(100))
        { name, maxHp, maxMp, defense ->
            val Blmg3 = BlackMage(name, maxHp, maxMp, defense, queue)
            Blmg3.toString() shouldBe "BlackMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', " +
                                      "defense='$defense'}"
        }
    }

    test("Two Black mages with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
                 Arb.positiveInt(100))
        { name, maxHp, maxMp, defense ->
            val Blmg31 = BlackMage(name, maxHp, maxMp, defense, queue)
            val Blmg32 = BlackMage(name, maxHp, maxMp, defense, queue)
            Blmg31 shouldNotBeSameInstanceAs Blmg32
            Blmg31 shouldBe Blmg32
        }
    }

    test("Two Black mages with different parameters are not equals") {
        Blmg1 shouldNotBeSameInstanceAs Blmg2
        Blmg1 shouldNotBe Blmg2
    }
})