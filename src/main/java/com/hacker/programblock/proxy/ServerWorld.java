package com.hacker.programblock.proxy;

import net.minecraft.entity.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class ServerWorld extends World {
    private final net.minecraft.world.server.ServerWorld target;

    public ServerWorld(@Nonnull net.minecraft.world.server.ServerWorld target) {
        super(target);
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.world.server.ServerWorld getTarget() {
        return target;
    }

    public List<Entity> getEntities(@Nullable EntityType<?> entityTypeIn, Predicate<? super Entity> predicateIn) {
        List<Entity> es = new ArrayList<>();
        List<net.minecraft.entity.Entity> l = target.getEntities(entityTypeIn, (e) -> predicateIn.test(new Entity(e)));
        l.forEach((e) -> es.add(new Entity(e)));
        return es;
    }

    public Stream<Entity> getEntities() {
        List<Entity> es = new ArrayList<>();
        Stream<net.minecraft.entity.Entity> s = target.getEntities();
        s.forEach((e) -> es.add(new Entity(e)));
        return es.stream();
    }
}
