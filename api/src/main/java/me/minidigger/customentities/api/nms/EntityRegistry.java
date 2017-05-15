package me.minidigger.customentities.api.nms;

import org.bukkit.entity.EntityType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yamakaja on 15.05.17.
 */
public class EntityRegistry {

    private Method registerEntityMethod;

    private Map<Short, Short> mobIds = new ConcurrentHashMap<>();

    public EntityRegistry(Class<?> entityTypes) {
        try {
            registerEntityMethod = entityTypes.getDeclaredMethod("a", int.class, String.class, Class.class, String.class);
            registerEntityMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Register entity
     *
     * @param name The name to register
     * @param entityId The string id
     * @param entityClass The class of the entity to register
     * @param entityBase The entity type
     */
    public void register(String name, String entityId, Class<?> entityClass, EntityType entityBase) {
        short id = (short) (1000 + mobIds.size());
        mobIds.put(id, entityBase.getTypeId());

        try {
            registerEntityMethod.invoke(null, id, entityId, entityClass, name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Translates server entity-ids to ones suitable for the client
     *
     * @param serverId The server id to translate
     * @return The translated id
     */
    public short getIdForClient(short serverId) {
        return mobIds.getOrDefault(serverId, serverId);
    }

}
