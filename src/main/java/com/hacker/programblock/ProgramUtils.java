package com.hacker.programblock;

import com.hacker.programblock.proxy.Player;
import com.hacker.programblock.proxy.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class ProgramUtils {
    private static final World overworld;
    private static final World nether;
    private static final World the_end;

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
}
