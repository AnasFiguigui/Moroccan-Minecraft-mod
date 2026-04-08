package com.moroccancraft.block;

import com.moroccancraft.MoroccanCraft;
import com.moroccancraft.block.custom.MoroccanFoodBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MoroccanCraft.MOD_ID);

    public static final RegistryObject<Block> TAGINE = BLOCKS.register("tagine",
            () -> new MoroccanFoodBlock(3, 0.4f,
                    BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f)
                            .sound(SoundType.WOOL).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> COUSCOUS = BLOCKS.register("couscous",
            () -> new MoroccanFoodBlock(2, 0.3f,
                    BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f)
                            .sound(SoundType.WOOL).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> CHICKEN_PASTILLA = BLOCKS.register("chicken_pastilla",
            () -> new MoroccanFoodBlock(3, 0.4f,
                    BlockBehaviour.Properties.of().forceSolidOn().strength(0.5f)
                            .sound(SoundType.WOOL).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
