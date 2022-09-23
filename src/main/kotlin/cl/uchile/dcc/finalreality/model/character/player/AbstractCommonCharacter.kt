package cl.uchile.dcc.finalreality.model.character.player

import cl.uchile.dcc.finalreality.model.character.GameCharacter
import java.util.concurrent.BlockingQueue

abstract class AbstractCommonCharacter (
    name: String,
    maxHp: Int,
    defense: Int,
    turnsQueue: BlockingQueue<GameCharacter>
) : AbstractPlayerCharacter(name, maxHp, defense, turnsQueue)