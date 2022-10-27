package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.LinkedBlockingQueue

private const val ENEMY1_NAME = "ENEMY1"
private const val ENEMY1_WEIGHT = 10
private const val ENEMY1_MAX_HP = 100
private const val ENEMY1_DEFENSE = 10
private const val ENEMY2_NAME = "ENEMY2"
private const val ENEMY2_WEIGHT = 10
private const val ENEMY2_MAX_HP = 80
private const val ENEMY2_DEFENSE = 20

class EnemySpec : FunSpec({
    lateinit var enemy1: Enemy
    lateinit var enemy2: Enemy
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        enemy1 = Enemy(ENEMY1_NAME, ENEMY1_WEIGHT, ENEMY1_MAX_HP, ENEMY1_DEFENSE, queue)
        enemy2 = Enemy(ENEMY2_NAME, ENEMY2_WEIGHT, ENEMY2_MAX_HP, ENEMY2_DEFENSE, queue)
    }

    test("weight setter throws exception when the value is less 1") {
        checkAll(Arb.int(-ENEMY1_WEIGHT..0)) {
            errorWeight ->
            assertThrows<InvalidStatValueException> {
                val enemyErrorWeight =
                    Enemy(ENEMY1_NAME, errorWeight, ENEMY1_MAX_HP, ENEMY1_DEFENSE, queue)
            }
        }
    }

    test("toString must return the Enemy description") {
        checkAll(
            Arb.string(), Arb.positiveInt(20), Arb.positiveInt(1000),
            Arb.positiveInt(100)
        ) { name, weight, maxHp, defense ->
            val enemy3 = Enemy(name, weight, maxHp, defense, queue)
            enemy3.toString() shouldBe "Enemy {name='$name', weight='$weight', maxHp='$maxHp', " +
                "defense='$defense'}"
        }
    }

    test("Two Enemies with the same parameters are equals") {
        checkAll(
            Arb.string(), Arb.positiveInt(20), Arb.positiveInt(1000),
            Arb.positiveInt(100)
        ) { name, weight, maxHp, defense ->
            val enemy31 = Enemy(name, weight, maxHp, defense, queue)
            val enemy32 = Enemy(name, weight, maxHp, defense, queue)
            enemy31 shouldNotBeSameInstanceAs enemy32
            enemy31 shouldBe enemy32
        }
    }

    test("Two Enemies with different parameters are not equals") {
        enemy1 shouldNotBeSameInstanceAs enemy2
        enemy1 shouldNotBe enemy2
    }

    test("Two Enemies with the same parameters have the same hash code") {
        checkAll(
            Arb.string(), Arb.positiveInt(20), Arb.positiveInt(1000),
            Arb.positiveInt(100)
        ) { name, weight, maxHp, defense ->
            val enemy31 = Enemy(name, weight, maxHp, defense, queue)
            val enemy32 = Enemy(name, weight, maxHp, defense, queue)
            enemy31.hashCode() shouldBe enemy32.hashCode()
        }
    }

    test("Two Enemies with different parameters have not the same hash code") {
        enemy1.hashCode() shouldNotBe enemy2.hashCode()
    }
})
