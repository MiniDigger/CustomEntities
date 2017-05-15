package me.minidigger.customentities.api.world;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * Represents a world from the point of view from the ECS. This is NOT a {@link org.bukkit.World}, its a {@link com.artemis.World}!<br>
 * A {@link EntityWorld} inhabits all entities, systems and components that a plugin created
 */
public class EntityWorld extends World {

    private Plugin plugin;

    EntityWorld(Plugin plugin, WorldConfiguration configuration) {
        super(configuration);
        this.plugin = plugin;
    }

    /**
     * The plugin which manages this world
     *
     * @return The {@link Plugin}
     */
    public Plugin getPlugin() {
        return plugin;
    }
}
