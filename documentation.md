# MoroccanCraft Developer Documentation

This guide explains how to extend the mod by adding new **armor sets**, **food items**, **music discs**, and **crafting ingredients**.

---

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Adding a New Armor Set](#adding-a-new-armor-set)
- [Adding Food Items](#adding-food-items)
- [Adding Music Discs](#adding-music-discs)
- [Adding Crafting Ingredients](#adding-crafting-ingredients)
- [Adding Textures](#adding-textures)
- [Adding Recipes](#adding-recipes)
- [Adding Translations](#adding-translations)
- [Building & Testing](#building--testing)

---

## Architecture Overview

The mod uses Forge's **DeferredRegister** pattern. All registrations happen in static initializer classes that are loaded during mod construction:

| Class | Registers |
|-------|-----------|
| `ModItems.java` | All items (ingredients, armor, food, discs) |
| `ModBlocks.java` | Placeable blocks (food blocks) |
| `ModSounds.java` | Sound events (for music discs) |
| `ModArmorMaterials.java` | Armor material definitions |
| `ModCreativeModeTab.java` | Creative mode tab contents |
| `MoroccanCraft.java` | Main mod class — wires everything together |

**Registration flow:**
1. `MoroccanCraft` static constructor calls `.register(modEventBus)` on each registry class
2. Forge processes `DeferredRegister` entries during mod loading
3. Items/blocks/sounds become available in-game

---

## Adding a New Armor Set

### Step 1: Define the Armor Material

In `src/main/java/com/moroccancraft/item/ModArmorMaterials.java`, add a new enum value:

```java
// Existing DJELLABA entry shown for reference:
DJELLABA("djellaba", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_LEATHER, 2.0F, 0.0F,
    () -> Ingredient.of(ModItems.MOROCCAN_FABRIC.get())),

// Add your new armor material:
KAFTAN("kaftan", 25, new int[]{2, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F,
    () -> Ingredient.of(Items.GOLD_INGOT));
```

**Parameters explained:**

| Parameter | Example | Description |
|-----------|---------|-------------|
| `name` | `"kaftan"` | Internal name (used for texture path) |
| `durabilityMultiplier` | `25` | Base durability (15=leather, 25=iron, 33=diamond, 37=netherite) |
| `protectionAmounts` | `{2,5,6,2}` | Protection for [boots, leggings, chestplate, helmet] |
| `enchantability` | `15` | Higher = better enchantments |
| `equipSound` | `SoundEvents.ARMOR_EQUIP_GOLD` | Sound when equipping |
| `toughness` | `1.0F` | Armor toughness (0=leather/iron, 2=diamond, 3=netherite) |
| `knockbackResistance` | `0.0F` | Knockback resistance (only netherite has 0.1) |
| `repairIngredient` | `() -> Ingredient.of(...)` | Anvil repair item |

### Step 2: Register the Armor Items

In `src/main/java/com/moroccancraft/item/ModItems.java`:

```java
// 4 armor pieces — one per slot
public static final RegistryObject<Item> KAFTAN_HOOD = ITEMS.register("kaftan_hood",
    () -> new ArmorItem(ModArmorMaterials.KAFTAN, ArmorItem.Type.HELMET,
        new Item.Properties()));

public static final RegistryObject<Item> KAFTAN_ROBE = ITEMS.register("kaftan_robe",
    () -> new ArmorItem(ModArmorMaterials.KAFTAN, ArmorItem.Type.CHESTPLATE,
        new Item.Properties()));

public static final RegistryObject<Item> KAFTAN_PANTS = ITEMS.register("kaftan_pants",
    () -> new ArmorItem(ModArmorMaterials.KAFTAN, ArmorItem.Type.LEGGINGS,
        new Item.Properties()));

public static final RegistryObject<Item> KAFTAN_SHOES = ITEMS.register("kaftan_shoes",
    () -> new ArmorItem(ModArmorMaterials.KAFTAN, ArmorItem.Type.BOOTS,
        new Item.Properties()));
```

### Step 3: Add to Creative Tab

In `src/main/java/com/moroccancraft/item/ModCreativeModeTab.java`, inside the `displayItems` lambda:

```java
output.accept(ModItems.KAFTAN_HOOD.get());
output.accept(ModItems.KAFTAN_ROBE.get());
output.accept(ModItems.KAFTAN_PANTS.get());
output.accept(ModItems.KAFTAN_SHOES.get());
```

### Step 4: Add Textures

Create two armor layer textures at:
- `textures/models/armor/kaftan_layer_1.png` (64×32 PNG) — head, chest, boots
- `textures/models/armor/kaftan_layer_2.png` (64×32 PNG) — leggings

The file name must match the `name` parameter in `ModArmorMaterials` (`kaftan`).

Create four item textures at (16×16 PNG each):
- `textures/item/kaftan_hood.png`
- `textures/item/kaftan_robe.png`
- `textures/item/kaftan_pants.png`
- `textures/item/kaftan_shoes.png`

### Step 5: Add Item Models

Create 4 files in `models/item/`, e.g. `kaftan_hood.json`:

```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "moroccancraft:item/kaftan_hood"
  }
}
```

Repeat for `kaftan_robe.json`, `kaftan_pants.json`, `kaftan_shoes.json`.

### Step 6: Add Recipes & Translations

See [Adding Recipes](#adding-recipes) and [Adding Translations](#adding-translations).

---

## Adding Food Items

There are two types of food: **handheld** (eaten from inventory) and **placeable** (placed as a block, cake-style).

### Handheld Food

In `ModItems.java`:

```java
public static final RegistryObject<Item> MSEMEN = ITEMS.register("msemen",
    () -> new Item(new Item.Properties()
        .food(new FoodProperties.Builder()
            .nutrition(5)        // hunger restored (half-drumsticks = nutrition/2)
            .saturationMod(0.6f) // saturation multiplier
            .build())));
```

**Optional food properties:**

```java
.fast()              // Eat faster (like dried kelp)
.alwaysEat()         // Eat even when full (like golden apple)
.meat()              // Wolves can eat it
.effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 1.0F)  // 100% chance
```

### Placeable Food (Block)

#### Step 1: Register the Block

In `src/main/java/com/moroccancraft/block/ModBlocks.java`:

```java
public static final RegistryObject<Block> MSEMEN_PLATE = BLOCKS.register("msemen_plate",
    () -> new MoroccanFoodBlock(
        4,      // nutrition per bite
        0.5f,   // saturation per bite
        BlockBehaviour.Properties.of()
            .forceSolidOn()
            .strength(0.5F)
            .sound(SoundType.WOOL)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)));
```

The `MoroccanFoodBlock` class provides 4 servings (bites 0–3) per placement.

#### Step 2: Register the BlockItem

In `ModItems.java`:

```java
public static final RegistryObject<Item> MSEMEN_PLATE = ITEMS.register("msemen_plate",
    () -> new BlockItem(ModBlocks.MSEMEN_PLATE.get(), new Item.Properties()));
```

#### Step 3: Add Blockstate, Block Model, and Block Textures

**Blockstate** — `blockstates/msemen_plate.json`:

```json
{
  "variants": {
    "bites=0": { "model": "moroccancraft:block/msemen_plate" },
    "bites=1": { "model": "moroccancraft:block/msemen_plate" },
    "bites=2": { "model": "moroccancraft:block/msemen_plate" },
    "bites=3": { "model": "moroccancraft:block/msemen_plate" }
  }
}
```

**Block model** — `models/block/msemen_plate.json`:

```json
{
  "textures": {
    "top": "moroccancraft:block/msemen_plate_top",
    "side": "moroccancraft:block/msemen_plate_side",
    "bottom": "moroccancraft:block/msemen_plate_bottom",
    "particle": "moroccancraft:block/msemen_plate_side"
  },
  "elements": [
    {
      "from": [1, 0, 1],
      "to": [15, 8, 15],
      "faces": {
        "down":  { "texture": "#bottom", "cullface": "down" },
        "up":    { "texture": "#top" },
        "north": { "texture": "#side" },
        "south": { "texture": "#side" },
        "west":  { "texture": "#side" },
        "east":  { "texture": "#side" }
      }
    }
  ]
}
```

**Block textures** (16×16 PNG each):
- `textures/block/msemen_plate_top.png`
- `textures/block/msemen_plate_side.png`
- `textures/block/msemen_plate_bottom.png`

**Item model** — `models/item/msemen_plate.json`:

```json
{
  "parent": "moroccancraft:block/msemen_plate"
}
```

#### Step 4: Add Loot Table

Create `data/moroccancraft/loot_tables/blocks/msemen_plate.json`:

```json
{
  "type": "minecraft:block",
  "pools": []
}
```

Empty pools means the block drops nothing when broken (like cake). If you want it to drop the item, use:

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "moroccancraft:msemen_plate"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:block_state_property",
          "block": "moroccancraft:msemen_plate",
          "properties": { "bites": "0" }
        }
      ]
    }
  ]
}
```

---

## Adding Music Discs

### Step 1: Register the Sound Event

In `src/main/java/com/moroccancraft/sound/ModSounds.java`:

```java
public static final RegistryObject<SoundEvent> DISC_DAKKA = SOUND_EVENTS.register("disc_dakka",
    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoroccanCraft.MOD_ID, "disc_dakka")));
```

### Step 2: Register the Disc Item

In `ModItems.java`:

```java
public static final RegistryObject<Item> MUSIC_DISC_DAKKA = ITEMS.register("music_disc_dakka",
    () -> new RecordItem(15, ModSounds.DISC_DAKKA,    // 15 = comparator output (1-15)
        new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));  // 3600 ticks = 3 minutes
```

**Parameters:**
- First `int`: Comparator signal strength when in jukebox (1–15, use unique values)
- `SoundEvent`: The registered sound event
- `Properties`: Always `stacksTo(1).rarity(Rarity.RARE)` for discs
- Last `int`: Song length in ticks (20 ticks = 1 second)

### Step 3: Add to Creative Tab

In `ModCreativeModeTab.java`:

```java
output.accept(ModItems.MUSIC_DISC_DAKKA.get());
```

### Step 4: Add the Sound File

Place your audio at:
```
src/main/resources/assets/moroccancraft/sounds/disc_dakka.ogg
```

Must be **OGG Vorbis** format. Convert with:
```bash
ffmpeg -i input.mp3 -c:a libvorbis -q:a 4 disc_dakka.ogg
```

### Step 5: Register in sounds.json

In `src/main/resources/assets/moroccancraft/sounds.json`, add an entry:

```json
{
  "disc_gnawa": { ... },
  "disc_dakka": {
    "sounds": [
      {
        "name": "moroccancraft:disc_dakka",
        "stream": true
      }
    ]
  }
}
```

`"stream": true` is important for music — it streams from disk instead of loading into memory.

### Step 6: Add Item Model & Texture

**Item model** — `models/item/music_disc_dakka.json`:

```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "moroccancraft:item/music_disc_dakka"
  }
}
```

**Texture** — `textures/item/music_disc_dakka.png` (16×16 PNG)

### Step 7: Add Translation

In `lang/en_us.json`:

```json
"item.moroccancraft.music_disc_dakka": "Music Disc",
"item.moroccancraft.music_disc_dakka.desc": "Dakka Marrakchia - Traditional Percussion"
```

The `.desc` key is what shows in the disc's tooltip.

---

## Adding Crafting Ingredients

Simple items with no special behavior:

```java
// In ModItems.java
public static final RegistryObject<Item> SAFFRON = ITEMS.register("saffron",
    () -> new Item(new Item.Properties()));
```

Add to creative tab, create item model, texture, and translation.

---

## Adding Textures

All textures are standard **PNG** files.

| Type | Size | Location |
|------|------|----------|
| Item | 16×16 | `assets/moroccancraft/textures/item/` |
| Block (top/side/bottom) | 16×16 | `assets/moroccancraft/textures/block/` |
| Armor layer 1 (head, chest, boots) | 64×32 | `assets/moroccancraft/textures/models/armor/` |
| Armor layer 2 (leggings) | 64×32 | `assets/moroccancraft/textures/models/armor/` |

For armor textures, the file name must match the material name:
`{material_name}_layer_1.png` and `{material_name}_layer_2.png`

Use the [Minecraft armor template](https://minecraft.wiki/w/Armor#Textures) as a reference for the 64×32 layout.

---

## Adding Recipes

Recipe files go in `src/main/resources/data/moroccancraft/recipes/`.

### Shaped Crafting

```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "FFF",
    "FCF",
    "FFF"
  ],
  "key": {
    "F": { "item": "moroccancraft:moroccan_fabric" },
    "C": { "item": "minecraft:chest" }
  },
  "result": {
    "item": "moroccancraft:kaftan_robe",
    "count": 1
  }
}
```

### Shapeless Crafting

```json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    { "item": "minecraft:bowl" },
    { "item": "moroccancraft:saffron" },
    { "item": "minecraft:cooked_beef" }
  ],
  "result": {
    "item": "moroccancraft:msemen",
    "count": 2
  }
}
```

### Smelting

```json
{
  "type": "minecraft:smelting",
  "ingredient": { "item": "moroccancraft:raw_saffron" },
  "result": "moroccancraft:saffron",
  "experience": 0.1,
  "cookingtime": 200
}
```

---

## Adding Translations

Edit `src/main/resources/assets/moroccancraft/lang/en_us.json`:

```json
{
  "item.moroccancraft.item_name": "Display Name",
  "block.moroccancraft.block_name": "Block Display Name",
  "item.moroccancraft.music_disc_x": "Music Disc",
  "item.moroccancraft.music_disc_x.desc": "Artist - Song Title",
  "itemGroup.moroccan_tab": "Moroccan Tab"
}
```

**Key format:**
- Items: `item.moroccancraft.{registry_name}`
- Blocks: `block.moroccancraft.{registry_name}`
- Music disc descriptions: `item.moroccancraft.{disc_name}.desc`
- Creative tabs: `itemGroup.{tab_name}`

---

## Building & Testing

```bash
# Build the mod JAR
./gradlew build
# Output: build/libs/moroccancraft-1.0.0.jar

# Test in dev environment
./gradlew runClient

# Clean and rebuild
./gradlew clean build
```

### Checklist for New Content

When adding any new item/block/disc, make sure you have:

- [ ] Java registration (`ModItems`, `ModBlocks`, `ModSounds` as needed)
- [ ] Added to creative tab in `ModCreativeModeTab.java`
- [ ] Item model JSON in `models/item/`
- [ ] Item texture PNG in `textures/item/`
- [ ] Block model + blockstate + block textures (if placeable)
- [ ] Loot table (if block)
- [ ] Recipe JSON in `recipes/`
- [ ] Translation in `lang/en_us.json`
- [ ] Sound entry in `sounds.json` + OGG file (if music disc)
- [ ] Armor layer textures (if armor)
