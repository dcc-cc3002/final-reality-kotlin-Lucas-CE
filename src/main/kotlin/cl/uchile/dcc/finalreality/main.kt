package cl.uchile.dcc.finalreality

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException
import cl.uchile.dcc.finalreality.model.character.Enemy
import cl.uchile.dcc.finalreality.model.character.GameCharacter
import cl.uchile.dcc.finalreality.model.character.player.BlackMage
import cl.uchile.dcc.finalreality.model.character.player.Engineer
import cl.uchile.dcc.finalreality.model.character.player.Knight
import cl.uchile.dcc.finalreality.model.character.player.Thief
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage
import cl.uchile.dcc.finalreality.model.weapon.types.Axe
import cl.uchile.dcc.finalreality.model.weapon.types.Bow
import cl.uchile.dcc.finalreality.model.weapon.types.Knife
import cl.uchile.dcc.finalreality.model.weapon.types.Staff
import cl.uchile.dcc.finalreality.model.weapon.types.Sword
import java.util.concurrent.LinkedBlockingQueue
import kotlin.random.Random

fun main() {

    //Queue creation
    val queue = LinkedBlockingQueue<GameCharacter>()

    //Instance Characters
    val per1 = Knight("Hero 1", 10, 10, queue)
    val per2 = Engineer("Hero 2", 10, 10, queue)
    val per3 = Thief("Hero 3", 10, 10, queue)
    val per4 = BlackMage("Hero 4", 10, 10, 10, queue)
    val per5 = WhiteMage("Hero 5", 10, 10, 10, queue)
    val ene1 = Enemy("Villain", 5, 100, 20, queue)

    //Instance Weapons
    val wep1 = Axe("Weapon 1", 9, Random.nextInt(1, 50))
    val wep2 = Bow("Weapon 2", 9, Random.nextInt(1, 50))
    val wep3 = Knife("Weapon 3", 9, Random.nextInt(1, 50))
    val wep4 = Staff("Weapon 4", 9, Random.nextInt(1, 50))
    val wep5 = Sword("Weapon 5", 9, Random.nextInt(1, 50))

    //Player characters .equip(Weapon)
    per1.equip(wep1)
    per2.equip(wep2)
    per3.equip(wep3)
    per4.equip(wep4)
    per5.equip(wep5)

    //Game Character .waitTurn()
    per1.waitTurn()
    per2.waitTurn()
    per3.waitTurn()
    per4.waitTurn()
    per5.waitTurn()
    ene1.waitTurn()

    //Test the queue
    // Waits for 6 seconds to ensure that all characters have finished waiting
    Thread.sleep(6000)
    while (!queue.isEmpty()) {
        // Pops and prints the names of the characters of the queue to illustrate the turns
        // order
        println(queue.poll())
    }

    //Test equals
    val per11 = Knight("Hero 1", 10, 10, queue)
    val per12 = Knight("Hero 1", 10, 10, queue)
    val per13 = Knight("Hero 1", 20, 10, queue)
    println("Should return true, returns :" + per11.equals(per12))
    println("Should return false, returns :" + per11.equals(per13))

    val wep11 = Axe("Weapon 1", 9, 1)
    val wep12 = Axe("Weapon 1", 9, 1)
    val wep13 = Sword("Weapon 1", 9, 1)
    println("Should return true, returns :" + wep11.equals(wep12))
    println("Should return false, returns :" + wep11.equals(wep13))

    //Test toStrings
    println("toString Knight character : $per11")
    println("toString Axe character : $wep11")

    //Limits properties
    try {
        per11.currentHp = -1
    } catch (Ex : InvalidStatValueException){
        println("currentHp : cannot be out of range (0, maxHp)")
    }

    try {
        val per21 = Knight("test", 0, 1, queue)
    } catch (Ex : InvalidStatValueException){
        println("maxHp : cannot be less than 0")
    }

    try {
        val per22 = Knight("test", 1, -1, queue)
    } catch (Ex : InvalidStatValueException){
        println("defense : cannot be less than 0")
    }

}
