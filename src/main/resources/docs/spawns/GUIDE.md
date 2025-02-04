# Spawn Modding

Modding the spawn Json files is extremely easy.
All you need is a basic understanding of how Json files work, and the ability to read.

## Setting Up for Modding

The first thing you should do is understand how to launch from a terminal/shell.
All you need to do is type:

```shell
java -jar [NAME-OF-JAR].jar
```

While in a terminal/shell window in the folder where the game is contained.

Now you should be able to see anything output to the terminal from the game like errors or warnings.

## Basic Structure

The Json is structured with the ability to easily modify it, and to easily create your own.
The very first thing in the Json is the ``{}``. This is important so the Json is read correctly.

Now for the actual first thing, the ``"levels"`` field.
This decides how long the level should last, the base Jsons all end at level 25.0 (expect lunatic, which is 27.5). 
The value this holds is a double.

Next is the ``"spawns"``, this is an object which contains info on when entities should be spawned.
Within the spawns are arrays that have ``"1.5": []`` or ``"3.0": []``, the ``"1.5"`` part is what level those entities spawn at,
and the objects inside is the enemy info.

The spawn objects are just ``{}`` with an ``"id"`` value inside, and any extra fields that can be found in the documentation file.