Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of
enemies controlled by the computer.

---

The main code content is in src/.../finalreality folder. Here are the exceptions, models, and main.kt. The tests are located in the file main.kt.

The model folder is divided into characters and weapons.

The main changes that were made in the base code were:
<ul>
  <li>Weapon Class Design</li>
  <li>MageCharacters and CommonCharacters class creation</li>
  <li>Method waitTurn() implementation</li>
  <li>main file creation to test the methods and classes</li>
</ul>

After the base code changes, the design and implementation of the classes are as follows.

![image](https://user-images.githubusercontent.com/100120556/193377973-5a4ee8f2-7855-4267-ba11-5cf6714fc9d4.png)


The weapons folder contains the weapon interface, the abstract weapon class, and a folder that contains weapon types. The weapon types are divided into the abstract classes NormalWeapons and MagicWeapons. Classes that inherit from NormalWeapons are Axe, Bow, Knife, and Sword. The class that inherits from MagicWeapons is Staff.

The character folder contains the game character interface, the abstract character class, the enemy class, and a folder containing all classes related to the player characters. The player character is divided into the abstract classes CommonCharacter and Mages. Classes that inherit from NormalWeapons are Engineer, Knight, and Thief. The classes that inherit from MagicWeapons are BlackMage and WhiteMage.




