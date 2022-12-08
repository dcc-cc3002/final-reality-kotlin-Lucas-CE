package cl.uchile.dcc.finalreality.model.character.player.mages

import cl.uchile.dcc.finalreality.controller.GameController
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.common.Engineer
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Fire
import cl.uchile.dcc.finalreality.model.character.player.spells.blackMageSpells.Thunder
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Heal
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Paralysis
import cl.uchile.dcc.finalreality.model.character.player.spells.whiteMageSpells.Poison
import io.kotest.core.spec.style.FunSpec
import java.util.concurrent.LinkedBlockingQueue

private const val NAME = "NAME"
private const val MAX_HP = 100
private const val MAX_MP = 30
private const val DEFENSE = 20

class AbstractMageSpec : FunSpec({
    lateinit var gameController: GameController
    lateinit var queue: LinkedBlockingQueue<GameCharacter>
    lateinit var whiteMage: WhiteMage
    lateinit var blackMage: BlackMage
    lateinit var thunder: Thunder
    lateinit var fire: Fire
    lateinit var heal: Heal
    lateinit var paralysis: Paralysis
    lateinit var poison: Poison
    lateinit var engineer: Engineer
    beforeEach() {
        gameController = GameController()
        queue = LinkedBlockingQueue<GameCharacter>()
        whiteMage = WhiteMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        blackMage = BlackMage(NAME, MAX_HP, MAX_MP, DEFENSE, queue)
        thunder = Thunder()
        fire = Fire()
        heal = Heal()
        paralysis = Paralysis()
        poison = Poison()
    }
})