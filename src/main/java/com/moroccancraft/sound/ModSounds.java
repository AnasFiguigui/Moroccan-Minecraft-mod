package com.moroccancraft.sound;

import com.moroccancraft.MoroccanCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoroccanCraft.MOD_ID);

    public static final RegistryObject<SoundEvent> DISC_GNAWA = SOUND_EVENTS.register("disc_gnawa",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoroccanCraft.MOD_ID, "disc_gnawa")));

    public static final RegistryObject<SoundEvent> DISC_ANDALUSI = SOUND_EVENTS.register("disc_andalusi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoroccanCraft.MOD_ID, "disc_andalusi")));

    public static final RegistryObject<SoundEvent> DISC_CHAABI = SOUND_EVENTS.register("disc_chaabi",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoroccanCraft.MOD_ID, "disc_chaabi")));

    public static final RegistryObject<SoundEvent> DISC_RAI = SOUND_EVENTS.register("disc_rai",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoroccanCraft.MOD_ID, "disc_rai")));

    public static final RegistryObject<SoundEvent> DISC_MALHOUN = SOUND_EVENTS.register("disc_malhoun",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MoroccanCraft.MOD_ID, "disc_malhoun")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
