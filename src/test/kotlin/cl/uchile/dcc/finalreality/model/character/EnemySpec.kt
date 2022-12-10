package cl.uchile.dcc.finalreality.model.character

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val NAME1 = "NAME1"
private const val WEIGHT1 = 40
private const val MAX_HP1 = 100
private const val DEFENSE1 = 10
private const val NAME2 = "NAME2"
private const val WEIGHT2 = 10
private const val MAX_HP2 = 80
private const val DEFENSE2 = 20

class EnemySpec : FunSpec({
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var enemy1: Enemy
    lateinit var enemy2: Enemy
    lateinit var engineer: Engineer

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        enemy1 = Enemy(NAME1, WEIGHT1, MAX_HP1, DEFENSE1, queue)
        enemy2 = Enemy(NAME2, WEIGHT2, MAX_HP2, DEFENSE2, queue)
        engineer = Engineer(NAME1, MAX_HP1, DEFENSE1, queue)
    }

    test("weight setter throws exception when the value is less 1") {
        checkAll(Arb.int(-WEIGHT1..0)) {
            errorWeight ->
            assertThrows<InvalidStatValueException> {
                val enemyErrorWeight =
                    Enemy(NAME1, errorWeight, MAX_HP1, DEFENSE1, queue)
            }
        }
    }

    test("Enemy can attack to other characters and deals weight / 2 points damage points") {
        val realDamage = WEIGHT1 / 2 - DEFENSE1
        enemy1.attack(engineer)
        if (realDamage in 0..MAX_HP1) {
            engineer.currentHp shouldBe MAX_HP1 - WEIGHT1 / 2 + DEFENSE1
        } else if (realDamage > 0) {
            engineer.currentHp shouldBe 0
        } else {
            engineer.currentHp shouldBe MAX_HP1
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
