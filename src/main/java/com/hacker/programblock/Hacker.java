package com.hacker.programblock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.OperatorOnlyItem;
import net.minecraft.item.Rarity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("all")
@Mod(Hacker.MOD_ID)
public class Hacker {
    public final static String MOD_ID = "programblock";
    private static Rarity gold = Rarity.create("program_block", TextFormatting.GOLD);
    public static MinecraftServer server;
    static String SOURCE_CODE =
            "package com.hacker.dy;" +
                    "import org.apache.logging.log4j.LogManager;" +
                    "import net.minecraft.entity.Entity;" +
                    "class Test{" +
                    "public static void print(Entity e){" +
                    "LogManager.getLogger().info(e.getName());" +
                    "}" +
                    "}" +
                    ";";
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    public static final RegistryObject<Block> programblock = BLOCKS.register("program_block", ProgramBlock::new);
    public static final RegistryObject<Item> programblock_item = ITEMS.register("program_block", () -> new OperatorOnlyItem(programblock.get(), new Item.Properties().rarity(gold)));
    public static final RegistryObject<TileEntityType<ProgramBlockTileEntity>> programblock_tile = TILE_ENTITY_TYPES.register("program_block", () -> TileEntityType.Builder.create(ProgramBlockTileEntity::new).build(null));

    public Hacker() {
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarted);
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        try {
            Entity e = Minecraft.getInstance().player;
            Class<?> z = Compiler.compile("com.hacker.dy", "Test", SOURCE_CODE);
            z.getDeclaredMethod("print", Entity.class).invoke(null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onServerStarted(FMLServerStartedEvent event) {
        Hacker.server = event.getServer();
    }
}
