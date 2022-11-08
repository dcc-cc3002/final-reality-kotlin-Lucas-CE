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
    lateinit var mageWeapon: Staff
    lateinit var engineerWeapon: Axe
    lateinit var knightWeapon: Sword
    lateinit var thiefWeapon: Knife
    lateinit var notMageWeapon: Axe
    lateinit var notEngineerWeapon: Sword
    lateinit var notKnightWeapon: Bow
    lateinit var notThiefWeapon: Staff

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blackMage1 = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer1 = Engineer(NAME, MAX_HP, DEFENSE, queue)
        knight1 = Knight(NAME, MAX_HP, DEFENSE, queue)
        thief1 = Thief(NAME, MAX_HP, DEFENSE, queue)
        whiteMage1 = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        mageWeapon = Staff(WEP_NAME, WEP_DMG, WEP_WGT)
        engineerWeapon = Axe(WEP_NAME, WEP_DMG, WEP_WGT)
        knightWeapon = Sword(WEP_NAME, WEP_DMG, WEP_WGT)
        thiefWeapon = Knife(WEP_NAME, WEP_DMG, WEP_WGT)
        notMageWeapon = Axe(WEP_NAME, WEP_DMG, WEP_WGT)
        notEngineerWeapon = Sword(WEP_NAME, WEP_DMG, WEP_WGT)
        notKnightWeapon = Bow(WEP_NAME, WEP_DMG, WEP_WGT)
        notThiefWeapon = Staff(WEP_NAME, WEP_DMG, WEP_WGT)
    }

    test("The equip method change de equippedWeapon") {
        blackMage1.equip(mageWeapon)
        blackMage1.equippedWeapon shouldBe mageWeapon

        whiteMage1.equip(mageWeapon)
        whiteMage1.equippedWeapon shouldBe mageWeapon

        engineer1.equip(engineerWeapon)
        engineer1.equippedWeapon shouldBe engineerWeapon

        knight1.equip(knightWeapon)
        knight1.equippedWeapon shouldBe knightWeapon

        thief1.equip(thiefWeapon)
        thief1.equippedWeapon shouldBe thiefWeapon
    }

    test("Trying to equip weapons to wrong classes throws an exception") {
        assertThrows<InvalidEquippedWeaponException> { blackMage1.equip(notMageWeapon) }
        assertThrows<InvalidEquippedWeaponException> { whiteMage1.equip(notMageWeapon) }
        assertThrows<InvalidEquippedWeaponException> { engineer1.equip(notEngineerWeapon) }
        assertThrows<InvalidEquippedWeaponException> { knight1.equip(notKnightWeapon) }
        assertThrows<InvalidEquippedWeaponException> { thief1.equip(notThiefWeapon) }
    }
})
