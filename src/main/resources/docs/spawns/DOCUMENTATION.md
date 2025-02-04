# Documentation

### Json Structure

```json
{
  "levels": 25.0,
  "spawns": {
    "1.5": [
      {ENEMY OBJECT}
    ]
  }
}
```

``"levels"``: Determines how long the difficulty will last.

``"spawns"``: Contains all the spawn info for the entities, and when they should spawn.
The ``"1.5"`` is at which level the entities will be spawned, levels go up in increments of 0.5.
This means levels go 0, 0.5, 1, 1.5, etc. Inside can be any amount of enemy objects. 

### Enemy Object

```json
{
  "clear": true,
  "revive": true,
  "id": "TestEnemy.json",
  "amount": 2,
  "positions": {
    "1": {
      "x": 30,
      "y": 30
    },
    "2": {
      "x": 105,
      "y": 6435
    }
  }
}
```

``"clear"``: Clears all non-player entities currently on screen. Optional

``"revive"``: Allows players who are dead and are allowed to revive be revived. Optional

``"id"``: The ID of the enemy to be spawn, for a list look in the IDs file.

``"amount"``: The amount of the enemy to spawn. Optional

``"positions"``: An object which decides where entities spawn, the ``"1"`` and ``"2"`` are for if there are more than one entity spawned of the same ID.
``"1"`` is the first one spawned, ``"2"`` is the second, and so on. The ``"x"`` and ``"y"`` set where the entity is spawned at on the screen.
The base width (x) 940 and height (y) is 705, the calculation for height is ``WIDTH / 12 * 9``. Optional