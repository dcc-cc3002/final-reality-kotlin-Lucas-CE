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
private const val MAX_HP = 100
private const val MAX_MP = 30
private const val DEFENSE = 10

class AbstractPlayerCharacterSpec : FunSpec({
    lateinit var blackMage1: BlackMage
    lateinit var engineer1: Engineer
    lateinit var knight1: Knight
    lateinit var thief1: Thief
    lateinit var whiteMage1: WhiteMage
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var normalWeapon: GameWeapon
    lateinit var magicWeapon: GameWeapon

    beforeEach {
        queue = LinkedBlockingQueue<GameCharacter>()
        blackMage1 = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer1 = Engineer(NAME, MAX_HP, DEFENSE, queue)
        knight1 = Knight(NAME, MAX_HP, DEFENSE, queue)
        thief1 = Thief(NAME, MAX_HP, DEFENSE, queue)
        whiteMage1 = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        normalWeapon = Axe("AXE", 10, 5)
        magicWeapon = Staff("STAFF", 10, 5)
    }

    test("The equip method change de equippedWeapon") {
        engineer1.equip(normalWeapon)
        engineer1.equippedWeapon shouldBe normalWeapon

        knight1.equip(normalWeapon)
        knight1.equippedWeapon shouldBe normalWeapon

        thief1.equip(normalWeapon)
        thief1.equippedWeapon shouldBe normalWeapon

        blackMage1.equip(magicWeapon)
        blackMage1.equippedWeapon shouldBe magicWeapon

        whiteMage1.equip(magicWeapon)
        whiteMage1.equippedWeapon shouldBe magicWeapon
    }
})
