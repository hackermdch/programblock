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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class ProgramUtils {
    public static final UUID DUMMY_UUID = Util.DUMMY_UUID;
    static Map<String, Object> global;

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

    public static Map<String, Object> global() {
        return global;
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
        callAsync(pos, getOverworld(), callback, null);
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
        return new ServerWorld(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.OVERWORLD)));
    }

    public static ServerWorld getNether() {
        return new ServerWorld(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.THE_NETHER)));
    }

    public static ServerWorld getTheEnd() {
        return new ServerWorld(Objects.requireNonNull(Hacker.server.getWorld(net.minecraft.world.World.THE_END)));
    }

    public static ServerWorld[] getOtherWorlds() {
        return getOthers();
    }

    public static ServerWorld[] getWorlds() {
        List<ServerWorld> a = new ArrayList<>();
        a.add(getOverworld());
        a.add(getNether());
        a.add(getTheEnd());
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

    public static void print(TextComponent text) {
        print(text != null ? text.getTarget().getString() : "null");
    }

    public static TextComponent from(String str) {
        return new TextComponent(new StringTextComponent(str));
    }

    public static TextComponent trans(String str, Object... args) {
        return new TextComponent(new TranslationTextComponent(str, args));
    }

    public static TextFormatting formatting(String name) {
        TextFormatting f = TextFormatting.getValueByName(name);
        return f != null ? f : TextFormatting.WHITE;
    }

    public static <T> T[] arrayOf(T... args) {
        return args;
    }

    public static int[] iArray(int... args) {
        return args;
    }

    public static byte[] bArray(byte... args) {
        return args;
    }

    public static char[] cArray(char... args) {
        return args;
    }

    public static float[] fArray(float... args) {
        return args;
    }

    public static double[] dArray(double... args) {
        return args;
    }

    public static short[] uiArray(short... args) {
        return args;
    }

    public static long[] lArray(long... args) {
        return args;
    }

    public static String dateTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static BlockPos pos(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }

    public static BlockState getBlockState(String id) {
        Block b = getBlock(id);
        return b.getDefaultState();
    }

    public static BlockState getBlockState(int id) {
        Block b = getBlock(id);
        return b.getDefaultState();
    }

    public static List<BlockState> getBlockStates(int... ids) {
        List<Block> l = getBlocks(ids);
        List<BlockState> l2 = new ArrayList<>();
        l.forEach((e) -> l2.add(e.getDefaultState()));
        return l2;
    }

    public static List<BlockState> getBlockStates() {
        List<Block> l = getBlocks();
        List<BlockState> l2 = new ArrayList<>();
        l.forEach((e) -> l2.add(e.getDefaultState()));
        return l2;
    }

    public static List<BlockState> getBlockStates(String... ids) {
        List<Block> l = getBlocks(ids);
        List<BlockState> l2 = new ArrayList<>();
        l.forEach((e) -> l2.add(e.getDefaultState()));
        return l2;
    }

    public static Block getBlock(String id) {
        net.minecraft.block.Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return b != null ? new Block(b) : new Block(Registry.BLOCK.getByValue(0));
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
            l.add(getBlock(s));
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
            l.add(getItem(s));
        return l;
    }

    public static List<Item> getItems() {
        List<Item> l = new ArrayList<>();
        ForgeRegistries.ITEMS.getValues().forEach((it) -> l.add(new Item(it)));
        return l;
    }

    public static Item getItem(String id) {
        net.minecraft.item.Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        return i != null ? new Item(i) : new Item(Registry.ITEM.getByValue(0));
    }

    private static double lengthSq(double x, double y, double z) {
        return x * x + y * y + z * z;
    }

    public static boolean setBlock(BlockPos pos, BlockState state) {
        return setBlock(getOverworld(), pos, state);
    }

    public static boolean setBlock(BlockPos pos, Block block) {
        return setBlock(getOverworld(), pos, block.getDefaultState());
    }

    public static boolean setBlock(World world, BlockPos pos, BlockState state) {
        return world.setBlockState(pos, state);
    }

    public static int color(int r, int g, int b) {
        return b + 256 * g + 65536 * r;
    }

    public static <T extends Entity> EntityType<T> getEntityType(String id) {
        net.minecraft.entity.EntityType<?> t = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
        return t != null ? new EntityType<T>(t) : new EntityType<T>(net.minecraft.entity.EntityType.PIG);
    }

    public static Entity createEntity(String id, World world) throws InstantiationException, IllegalAccessException {
        Entity e = getEntityType(id).create(world);
        world.addEntity(e);
        return e;
    }

    public static int makeSphereC(BlockPos pos, BlockState block, double radiusX, double radiusY, double radiusZ) {
        return makeSphereC(pos, block, radiusX, radiusY, radiusZ, true);
    }

    public static void makeSphere(BlockPos pos, BlockState block, double radiusX, double radiusY, double radiusZ) {
        makeSphere(pos, block, radiusX, radiusY, radiusZ, true);
    }

    public static int makeSphereC(BlockPos pos, BlockState block, double radiusX, double radiusY, double radiusZ, boolean filled) {
        int affected = 0;
        radiusX += 0.5D;
        radiusY += 0.5D;
        radiusZ += 0.5D;
        double invRadiusX = 1.0D / radiusX;
        double invRadiusY = 1.0D / radiusY;
        double invRadiusZ = 1.0D / radiusZ;
        int ceilRadiusX = (int) Math.ceil(radiusX);
        int ceilRadiusY = (int) Math.ceil(radiusY);
        int ceilRadiusZ = (int) Math.ceil(radiusZ);
        double nextXn = 0.0D;
        int x;
        l1:
        for (x = 0; x <= ceilRadiusX; x++) {
            double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0.0D;
            int y;
            l2:
            for (y = 0; y <= ceilRadiusY; y++) {
                double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0.0D;
                for (int z = 0; z <= ceilRadiusZ; z++) {
                    double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;
                    double distanceSq = lengthSq(xn, yn, zn);
                    if (distanceSq > 1.0D) {
                        if (z == 0) {
                            if (y == 0)
                                break l1;
                            break l2;
                        }
                        break;
                    }
                    if (filled ||
                            lengthSq(nextXn, yn, zn) > 1.0D || lengthSq(xn, nextYn, zn) > 1.0D || lengthSq(xn, yn, nextZn) > 1.0D) {
                        if (setBlock(pos.add(x, y, z), block))
                            affected++;
                        if (setBlock(pos.add(-x, y, z), block))
                            affected++;
                        if (setBlock(pos.add(x, -y, z), block))
                            affected++;
                        if (setBlock(pos.add(x, y, -z), block))
                            affected++;
                        if (setBlock(pos.add(-x, -y, z), block))
                            affected++;
                        if (setBlock(pos.add(x, -y, -z), block))
                            affected++;
                        if (setBlock(pos.add(-x, y, -z), block))
                            affected++;
                        if (setBlock(pos.add(-x, -y, -z), block))
                            affected++;
                    }
                }
            }
        }
        return affected;
    }

    public static void makeSphere(BlockPos pos, BlockState block, double radiusX, double radiusY, double radiusZ, boolean filled) {
        radiusX += 0.5D;
        radiusY += 0.5D;
        radiusZ += 0.5D;
        double invRadiusX = 1.0D / radiusX;
        double invRadiusY = 1.0D / radiusY;
        double invRadiusZ = 1.0D / radiusZ;
        int ceilRadiusX = (int) Math.ceil(radiusX);
        int ceilRadiusY = (int) Math.ceil(radiusY);
        int ceilRadiusZ = (int) Math.ceil(radiusZ);
        double nextXn = 0.0D;
        int x;
        l1:
        for (x = 0; x <= ceilRadiusX; x++) {
            double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0.0D;
            int y;
            l2:
            for (y = 0; y <= ceilRadiusY; y++) {
                double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0.0D;
                for (int z = 0; z <= ceilRadiusZ; z++) {
                    double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;
                    double distanceSq = lengthSq(xn, yn, zn);
                    if (distanceSq > 1.0D) {
                        if (z == 0) {
                            if (y == 0)
                                break l1;
                            break l2;
                        }
                        break;
                    }
                    if (filled ||
                            lengthSq(nextXn, yn, zn) > 1.0D || lengthSq(xn, nextYn, zn) > 1.0D || lengthSq(xn, yn, nextZn) > 1.0D) {
                        setBlock(pos.add(x, y, z), block);
                        setBlock(pos.add(-x, y, z), block);
                        setBlock(pos.add(x, -y, z), block);
                        setBlock(pos.add(x, y, -z), block);
                        setBlock(pos.add(-x, -y, z), block);
                        setBlock(pos.add(x, -y, -z), block);
                        setBlock(pos.add(-x, y, -z), block);
                        setBlock(pos.add(-x, -y, -z), block);
                    }
                }
            }
        }
    }
}
