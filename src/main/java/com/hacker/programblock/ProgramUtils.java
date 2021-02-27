package com.hacker.programblock;

import com.hacker.programblock.proxy.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class ProgramUtils {
    private static final ServerWorld overworld;
    private static final ServerWorld nether;
    private static final ServerWorld the_end;
    public static final UUID DUMMY_UUID = Util.DUMMY_UUID;

    static {
        overworld = new ServerWorld(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.OVERWORLD)));
        nether = new ServerWorld(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.THE_NETHER)));
        the_end = new ServerWorld(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.THE_END)));
    }

    private static ServerWorld[] getOthers() {
        Iterable<net.minecraft.world.server.ServerWorld> worlds = Hacker.server.getWorlds();
        List<World> a = new ArrayList<>();
        for (net.minecraft.world.server.ServerWorld w : worlds) {
            if (w.getDimensionKey() != net.minecraft.world.World.OVERWORLD &&
                    w.getDimensionKey() != net.minecraft.world.World.THE_NETHER &&
                    w.getDimensionKey() != net.minecraft.world.World.THE_END)
                a.add(new World(w));
        }
        return (ServerWorld[]) a.toArray();
    }

    public static boolean checkServerNonNull() {
        return Hacker.server != null;
    }

    public static void callAsync(BlockPos pos, World world, Consumer<Object> callback, Map<String, Object> args) {
        TileEntity e = world.getTileEntity(pos);
        if (e instanceof ProgramBlockTileEntity) {
            ProgramBlockTileEntity pb = (ProgramBlockTileEntity) e;
            pb.call(false, args, callback);
        }
    }

    public static void callAsync(BlockPos pos, Consumer<Object> callback) {
        callAsync(pos, overworld, callback, null);
    }

    public static void callAsync(int x, int y, int z, Consumer<Object> callback) {
        callAsync(new BlockPos(x, y, z), callback);
    }

    public static Object call(BlockPos pos, World world, Map<String, Object> args) {
        TileEntity e = world.getTileEntity(pos);
        if (e instanceof ProgramBlockTileEntity) {
            ProgramBlockTileEntity pb = (ProgramBlockTileEntity) e;
            return pb.call(true, args);
        }
        return null;
    }

    public static Object call(BlockPos pos, World world) {
        return call(pos, world, null);
    }

    public static Object call(BlockPos pos, Map<String, Object> args) {
        return call(pos, getOverworld(), args);
    }

    public static Object call(int x, int y, int z, Map<String, Object> args) {
        return call(new BlockPos(x, y, z), args);
    }

    public static Object call(BlockPos pos) {
        return call(pos, getOverworld(), null);
    }

    public static Object call(int x, int y, int z) {
        return call(new BlockPos(x, y, z));
    }

    public static ServerWorld getOverworld() {
        return overworld;
    }

    public static ServerWorld getNether() {
        return nether;
    }

    public static ServerWorld getTheEnd() {
        return the_end;
    }

    public static ServerWorld[] getOtherWorlds() {
        return getOthers();
    }

    public static ServerWorld[] getWorlds() {
        List<ServerWorld> a = new ArrayList<>();
        a.add(overworld);
        a.add(nether);
        a.add(the_end);
        a.addAll(Arrays.asList(getOthers()));
        return (ServerWorld[]) a.toArray();
    }

    public static List<Player> getPlayers() {
        List<Player> ps = new ArrayList<>();
        Hacker.server.getPlayerList().getPlayers().forEach((p) -> ps.add(new Player(p)));
        return ps;
    }

    public static Player getPlayer(String name) {
        for (ServerPlayerEntity p : Hacker.server.getPlayerList().getPlayers()) {
            if (p.getName().getString().equals(name))
                return new Player(p);
        }
        return null;
    }

    public static void print(String str) {
        getPlayers().forEach((p) -> p.sendMessage(from(TextFormatting.GOLD + "<").append(trans("block.programblock.program_block").mergeStyle(TextFormatting.GOLD)).append(from(TextFormatting.GOLD + ">" + TextFormatting.WHITE + " " + str)), DUMMY_UUID));
        System.out.println(str);
    }

    public static void print(Object obj) {
        print(obj != null ? obj.toString() : "null");
    }

    public static StringTextComponent from(String str) {
        return new StringTextComponent(str);
    }

    public static TranslationTextComponent trans(String str, Object... args) {
        return new TranslationTextComponent(str, args);
    }

    public static Block getBlock(String id) {
        net.minecraft.block.Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return b != null ? new Block(b) : null;
    }

    public static Block getBlock(int id) {
        net.minecraft.block.Block b = Registry.BLOCK.getByValue(id);
        return new Block(b);
    }

    public static List<Block> getBlocks(int... ids) {
        List<Block> l = new ArrayList<>();
        for (int i : ids)
            l.add(new Block(Registry.BLOCK.getByValue(i)));
        return l;
    }

    public static List<Block> getBlocks() {
        List<Block> l = new ArrayList<>();
        ForgeRegistries.BLOCKS.getValues().forEach((it) -> l.add(new Block(it)));
        return l;
    }

    public static List<Block> getBlocks(String... ids) {
        List<Block> l = new ArrayList<>();
        for (String s : ids)
            l.add(new Block(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(s))));
        return l;
    }

    public static Item getItem(int id) {
        net.minecraft.item.Item i = Registry.ITEM.getByValue(id);
        return new Item(i);
    }

    public static List<Item> getItems(int... ids) {
        List<Item> l = new ArrayList<>();
        for (int i : ids)
            l.add(new Item(Registry.ITEM.getByValue(i)));
        return l;
    }

    public static List<Item> getItems(String... ids) {
        List<Item> l = new ArrayList<>();
        for (String s : ids)
            l.add(new Item(ForgeRegistries.ITEMS.getValue(new ResourceLocation(s))));
        return l;
    }

    public static List<Item> getItems() {
        List<Item> l = new ArrayList<>();
        ForgeRegistries.ITEMS.getValues().forEach((it) -> l.add(new Item(it)));
        return l;
    }

    public static Item getItem(String id) {
        net.minecraft.item.Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        return i != null ? new Item(i) : null;
    }
}
