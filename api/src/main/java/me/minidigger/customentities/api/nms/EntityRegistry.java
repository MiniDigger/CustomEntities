package me.minidigger.customentities.api.nms;

import org.bukkit.entity.EntityType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yamakaja on 15.05.17.
 */
public abstract class EntityRegistry {

    private Map<Short, Short> mobIds = new ConcurrentHashMap<>();

    /**
     * Register entity
     *
     * @param entityName The name to register
     * @param entityId The string id
     * @param entityClass The class of the entity to register
     * @param entityBase The entity type
     */
    public void register(String entityName, String entityId, Class<?> entityClass, EntityType entityBase) {
        short id = (short) (1000 + mobIds.size());
        mobIds.put(id, entityBase.getTypeId());

        registerEntity(id, entityId, entityClass, entityName);
    }

    protected abstract void registerEntity(int id, String entityId, Class<?> entityClass, String entityName);

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
