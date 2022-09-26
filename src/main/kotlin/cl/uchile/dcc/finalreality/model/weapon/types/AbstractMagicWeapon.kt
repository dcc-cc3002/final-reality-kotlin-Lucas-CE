package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.weapon.AbstractWeapon

abstract class AbstractMagicWeapon(
    name: String,
    magicDamage: Int,
    weight: Int
): AbstractWeapon(name, magicDamage, weight)