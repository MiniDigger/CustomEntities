package me.minidigger.customentities.api.world;

import com.artemis.WorldConfigurationBuilder;
import com.artemis.injection.*;
import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.systems.SpawnSystem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the {@link EntityWorld}s for all plugins
 */
public class WorldHandler {

    private static WorldHandler instance;

    private CustomEntities plugin;

    private Map<String, EntityWorld> worldMap;
    private Map<String, WorldConfigurationBuilder> worldConfigurationBuilderMap;

    private long lastTick = System.currentTimeMillis();

    private WorldHandler(CustomEntities customEntities) {
        this.plugin = customEntities;

        this.worldMap = new HashMap<>();
        this.worldConfigurationBuilderMap = new HashMap<>();

        // tick loop
        Bukkit.getScheduler().runTaskTimer(customEntities, this::tick, 1, 1);
    }

    public static WorldHandler initialize(CustomEntities customEntities) {
        if (instance != null)
            throw new RuntimeException("WorldHandler can only be instantiated once!");
        return instance = new WorldHandler(customEntities);
    }

    public static WorldHandler getInstance() {
        return instance;
    }

    public CustomEntities getPlugin() {
        return plugin;
    }

    /**
     * Returns the {@link EntityWorld} for the given {@link Plugin}<br>
     * You can configure the systems the world uses using {@link #getWorldConfigurationBuilder(Plugin)}.<br>
     * You can't change the systems after you first created a {@link EntityWorld} using this method!
     *
     * @param plugin the {@link Plugin} that requests the {@link EntityWorld}
     * @return The {@link EntityWorld}
     */
    public EntityWorld getWorld(Plugin plugin) {
        return worldMap.computeIfAbsent(plugin.getName(), k -> new EntityWorld(plugin, getWorldConfigurationBuilder(plugin).build().setInjector(createInjector(plugin))));
    }

    /**
     * Returns the {@link WorldConfigurationBuilder} for the given {@link Plugin}.<br>
     * <b>NOTE: Do not use this after you called {@link #getWorld(Plugin)}! You currently can't have multiple worlds for one plugin!</b><br>
     * <b>NOTE: Don't call build on the {@link WorldConfigurationBuilder}, it will reset the builder!</b>
     *
     * @param plugin the {@link Plugin} that requests the {@link WorldConfigurationBuilder}
     * @return the {@link WorldConfigurationBuilder}
     */
    public WorldConfigurationBuilder getWorldConfigurationBuilder(Plugin plugin) {
        return worldConfigurationBuilderMap.computeIfAbsent(plugin.getName(), this::getNewWorldConfigurationBuilder);
    }

    /**
     * Creates a new {@link WorldConfigurationBuilder} and registers all internal systems
     *
     * @param key The plugin name, here so that we can use a method reference ^^
     * @return The {@link WorldConfigurationBuilder}
     */
    private WorldConfigurationBuilder getNewWorldConfigurationBuilder(String key) {
        return new WorldConfigurationBuilder()
                .with(new SpawnSystem());
    }

    /**
     * Creates a new {@link Injector} the specified {@link Plugin} is gonna use for dependency injection.
     *
     * @param plugin The {@link Plugin}
     * @return the created {@link Injector}
     */
    private Injector createInjector(Plugin plugin) {
        FieldHandler fieldHandler = new FieldHandler(new InjectionCache());
        fieldHandler.addFieldResolver(new CustomEntitiesFieldResolver(this.plugin, plugin));
        fieldHandler.addFieldResolver(new WiredFieldResolver());
        fieldHandler.addFieldResolver(new ArtemisFieldResolver());

        return new CachedInjector().setFieldHandler(fieldHandler);
    }

    /**
     * Ticks all {@link com.artemis.World}s
     */
    private void tick() {
        long delta = System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();

        worldMap.forEach((plugin, world) -> {
            world.setDelta(delta);
            world.process();
        });
    }

}
