package com.moroccancraft.item;

import com.moroccancraft.MoroccanCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoroccanCraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOROCCAN_TAB = CREATIVE_MODE_TABS.register("moroccan_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.DJELLABA_ROBE.get()))
                    .title(Component.translatable("creativetab.moroccancraft.moroccan_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.MOROCCAN_FABRIC.get());
                        output.accept(ModItems.MINT_LEAVES.get());
                        output.accept(ModItems.DJELLABA_HOOD.get());
                        output.accept(ModItems.DJELLABA_ROBE.get());
                        output.accept(ModItems.DJELLABA_PANTS.get());
                        output.accept(ModItems.BABOUCHE_SLIPPERS.get());
                        output.accept(ModItems.TAGINE.get());
                        output.accept(ModItems.COUSCOUS.get());
                        output.accept(ModItems.HARIRA.get());
                        output.accept(ModItems.CHICKEN_PASTILLA.get());
                        output.accept(ModItems.MOROCCAN_MINT_TEA.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
