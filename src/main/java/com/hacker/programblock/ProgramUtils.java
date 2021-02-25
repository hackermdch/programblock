package com.hacker.programblock;

import com.hacker.programblock.proxy.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ProgramUtils {
    private static final World overworld;
    private static final World nether;
    private static final World the_end;
    public static final UUID DUMMY_UUID = Util.DUMMY_UUID;

    static {
        overworld = new World(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.OVERWORLD)));
        nether = new World(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.THE_NETHER)));
        the_end = new World(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.THE_END)));
    }

    private static World[] getOthers() {
        Iterable<ServerWorld> worlds = Hacker.server.getWorlds();
        List<World> a = new ArrayList<>();
        for (ServerWorld w : worlds) {
            if (w.getDimensionKey() != net.minecraft.world.World.OVERWORLD &&
                    w.getDimensionKey() != net.minecraft.world.World.THE_NETHER &&
                    w.getDimensionKey() != net.minecraft.world.World.THE_END)
                a.add(new World(w));
        }
        return (World[]) a.toArray();
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

    public static World getOverworld() {
        return overworld;
    }

    public static World getNether() {
        return nether;
    }

    public static World getTheEnd() {
        return the_end;
    }

    public static World[] getOtherWorlds() {
        return getOthers();
    }

    public static World[] getWorlds() {
        List<World> a = new ArrayList<>();
        a.add(overworld);
        a.add(nether);
        a.add(the_end);
        a.addAll(Arrays.asList(getOthers()));
        return (World[]) a.toArray();
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
        getPlayers().forEach((p) -> p.sendMessage(from(TextFormatting.GOLD + "<" + trans("block.programblock.program_block").getString() + ">" + TextFormatting.WHITE + " " + str), DUMMY_UUID));
        System.out.println(str);
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

    public static Item getItem(String id) {
        net.minecraft.item.Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        return i != null ? new Item(i) : null;
    }
}
