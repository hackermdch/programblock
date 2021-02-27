package com.hacker.programblock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.OperatorOnlyItem;
import net.minecraft.item.Rarity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("all")
@Mod(Hacker.MOD_ID)
public class Hacker {
    public final static String MOD_ID = "programblock";
    private static Rarity gold = Rarity.create("program_block", TextFormatting.GOLD);
    public static MinecraftServer server;
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    public static final RegistryObject<Block> programblock = BLOCKS.register("program_block", ProgramBlock::new);
    public static final RegistryObject<Item> programblock_item = ITEMS.register("program_block", () -> new OperatorOnlyItem(programblock.get(), new Item.Properties().rarity(gold)));
    public static final RegistryObject<TileEntityType<ProgramBlockTileEntity>> programblock_tile = TILE_ENTITY_TYPES.register("program_block", () -> TileEntityType.Builder.create(ProgramBlockTileEntity::new).build(null));

    public Hacker() {
        MinecraftForge.EVENT_BUS.register(new Object() {
            @SubscribeEvent
            public void onServerStarted(FMLServerStartedEvent event) {
                server = event.getServer();
            }

            @SubscribeEvent
            public void onServerStopping(FMLServerStoppingEvent event) {
                ProgramBlockTileEntity.removeAllThread();
            }

            @SubscribeEvent
            public void onServerStoped(FMLServerStoppedEvent event) {
                server = null;
            }
        });
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().register(new Object() {
            @SubscribeEvent
            public void onCommonSetup(FMLCommonSetupEvent event) {
                event.enqueueWork(Networking::registerMessage);
            }
        });
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        Compiler.getClassLoader();
    }
}
