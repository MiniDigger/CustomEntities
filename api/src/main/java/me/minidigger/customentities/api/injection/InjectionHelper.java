package me.minidigger.customentities.api.injection;

import me.minidigger.customentities.api.CustomEntities;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class InjectionHelper {

    private CustomEntities customEntities;
    private Plugin plugin;
    private Logger logger;
    private Logger internalLogger;

    public CustomEntities getCustomEntities() {
        return customEntities;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public Logger getInternalLogger() {
        return internalLogger;
    }

    private static InjectionHelper instance = new InjectionHelper();

    public static InjectionHelper getInstance() {
        return instance;
    }

    private InjectionHelper() {
        //NOP
    }
}
