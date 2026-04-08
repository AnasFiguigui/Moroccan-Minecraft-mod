package com.moroccancraft.item;

import com.moroccancraft.MoroccanCraft;
import com.moroccancraft.item.custom.MoroccanMintTeaItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    // ===== Moroccan Food =====
    public static final RegistryObject<Item> TAGINE = ITEMS.register("tagine",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(10).saturationMod(0.8f).build())));

    public static final RegistryObject<Item> COUSCOUS = ITEMS.register("couscous",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationMod(0.6f).build())));

    public static final RegistryObject<Item> HARIRA = ITEMS.register("harira",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(7).saturationMod(0.7f).build())
                    .craftRemainder(Items.BOWL)));

    public static final RegistryObject<Item> CHICKEN_PASTILLA = ITEMS.register("chicken_pastilla",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(9).saturationMod(0.8f).build())));

    // ===== Moroccan Mint Tea =====
    public static final RegistryObject<Item> MOROCCAN_MINT_TEA = ITEMS.register("moroccan_mint_tea",
            () -> new MoroccanMintTeaItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).alwaysEat().build())
                    .stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
