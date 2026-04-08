package com.moroccancraft;

import com.moroccancraft.block.ModBlocks;
import com.moroccancraft.item.ModCreativeModeTab;
import com.moroccancraft.item.ModItems;
import com.moroccancraft.sound.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoroccanCraft.MOD_ID)
@SuppressWarnings("java:S1118")
public class MoroccanCraft {
    public static final String MOD_ID = "moroccancraft";

    public MoroccanCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModSounds.register(modEventBus);
        ModCreativeModeTab.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
