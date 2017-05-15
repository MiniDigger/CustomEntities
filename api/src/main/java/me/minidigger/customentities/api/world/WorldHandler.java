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

    private Map<String, EntityWorld> worldMap;
    private Map<String, WorldConfigurationBuilder> worldConfigurationBuilderMap;

    private long lastTick = System.currentTimeMillis();

    private Injector injector;

    public WorldHandler(CustomEntities customEntities) {
        worldMap = new HashMap<>();
        worldConfigurationBuilderMap = new HashMap<>();

        FieldHandler fieldHandler = new FieldHandler(new InjectionCache());
        fieldHandler.addFieldResolver(new CustomEntitiesFieldResolver());
        fieldHandler.addFieldResolver(new WiredFieldResolver());
        fieldHandler.addFieldResolver(new ArtemisFieldResolver());

        injector = new CachedInjector().setFieldHandler(fieldHandler);

        // tick loop
        Bukkit.getScheduler().runTaskTimer(customEntities, this::tick, 1, 1);
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
        return worldMap.computeIfAbsent(plugin.getName(), k -> new EntityWorld(plugin, getWorldConfigurationBuilder(plugin).build().setInjector(injector)));
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
     * Ticks all worlds
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
