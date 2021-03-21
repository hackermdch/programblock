package com.hacker.programblock.mixin;

import com.hacker.programblock.Hacker;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(BlockTagsProvider.class)
public abstract class BlockTagsProviderMixin extends TagsProvider<Block> {
    protected BlockTagsProviderMixin(DataGenerator generatorIn, Registry<Block> registryIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, registryIn, modId, existingFileHelper);
    }

    @Inject(method = "registerTags", at = @At("RETURN"))
    public void inj(CallbackInfo ci) {
        getOrCreateBuilder(BlockTags.DRAGON_IMMUNE).add(Hacker.programblock.get());
        getOrCreateBuilder(BlockTags.WITHER_IMMUNE).add(Hacker.programblock.get());
        getOrCreateBuilder(BlockTags.DRAGON_IMMUNE).add(Hacker.eventblock.get());
        getOrCreateBuilder(BlockTags.WITHER_IMMUNE).add(Hacker.eventblock.get());
    }
}
