# MoroccanCraft - Minecraft Forge Mod

A Moroccan-themed Minecraft mod for **Forge 1.20.1** that adds traditional Moroccan armor, food, mint tea, and music discs.

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Forge](https://img.shields.io/badge/Forge-47.3.0-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

## Features

### Djellaba Armor Set (Diamond-tier)
Full 4-piece armor set crafted from **Moroccan Fabric** — a custom ingredient made from wool, string, and red dye.

| Piece | Slot | Protection | Durability |
|-------|------|------------|------------|
| Djellaba Hood | Head | 3 | 363 |
| Djellaba Robe | Chest | 8 | 528 |
| Djellaba Pants | Legs | 6 | 495 |
| Babouche Slippers | Feet | 3 | 429 |

**Armor toughness:** 2.0 — **Repair ingredient:** Moroccan Fabric

### Placeable Food (Cake-style)
Place on any surface, then right-click to eat. Each has **4 servings** before the block disappears.

| Food | Nutrition/bite | Total | Recipe |
|------|---------------|-------|--------|
| Tagine | 3 | 12 | Bowl + Cooked Chicken + Carrot + Potato |
| Couscous | 2 | 8 | 3 Wheat + Bowl |
| Chicken Pastilla | 3 | 12 | Cooked Chicken + Egg + Sugar + Wheat |

### Handheld Food & Drink
| Item | Nutrition | Special Effect |
|------|-----------|----------------|
| Harira | 7 | Returns bowl |
| Moroccan Mint Tea | 2 | Speed I + Regeneration I (30s), returns glass bottle |

### Music Discs (5 Moroccan genres)
Play in a jukebox. Each disc features a different traditional Moroccan music genre.

| Disc | Genre |
|------|-------|
| Gnawa | Traditional spiritual music |
| Andalusi | Classical Andalusian music |
| Chaabi | Popular folk music |
| Rai | North African genre |
| Malhoun | Traditional poetry-song |

### Crafting Ingredients
| Item | Recipe |
|------|--------|
| Moroccan Fabric (×4) | 4 White Wool + 4 String + 1 Red Dye (shaped) |
| Mint Leaves (×2) | 2 Ferns (shapeless) |

## Installation

### Play with the mod
1. Install [Minecraft Forge 1.20.1](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)
2. Download `moroccancraft-1.0.0.jar` from [Releases](https://github.com/AnasFiguigui/Moroccan-Minecraft-mod/releases)
3. Place the JAR in `%APPDATA%\.minecraft\mods\`
4. Launch Minecraft with the Forge 1.20.1 profile

## Building from Source

### Prerequisites
- **Java JDK 17+** (tested with OpenJDK 25)
- **Git**

### Build Steps

```bash
# Clone the repository
git clone https://github.com/AnasFiguigui/Moroccan-Minecraft-mod.git
cd Moroccan-Minecraft-mod

# Build the mod
./gradlew build

# The JAR will be at:
# build/libs/moroccancraft-1.0.0.jar
```

### Development

```bash
# Run Minecraft client with the mod (dev environment)
./gradlew runClient

# Run dedicated server with the mod
./gradlew runServer

# Clean build artifacts
./gradlew clean

# Full clean rebuild
./gradlew clean build
```

### IDE Setup

**IntelliJ IDEA:**
```bash
./gradlew genIntellijRuns
```
Then open the project folder in IntelliJ and select the `runClient` configuration.

**Eclipse:**
```bash
./gradlew genEclipseRuns
```

## Project Structure

```
src/main/
├── java/com/moroccancraft/
│   ├── MoroccanCraft.java              # Main mod class
│   ├── block/
│   │   ├── ModBlocks.java              # Block registrations
│   │   └── custom/
│   │       └── MoroccanFoodBlock.java  # Placeable food block (cake-style)
│   ├── item/
│   │   ├── ModItems.java              # Item registrations
│   │   ├── ModArmorMaterials.java     # Djellaba armor material
│   │   ├── ModCreativeModeTab.java    # Creative inventory tab
│   │   └── custom/
│   │       └── MoroccanMintTeaItem.java  # Tea with potion effects
│   └── sound/
│       └── ModSounds.java             # Sound event registrations
└── resources/
    ├── META-INF/mods.toml             # Mod metadata
    ├── pack.mcmeta                    # Resource pack metadata
    ├── assets/moroccancraft/
    │   ├── blockstates/               # Block state definitions
    │   ├── lang/en_us.json            # English translations
    │   ├── models/block/              # Block models
    │   ├── models/item/               # Item models
    │   ├── sounds.json                # Sound definitions
    │   ├── sounds/                    # OGG audio files (placeholder)
    │   └── textures/                  # Item, block, and armor textures
    └── data/moroccancraft/
        ├── loot_tables/blocks/        # Block loot tables
        └── recipes/                   # Crafting recipes
```

## Adding Custom Music

The 5 music disc sound files are placeholders. To add your own music:

1. Convert your audio to **OGG Vorbis** format (use [Audacity](https://www.audacityteam.org/) or ffmpeg)
2. Replace files in `src/main/resources/assets/moroccancraft/sounds/`:
   - `disc_gnawa.ogg`
   - `disc_andalusi.ogg`
   - `disc_chaabi.ogg`
   - `disc_rai.ogg`
   - `disc_malhoun.ogg`
3. Rebuild: `./gradlew clean build`

## Replacing Placeholder Textures

All textures are programmatically generated placeholders. Replace them with pixel art:

- **Item textures** (16×16 PNG): `src/main/resources/assets/moroccancraft/textures/item/`
- **Block textures** (16×16 PNG): `src/main/resources/assets/moroccancraft/textures/block/`
- **Armor overlays** (64×32 PNG): `src/main/resources/assets/moroccancraft/textures/models/armor/`
  - `djellaba_layer_1.png` — head, chest, boots overlay
  - `djellaba_layer_2.png` — leggings overlay

## License

MIT

## Author

[AnasFiguigui](https://github.com/AnasFiguigui)
