package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.weapon.AbstractWeapon

abstract class AbstractMagicWeapon(
    name: String,
    damage: Int,
    weight: Int
): AbstractWeapon(name, damage, weight)