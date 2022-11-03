package cl.uchile.dcc.finalreality.exceptions

/**
 * This error is used to represent an invalid stat value.
 *
 * @constructor Creates a new `InvalidEquippedWeaponException` with a `description` of the
 * error.
 *
 * @author <a href="https://github.com/Lucas-CE">Lucase</a>
 */

class InvalidEquippedWeaponException(weapon_class_name: String, class_name: String) :
    Exception("The $weapon_class_name cannot be equipped to an $class_name")