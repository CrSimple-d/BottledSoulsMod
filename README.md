![Logo](https://cdn.modrinth.com/data/3CsYXFPB/images/0e4ba336e3f44cad2db46ec1a1d4fdccddca43e9.png)

# Bottled Souls

### Core Feature

**With the Soul Bottle, you can:**

- Capture the soul of almost any mob
- Store it inside the bottle
- Release the captured soul at any location you choose
---
### Configuration

The mod includes a flexible JSON configuration system:
```json
{
  "isSoulBottleUnbreakable": false,
  "soulBottleDurability": 5,
  "mobBlackList": {
    "enable": true,
    "collection": [
      "minecraft:warden",
      "minecraft:wither"
    ]
  },
  "mobWhiteList": {
    "enable": false,
    "collection": [
      "minecraft:creeper"
    ]
  }
}
```
---
### Mob Tag

**For modpack and plugin developers:**

- Added black_list tag support
- Works similarly to the blacklist system in the config
- Allows flexible integration and datapack customization

---

### Permissions & Commands

**The mod includes compatibility with LuckPerms:** 

Command: `/bottledsouls reload`, **reloads the configuration file**

Requirements:
OP Level 4, or LuckPerms permission: `bottled_souls.config.reload`
