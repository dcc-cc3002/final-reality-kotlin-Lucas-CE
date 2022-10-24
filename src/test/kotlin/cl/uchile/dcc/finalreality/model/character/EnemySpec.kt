package cl.uchile.dcc.finalreality.model.character

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue

private const val ENEM1_NAME = "ENEM1"
private const val ENEM1_WEIGHT = 10
private const val ENEM1_MAXHP = 100
private const val ENEM1_DEFENSE = 10
private const val ENEM2_NAME = "ENEM2"
private const val ENEM2_WEIGHT = 10
private const val ENEM2_MAXHP = 80
private const val ENEM2_DEFENSE = 20


class EnemySpec : FunSpec ({
    lateinit var Enem1: Enemy
    lateinit var Enem2: Enemy
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        Enem1 = Enemy(ENEM1_NAME, ENEM1_WEIGHT, ENEM1_MAXHP, ENEM1_DEFENSE, queue)
        Enem2 = Enemy(ENEM2_NAME, ENEM2_WEIGHT, ENEM2_MAXHP, ENEM2_DEFENSE, queue)
    }

    test("toString must return the Enemy description") {
        checkAll(Arb.string(), Arb.positiveInt(20),Arb.positiveInt(1000),
                 Arb.positiveInt(100))
        { name, weight, maxHp, defense ->
            val Enemy3 = Enemy(name, weight, maxHp, defense, queue)
            Enemy3.toString() shouldBe "Enemy {name='$name', weight='$weight', maxHp='$maxHp', " +
                                            "defense='$defense'}"
        }
    }

    test("Two Enemies with the same parameters are equals") {
        checkAll(Arb.string(), Arb.positiveInt(20),Arb.positiveInt(1000),
                 Arb.positiveInt(100))
        { name, weight, maxHp, defense ->
            val Enem31 = Enemy(name, weight, maxHp, defense, queue)
            val Enem32 = Enemy(name, weight, maxHp, defense, queue)
            Enem31 shouldNotBeSameInstanceAs Enem32
            Enem31 shouldBe Enem32
        }
    }

    test("Two Enemies with different parameters are not equals") {
        Enem1 shouldNotBeSameInstanceAs Enem2
        Enem1 shouldNotBe Enem2
    }

    test("Two Enemies with the same parameters have the same hash code"){
        checkAll(Arb.string(), Arb.positiveInt(20),Arb.positiveInt(1000),
                 Arb.positiveInt(100))
        { name, weight, maxHp, defense ->
            val Enem31 = Enemy(name, weight, maxHp, defense, queue)
            val Enem32 = Enemy(name, weight, maxHp, defense, queue)
            Enem31.hashCode() shouldBe Enem32.hashCode()
        }
    }

    test("Two Enemies with different parameters have not the same hash code"){
        Enem1.hashCode() shouldNotBe Enem2.hashCode()
    }
})