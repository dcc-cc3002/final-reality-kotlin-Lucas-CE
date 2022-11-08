package cl.uchile.dcc.finalreality.exceptions

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val NAME = "ENGINEER1"
private const val MAX_HP = 100
private const val DEFENSE = 10
private const val WEAPON_NAME = "WEAPON1"
private const val WEAPON_DAMAGE = 10
private const val WEAPON_WEIGHT = 5

class InvalidEquippedWeaponExceptionSpec : FunSpec({
    lateinit var player1: Engineer
    lateinit var weapon1: Axe
    lateinit var queue: LinkedBlockingQueue<GameCharacter>

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        player1 = Engineer(NAME, MAX_HP, DEFENSE, queue)
        weapon1 = Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT)
    }

    test(
        "An invalid equipped weapon exception can be thrown with a weapon class name, " +
            "and a player character class name"
    ) {
        assertThrows<InvalidEquippedWeaponException> {
                throw InvalidEquippedWeaponException(weapon1, player1)
            }.message shouldBe "The $weapon1 class cannot be equipped to a $player1 class"
        }
})
