package com.moroccancraft;

import com.moroccancraft.item.ModCreativeModeTab;
import com.moroccancraft.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoroccanCraft.MOD_ID)
public class MoroccanCraft {
    public static final String MOD_ID = "moroccancraft";

    public MoroccanCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeModeTab.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
