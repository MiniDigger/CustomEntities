package me.minidigger.customentities.api;

import me.minidigger.customentities.api.nms.NMSHandler;
import me.minidigger.customentities.api.world.EntityWorld;
import me.minidigger.customentities.api.world.WorldHandler;
import org.bukkit.plugin.Plugin;

/**
 * Main API entry point
 */
public interface CustomEntities extends Plugin {

    /**
     * Get the {@link NMSHandler} providing version specific implementations
     *
     * @return The {@link NMSHandler}
     */
    NMSHandler getNmsHandler();

    /**
     * Get the {@link WorldHandler} which manages all {@link EntityWorld}s for this server
     *
     * @return The {@link WorldHandler}
     */
    WorldHandler getWorldHandler();
}
