# Documentation

### Json Structure

```json
{
  "size": {
    "x": 16,
    "y": 16,
    "hitbox": {
      "x": 16,
      "y": 16
    }
  },
  "velocity": {
    "x": 8,
    "y": 6
  },
  "color": {
    "r": 0,
    "g": 255,
    "b": 0
  },
  "trail": {
    "color": {
      "r": 0,
      "g": 255,
      "b": 0
    },
    "size": {
      "x": 16,
      "y": 16
    },
    "life": 0.15
  },
  "damage": 35,
  "graze": 1,
  "shape": "cube",
  "spawnTime": 50
}
```

``"size"``: How large the entity will appear. "x" and "y" are how long and high the entity is visually in pixels.
The ``"hitbox"`` controls how large the hitbox is in pixels.

``"velocity"``: Controls how fast the enemy will go on the x and y respectively.

``"color"``: Controls the color of the entity.

``"damage"``: How much damage the entity deals as an int.

``"graze"``: How much graze is gained from the entity as an int.

``"shape"``: The shape of the entity.

``"spawnTime"``: the delay before the enemy becomes active.

### Trail Block

```json
{
  "trail": {
    "color": {
      "r": 0,
      "g": 255,
      "b": 0
    },
    "size": {
      "x": 16,
      "y": 16
    },
    "life": 0.15
  }
}
```

``"color"``: Controls the color of the trail.

``"size"``: How large the trail will appear. "x" and "y" are how long and high the trail is visually in pixels.

``"life"``: How quickly the trail disappears.