package me.minidigger.customentities.v1_11_R1;

import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.nms.EntityRegistry;
import me.minidigger.customentities.api.nms.NMSHandler;
import net.minecraft.server.v1_11_R1.EntityTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yamakaja on 15.05.17.
 */
public class NMSHandler_v1_11_R1 implements NMSHandler {

    private CustomEntities plugin;
    private EntityRegistry entityRegistry;

    public NMSHandler_v1_11_R1(CustomEntities plugin) {
        this.plugin = plugin;
        this.entityRegistry = new EntityRegistry() {

            private Method registerEntityMethod;
            {
                try {
                    registerEntityMethod = EntityTypes.class.getDeclaredMethod("a", int.class, String.class, Class.class, String.class);
                    registerEntityMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void registerEntity(int id, String entityId, Class<?> entityClass, String entityName) {
                try {
                    registerEntityMethod.invoke(null, id, entityId, entityClass, entityName);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        };
    }

    @Override
    public String getVersion() {
        return "v1_11_R1";
    }

    @Override
    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    @Override
    public CustomEntities getPlugin() {
        return plugin;
    }
}
