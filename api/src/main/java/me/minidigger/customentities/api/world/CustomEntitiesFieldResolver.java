package me.minidigger.customentities.api.world;

import com.artemis.World;
import com.artemis.injection.FieldResolver;
import com.artemis.utils.reflect.Field;
import me.minidigger.customentities.api.CustomEntities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

/**
 * Handles custom injections. Currently injects:
 * <ul>
 * <li>{@link Plugin}</li>
 * <li>{@link CustomEntities}</li>
 * <li>{@link Logger}</li>
 * </ul>
 */
public class CustomEntitiesFieldResolver implements FieldResolver {

    private EntityWorld world;
    private CustomEntities customEntities;

    public CustomEntitiesFieldResolver(CustomEntities customEntities) {
        this.customEntities = customEntities;
    }

    @Override
    public void initialize(World world) {
        if (world instanceof EntityWorld) {
            this.world = (EntityWorld) world;
        } else {
            customEntities.getLogger().warning("Tried to initialize field resolver with unknown world type: " + world.getClass().getName());
        }
    }

    @Override
    public Object resolve(Class<?> fieldType, Field field) {
        if (fieldType.isAssignableFrom(Plugin.class)) {
            return world.getPlugin();
        } else if (fieldType.equals(CustomEntities.class)) {
            return customEntities;
        } else if (fieldType.equals(Logger.class)) {
            return customEntities.getLogger();
        }
        return null;
    }
}
