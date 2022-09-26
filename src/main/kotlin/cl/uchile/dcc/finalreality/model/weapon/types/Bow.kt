package cl.uchile.dcc.finalreality.model.weapon.types

import java.util.Objects

class Bow(
    name: String,
    damage: Int,
    weight: Int
): AbstractNormalWeapon(name, damage, weight){

    override fun equals(other: Any?) = when {
        this === other -> true
        other !is Bow -> false
        hashCode() != other.hashCode() -> false
        name != other.name -> false
        damage != other.damage -> false
        weight != other.weight -> false
        else -> true
    }

    override fun hashCode() = Objects.hash(Bow::class, name, damage, weight)

    override fun toString() = "Bow { name: $name, damage: $damage, weight: $weight}"
}