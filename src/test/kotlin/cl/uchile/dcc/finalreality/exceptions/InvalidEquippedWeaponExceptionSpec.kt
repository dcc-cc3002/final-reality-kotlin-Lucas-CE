package cl.uchile.dcc.finalreality.exceptions

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertThrows

class InvalidEquippedWeaponExceptionSpec : FunSpec({
    test("An invalid equipped weapon exception can be thrown with a weapon class name, " +
             "and a player character class name") {
        checkAll(Arb.string(), Arb.string()) { weapon_class_name, player_class_name ->
            assertThrows<InvalidEquippedWeaponException> {
                throw InvalidEquippedWeaponException(weapon_class_name, player_class_name)
            }.message shouldBe "The $weapon_class_name cannot be equipped to an $player_class_name"
        }
    }
})