package com.hacker.programblock;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod("programblock")
public class Hacker {
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

    public Hacker() {
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarted);
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
