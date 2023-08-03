package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.exceptions.InvalidEquippedSpellException
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import cl.uchile.dcc.finalreality.model.weapon.types.magicWeapons.Staff
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import java.util.concurrent.LinkedBlockingQueue
import kotlin.random.Random
import org.junit.jupiter.api.assertThrows

private const val NAME = "NAME"
private const val MAX_HP = 100
private const val MAX_MP = 30
private const val DEFENSE = 20
private const val WEIGHT = 5
private const val DAMAGE = 30
private const val MAGIC_DAMAGE = 30

class AbstractMageSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var whiteMage: WhiteMage
    lateinit var blackMage: BlackMage
    lateinit var staff: Staff
    lateinit var thunder: Thunder
    lateinit var fire: Fire
    lateinit var heal: Heal
    lateinit var paralysis: Paralysis
    lateinit var poison: Poison
    lateinit var engineer: Engineer
    lateinit var enemy: Enemy
    beforeEach() {
        gameController = GameController()
        queue = LinkedBlockingQueue<GameCharacter>()
        whiteMage = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        blackMage = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        engineer = Engineer(NAME, MAX_HP, DEFENSE, queue)
        enemy = Enemy(NAME, WEIGHT, MAX_HP, DEFENSE, queue)
        staff = Staff(NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE)
        thunder = Thunder()
        fire = Fire()
        heal = Heal()
        paralysis = Paralysis()
        poison = Poison()
        whiteMage.equip(staff)
        blackMage.equip(staff)
        whiteMage.equipSpell(heal)
        blackMage.equipSpell(thunder)
    }
    test("The currentMp initial value is the maxMp value") {
        blackMage.currentMp shouldBe MAX_MP
        whiteMage.currentMp shouldBe MAX_MP
    }
    test("The currentMp setter change the currentMp value") {
        checkAll(Arb.int(0..MAX_MP)) {
            currentMp ->
            blackMage.currentMp = currentMp
            blackMage.currentMp shouldBe currentMp
        }
        checkAll(Arb.int(0..MAX_MP)) {
            currentMp ->
            whiteMage.currentMp = currentMp
            whiteMage.currentMp shouldBe currentMp
        }
    }
    test("The currentMp setter throws an exception when the value is out of range (0,maxMp)") {
        checkAll(
            Arb.int(MAX_MP + 1..2 * MAX_MP),
            Arb.int(-MAX_MP..-1)
        ) { greaterMaxMp, lessMinMp ->
            // BlackMage
            assertThrows<InvalidStatValueException> { blackMage.currentMp = greaterMaxMp }
            assertThrows<InvalidStatValueException> { blackMage.currentMp = lessMinMp }
            // WhiteMage
            assertThrows<InvalidStatValueException> { whiteMage.currentMp = greaterMaxMp }
            assertThrows<InvalidStatValueException> { whiteMage.currentMp = lessMinMp }
        }
    }
    test("Trying to equip spells to wrong mages classes throws an exception and not change spell") {
        // Using equipSpell
        assertThrows<InvalidEquippedSpellException> { whiteMage.equipSpell(fire) }
        whiteMage.spell shouldNotBe fire
        assertThrows<InvalidEquippedSpellException> { whiteMage.equipSpell(thunder) }
        whiteMage.spell shouldNotBe thunder
        assertThrows<InvalidEquippedSpellException> { blackMage.equipSpell(heal) }
        blackMage.spell shouldNotBe heal
        assertThrows<InvalidEquippedSpellException> { blackMage.equipSpell(paralysis) }
        blackMage.spell shouldNotBe paralysis
        assertThrows<InvalidEquippedSpellException> { blackMage.equipSpell(poison) }
        blackMage.spell shouldNotBe poison

        // Using equip{SpellName}
        assertThrows<InvalidEquippedSpellException> { whiteMage.equipSpellFire(fire) }
        whiteMage.spell shouldNotBe fire
        assertThrows<InvalidEquippedSpellException> { whiteMage.equipSpellThunder(thunder) }
        whiteMage.spell shouldNotBe thunder
        assertThrows<InvalidEquippedSpellException> { blackMage.equipSpellHeal(heal) }
        blackMage.spell shouldNotBe heal
        assertThrows<InvalidEquippedSpellException> { blackMage.equipSpellParalysis(paralysis) }
        blackMage.spell shouldNotBe paralysis
        assertThrows<InvalidEquippedSpellException> { blackMage.equipSpellPoison(poison) }
        blackMage.spell shouldNotBe poison
    }
    test("white mage can cure to other characters with heal spell") {
        whiteMage.equipSpell(heal)
        engineer.currentHp = engineer.currentHp / 2
        whiteMage.throwSpell(engineer)
        engineer.currentHp shouldBe MAX_HP * 0.8
    }
    test("white mage can paralyse to an enemy") {
        whiteMage.equipSpell(paralysis)
        whiteMage.addListener(gameController)
        enemy.addListener(gameController)
        whiteMage.throwSpell(enemy)
        gameController.paralyzedCharacters.contains(enemy) shouldBe true
    }
    test("white mage can poison to an enemy") {
        whiteMage.equipSpell(poison)
        enemy.addListener(gameController)
        whiteMage.throwSpell(enemy)
        gameController.poisonedCharacters.contains(enemy) shouldBe true
    }
    test("black mage can throw fire to an enemy") {
        blackMage.equipSpell(fire)
        enemy.addListener(gameController)
        val random = Random(100)
        repeat(100) {
            enemy.currentHp = MAX_HP
            blackMage.throwSpell(enemy)
            enemy.currentHp shouldBe MAX_HP - MAGIC_DAMAGE + DEFENSE
            if (random.nextDouble() < 0.2) {
                gameController.burnedCharacters.contains(enemy) shouldBe true
            }
        }
    }
    test("black mage can throw thunder to an enemy") {
        blackMage.equipSpell(thunder)
        enemy.addListener(gameController)
        val random = Random(100)
        repeat(100) {
            enemy.currentHp = MAX_HP
            blackMage.throwSpell(enemy)
            enemy.currentHp shouldBe MAX_HP - MAGIC_DAMAGE + DEFENSE
            while (Random(100).nextDouble() < 0.3) {
                gameController.paralyzedCharacters.contains(enemy) shouldBe true
            }
        }
    }
})
