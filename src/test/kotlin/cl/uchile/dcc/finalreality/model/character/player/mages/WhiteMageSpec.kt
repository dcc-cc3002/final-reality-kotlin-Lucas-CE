package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.weapon.types.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.Staff
import cl.uchile.dcc.finalreality.model.weapon.types.Sword
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val WHMG1_NAME = "WHMG1"
private const val WHMG1_MAX_HP = 100
private const val WHMG1_MAX_MP = 30
private const val WHMG1_DEFENSE = 10
private const val WHMG2_NAME = "WHMG2"
private const val WHMG2_MAX_HP = 80
private const val WHMG2_MAX_MP = 20
private const val WHMG2_DEFENSE = 20

class WhiteMageSpec : FunSpec({
    lateinit var whmg1: WhiteMage
    lateinit var whmg2: WhiteMage
    lateinit var whmg12: WhiteMage
    val queue = LinkedBlockingQueue<GameCharacter>()

    beforeEach {
        whmg1 = WhiteMage(WHMG1_NAME, WHMG1_MAX_HP, WHMG1_MAX_MP, WHMG1_DEFENSE, queue)
        whmg2 = WhiteMage(WHMG2_NAME, WHMG2_MAX_HP, WHMG2_MAX_MP, WHMG2_DEFENSE, queue)
        whmg12 = WhiteMage(WHMG1_NAME, WHMG1_MAX_HP, WHMG1_MAX_MP, WHMG1_DEFENSE, queue)
    }

    test("toString must return the White mage description") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val whmg3 = WhiteMage(name, maxHp, maxMp, defense, queue)
            whmg3.toString() shouldBe "WhiteMage {name='$name', maxHp='$maxHp', maxMp='$maxMp', " +
                "defense='$defense'}"
        }
    }

    test("Two White mages with the same parameters are equals") {
        checkAll(
            Arb.string(), Arb.positiveInt(1000), Arb.positiveInt(50),
            Arb.positiveInt(100)
        ) { name, maxHp, maxMp, defense ->
            val whmg31 = WhiteMage(name, maxHp, maxMp, defense, queue)
            val whmg32 = WhiteMage(name, maxHp, maxMp, defense, queue)
            whmg31 shouldNotBeSameInstanceAs whmg32
            whmg31 shouldBe whmg32
        }
    }

    test("Two White mages with different parameters are not equals") {
        whmg1 shouldNotBeSameInstanceAs whmg2
        whmg1 shouldNotBe whmg2
    }

    test("Two White mages with different parameters have not the same hash code") {
        checkAll(
            Arb.string(), Arb.positiveInt(200), Arb.positiveInt(100),
            Arb.positiveInt(50)
        ) { name, maxHp, maxMp, defense ->
            val whmg31 = WhiteMage(name, maxHp, maxMp, defense, queue)
            val whmg32 = WhiteMage(name, maxHp, maxMp, defense, queue)
            whmg31.hashCode() shouldBe whmg32.hashCode()
        }
    }

    test("Two White mages with the same parameters have the same hash code") {
        whmg1.hashCode() shouldBe whmg12.hashCode()
    }

    test("Only white mage weapons can be equipped to white mages") {
        val engineerWeapon2 = Staff("staff", 10, 10)
        whmg1.equip(engineerWeapon2)
        val nonKnightWeapon1 = Axe("axe", 10, 10)
        assertThrows<AssertionError> { whmg1.equip(nonKnightWeapon1) }
        val nonKnightWeapon2 = Bow("bow", 10, 10)
        assertThrows<AssertionError> { whmg1.equip(nonKnightWeapon2) }
        val nonKnightWeapon3 = Knife("knife", 10, 10)
        assertThrows<AssertionError> { whmg1.equip(nonKnightWeapon3) }
        val nonKnightWeapon4 = Sword("sword", 10, 10)
        assertThrows<AssertionError> { whmg1.equip(nonKnightWeapon4) }
    }
})