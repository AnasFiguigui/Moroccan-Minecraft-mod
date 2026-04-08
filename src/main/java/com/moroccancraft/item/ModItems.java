package com.moroccancraft.item;

import com.moroccancraft.MoroccanCraft;
import com.moroccancraft.block.ModBlocks;
import com.moroccancraft.item.custom.MoroccanMintTeaItem;
import com.moroccancraft.sound.ModSounds;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoroccanCraft.MOD_ID);

    // ===== Crafting Ingredients =====
    public static final RegistryObject<Item> MOROCCAN_FABRIC = ITEMS.register("moroccan_fabric",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MINT_LEAVES = ITEMS.register("mint_leaves",
            () -> new Item(new Item.Properties()));

    // ===== Djellaba Armor Set (Diamond-tier) =====
    public static final RegistryObject<Item> DJELLABA_HOOD = ITEMS.register("djellaba_hood",
            () -> new ArmorItem(ModArmorMaterials.DJELLABA, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryObject<Item> DJELLABA_ROBE = ITEMS.register("djellaba_robe",
            () -> new ArmorItem(ModArmorMaterials.DJELLABA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));

    public static final RegistryObject<Item> DJELLABA_PANTS = ITEMS.register("djellaba_pants",
            () -> new ArmorItem(ModArmorMaterials.DJELLABA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));

    public static final RegistryObject<Item> BABOUCHE_SLIPPERS = ITEMS.register("babouche_slippers",
            () -> new ArmorItem(ModArmorMaterials.DJELLABA, ArmorItem.Type.BOOTS,
                    new Item.Properties()));

    // ===== Moroccan Food (Placeable like cake) =====
    public static final RegistryObject<Item> TAGINE = ITEMS.register("tagine",
            () -> new BlockItem(ModBlocks.TAGINE.get(), new Item.Properties()));

    public static final RegistryObject<Item> COUSCOUS = ITEMS.register("couscous",
            () -> new BlockItem(ModBlocks.COUSCOUS.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHICKEN_PASTILLA = ITEMS.register("chicken_pastilla",
            () -> new BlockItem(ModBlocks.CHICKEN_PASTILLA.get(), new Item.Properties()));

    // ===== Moroccan Food (Handheld) =====
    public static final RegistryObject<Item> HARIRA = ITEMS.register("harira",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(7).saturationMod(0.7f).build())
                    .craftRemainder(Items.BOWL)));

    // ===== Moroccan Mint Tea =====
    public static final RegistryObject<Item> MOROCCAN_MINT_TEA = ITEMS.register("moroccan_mint_tea",
            () -> new MoroccanMintTeaItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).alwaysEat().build())
                    .stacksTo(16)));

    // ===== Music Discs (Moroccan genres) =====
    public static final RegistryObject<Item> MUSIC_DISC_GNAWA = ITEMS.register("music_disc_gnawa",
            () -> new RecordItem(1, ModSounds.DISC_GNAWA,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static final RegistryObject<Item> MUSIC_DISC_ANDALUSI = ITEMS.register("music_disc_andalusi",
            () -> new RecordItem(2, ModSounds.DISC_ANDALUSI,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static final RegistryObject<Item> MUSIC_DISC_CHAABI = ITEMS.register("music_disc_chaabi",
            () -> new RecordItem(3, ModSounds.DISC_CHAABI,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static final RegistryObject<Item> MUSIC_DISC_RAI = ITEMS.register("music_disc_rai",
            () -> new RecordItem(4, ModSounds.DISC_RAI,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static final RegistryObject<Item> MUSIC_DISC_MALHOUN = ITEMS.register("music_disc_malhoun",
            () -> new RecordItem(5, ModSounds.DISC_MALHOUN,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3600));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
