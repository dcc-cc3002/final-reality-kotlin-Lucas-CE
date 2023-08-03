package cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

private const val NAME = "NAME"
private const val DAMAGE = 10
private const val WEIGHT = 5

class AbstractMagicWeaponSpec : FunSpec({

    test("Magic weapon initial setter set the magic weapon value.") {
        checkAll(Arb.positiveInt(100)) { magic_damage ->
            val staff = Staff(NAME, DAMAGE, WEIGHT, magic_damage)
            staff.magicDmg shouldBe magic_damage
        }
    }
})