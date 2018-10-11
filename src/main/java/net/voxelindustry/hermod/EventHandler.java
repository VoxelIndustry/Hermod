package net.voxelindustry.hermod;

@FunctionalInterface
public interface EventHandler<T extends HermodEvent>
{
    void handle(T event);
}