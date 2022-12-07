package cl.uchile.dcc.finalreality.model.weapon

import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Sword
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

private const val NAME = "NAME"
private const val DAMAGE = 10
private const val WEIGHT = 15
private const val MAGIC_DAMAGE = 10
private const val MAX_HP = 100
private const val MAX_MP = 50
private const val DEFENSE = 20

class AbstractWeaponSpec : FunSpec({
    lateinit var axe: Axe
    lateinit var bow: Bow
    lateinit var knife: Knife
    lateinit var sword: Sword

    beforeEach {
        axe = Axe(NAME, DAMAGE, WEIGHT)
        bow = Bow(NAME, DAMAGE, WEIGHT)
        knife = Knife(NAME, DAMAGE, WEIGHT)
        sword = Sword(NAME, DAMAGE, WEIGHT)
    }

    test("The Axe, Bow, Knife, and Sword should have 0 magic damage.") {
        axe.magicDmg shouldBe 0
        bow.magicDmg shouldBe 0
        knife.magicDmg shouldBe 0
        sword.magicDmg shouldBe 0
    }
})