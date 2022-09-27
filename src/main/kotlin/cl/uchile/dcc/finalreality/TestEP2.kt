package cl.uchile.dcc.finalreality

import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.Knight
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.Staff
import cl.uchile.dcc.finalreality.model.weapon.types.Sword
import java.util.concurrent.LinkedBlockingQueue
import kotlin.random.Random

fun main() {

    val queue = LinkedBlockingQueue<GameCharacter>()

    val hero1 = Knight("Hero 1", 10, 10, queue)
    val rapier = Sword("Rapier", 9, 5)
    hero1.equip(rapier)

    val hero2 = WhiteMage("Heroe 2", 10, 5, 5, queue)
    val crosier = Staff("crosier", 14, Random.nextInt(1, 50))
    hero2.equip(crosier)

    hero1.waitTurn()
    hero2.waitTurn()

    val evilElf = Enemy("Villain", 10, 100, 10, queue)
    evilElf.waitTurn()

    // Waits for 6 seconds to ensure that all characters have finished waiting
    Thread.sleep(6000)
    while (!queue.isEmpty()) {
        // Pops and prints the names of the characters of the queue to illustrate the turns
        // order
        println(queue.poll())
    }
}
