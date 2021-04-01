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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

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
    public static final RegistryObject<Block> eventblock = BLOCKS.register("event_block", EventBlock::new);
    public static final RegistryObject<Item> programblock_item = ITEMS.register("program_block", () -> new OperatorOnlyItem(programblock.get(), new Item.Properties().rarity(gold)));
    public static final RegistryObject<Item> eventblock_item = ITEMS.register("event_block", () -> new OperatorOnlyItem(eventblock.get(), new Item.Properties().rarity(gold)));
    public static final RegistryObject<TileEntityType<ProgramBlockTileEntity>> programblock_tile = TILE_ENTITY_TYPES.register("program_block", () -> TileEntityType.Builder.create(ProgramBlockTileEntity::new).build(null));
    public static final RegistryObject<TileEntityType<EventBlockTileEntity>> eventblock_tile = TILE_ENTITY_TYPES.register("event_block", () -> TileEntityType.Builder.create(EventBlockTileEntity::new).build(null));

    static {
        try {
            String[] libs = {"native.dll", "native32.dll", "native.so"};
            for (int i = 0; i < libs.length; i++) {
                InputStream in = Hacker.class.getClassLoader().getResourceAsStream("libs/" + libs[i]);
                FileOutputStream o = new FileOutputStream(System.getProperty("java.io.tmpdir") + "/" + libs[i]);
                int len;
                byte[] b = new byte[1024];
                while ((len = in.read(b)) > 0) {
                    o.write(b, 0, len);
                }
                o.close();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String bit = System.getProperty("sun.arch.data.model");
        System.setProperty("java.library.path", System.getProperty("java.library.path") + ";" + System.getProperty("java.io.tmpdir"));
        try {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bit.equals("64"))
            System.loadLibrary("native");
        else
            System.loadLibrary("native32");
    }

    public Hacker() {
        MinecraftForge.EVENT_BUS.register(new Object() {
            @SubscribeEvent
            public void onServerStarted(FMLServerStartedEvent event) {
                server = event.getServer();
                ProgramUtils.global = new HashMap<>();
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
