package cl.uchile.dcc.finalreality.model.weapon.types

import cl.uchile.dcc.finalreality.model.weapon.AbstractWeapon

class Axe(
    name: String,
    damage: Int,
    weight: Int
): AbstractNormalWeapon(name, damage, weight)