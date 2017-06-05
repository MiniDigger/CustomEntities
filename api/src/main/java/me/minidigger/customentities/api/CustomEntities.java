package me.minidigger.customentities.api;

import me.minidigger.customentities.api.nms.NMSHandler;
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

}
