package me.minidigger.customentities.api.injection;

import com.artemis.World;
import com.artemis.injection.FieldResolver;
import com.artemis.utils.reflect.Field;
import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.world.EntityWorld;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Handles custom injections. Currently injects:
 * <ul>
 * <li>{@link Plugin}</li>
 * <li>{@link CustomEntities}</li>
 * <li>{@link Logger} (both plugin (default) and API (via @{@link Internal}))</li>
 * </ul>
 */
public class CustomEntitiesFieldResolver implements FieldResolver {

    private CustomEntities customEntities;
    private Plugin plugin;

    public CustomEntitiesFieldResolver(CustomEntities customEntities, Plugin plugin) {
        this.customEntities = customEntities;
        this.plugin = plugin;
    }

    @Override
    public void initialize(World world) {
    }

    @Override
    public Object resolve(Class<?> fieldType, Field field) {
        if (fieldType.equals(CustomEntities.class)) {
            return customEntities;
        } else if (Plugin.class.isAssignableFrom(fieldType)) {
            return plugin;
        } else if (fieldType.equals(Logger.class)) {
            if (field.isAnnotationPresent(Internal.class)) {
                return customEntities.getLogger();
            } else {
                return plugin.getLogger();
            }
        }
        return null;
    }
}
