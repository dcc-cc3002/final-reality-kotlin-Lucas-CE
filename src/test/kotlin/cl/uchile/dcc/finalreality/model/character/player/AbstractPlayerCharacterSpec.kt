package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.common.Knight
import cl.uchile.dcc.finalreality.model.character.player.common.Thief
import cl.uchile.dcc.finalreality.model.character.player.mages.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.mages.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.GameWeapon
import cl.uchile.dcc.finalreality.model.weapon.types.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.concurrent.LinkedBlockingQueue

private const val NAME = "NAME"
private const val MAXHP = 100
private const val MAXMP = 30
private const val DEFENSE = 10

class AbstractPlayerCharacterSpec :FunSpec ({
    lateinit var BlackMage1: BlackMage
    lateinit var Engineer1: Engineer
    lateinit var Knight1: Knight
    lateinit var Thief1: Thief
    lateinit var WhiteMage1: WhiteMage
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var normalWeapon: GameWeapon
    lateinit var magicWeapon: GameWeapon

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        BlackMage1 = BlackMage(NAME, MAXHP, MAXMP, DEFENSE, queue)
        Engineer1 = Engineer(NAME, MAXHP, DEFENSE, queue)
        Knight1 = Knight(NAME, MAXHP, DEFENSE, queue)
        Thief1 = Thief(NAME, MAXHP, DEFENSE, queue)
        WhiteMage1 = WhiteMage(NAME, MAXHP, MAXMP, DEFENSE, queue)
        normalWeapon = Axe("AXE", 10, 5)
        magicWeapon = Staff("STAFF", 10, 5)
    }

    test("The equip method change de equippedWeapon"){
        Engineer1.equip(normalWeapon)
        Engineer1.equippedWeapon shouldBe normalWeapon

        Knight1.equip(normalWeapon)
        Knight1.equippedWeapon shouldBe normalWeapon

        Thief1.equip(normalWeapon)
        Thief1.equippedWeapon shouldBe normalWeapon

        BlackMage1.equip(magicWeapon)
        BlackMage1.equippedWeapon shouldBe magicWeapon

        WhiteMage1.equip(magicWeapon)
        WhiteMage1.equippedWeapon shouldBe magicWeapon
    }
})