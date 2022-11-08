package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedWeaponException
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.commonWeapons.Sword
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue
import org.junit.jupiter.api.assertThrows

private const val NAME = "NAME"
private const val MAX_HP = 100
private const val MAX_MP = 30
private const val DEFENSE = 10
private const val WEP_NAME = "WEP"
private const val WEP_DMG = 10
private const val WEP_WGT = 10

class AbstractPlayerCharacterSpec : FunSpec({
    lateinit var blackMage1: BlackMage
    lateinit var engineer1: Engineer
    lateinit var knight1: Knight
    lateinit var thief1: Thief
    lateinit var whiteMage1: WhiteMage
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var axe: Axe
    lateinit var bow: Bow
    lateinit var knife: Knife
    lateinit var staff: Staff
    lateinit var sword: Sword

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blackMage1 = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer1 = Engineer(NAME, MAX_HP, DEFENSE, queue)
        knight1 = Knight(NAME, MAX_HP, DEFENSE, queue)
        thief1 = Thief(NAME, MAX_HP, DEFENSE, queue)
        whiteMage1 = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        axe = Axe(WEP_NAME, WEP_DMG, WEP_WGT)
        bow = Bow(WEP_NAME, WEP_DMG, WEP_WGT)
        knife = Knife(WEP_NAME, WEP_DMG, WEP_WGT)
        staff = Staff(WEP_NAME, WEP_DMG, WEP_WGT)
        sword = Sword(WEP_NAME, WEP_DMG, WEP_WGT)
    }

    test("The equip method change de equippedWeapon") {
        //Staff is a mage weapon
        blackMage1.equip(staff)
        blackMage1.equippedWeapon shouldBe staff

        whiteMage1.equip(staff)
        whiteMage1.equippedWeapon shouldBe staff

        //Axe is a engineer weapon
        engineer1.equip(axe)
        engineer1.equippedWeapon shouldBe axe

        //Knife is a knight weapon
        knight1.equip(knife)
        knight1.equippedWeapon shouldBe knife

        //Sword is a thief weapon
        thief1.equip(sword)
        thief1.equippedWeapon shouldBe sword
    }

    test("Trying to equip weapons to wrong classes throws an exception") {
        //Using equip
        assertThrows<InvalidEquippedWeaponException> { blackMage1.equip(sword) }
        assertThrows<InvalidEquippedWeaponException> { whiteMage1.equip(knife) }
        assertThrows<InvalidEquippedWeaponException> { engineer1.equip(staff) }
        assertThrows<InvalidEquippedWeaponException> { knight1.equip(bow) }
        assertThrows<InvalidEquippedWeaponException> { thief1.equip(axe) }

        //Using equipWeapon
        assertThrows<InvalidEquippedWeaponException> { blackMage1.equipSword(sword) }
        assertThrows<InvalidEquippedWeaponException> { whiteMage1.equipKnife(knife) }
        assertThrows<InvalidEquippedWeaponException> { engineer1.equipStaff(staff) }
        assertThrows<InvalidEquippedWeaponException> { knight1.equipBow(bow) }
        assertThrows<InvalidEquippedWeaponException> { thief1.equipAxe(axe) }
    }


})
