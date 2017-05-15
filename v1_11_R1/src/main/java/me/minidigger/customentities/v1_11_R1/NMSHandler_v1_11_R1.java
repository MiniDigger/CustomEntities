package me.minidigger.customentities.v1_11_R1;

import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.nms.EntityRegistry;
import me.minidigger.customentities.api.nms.NMSHandler;
import net.minecraft.server.v1_11_R1.EntityTypes;

/**
 * Created by Yamakaja on 15.05.17.
 */
public class NMSHandler_v1_11_R1 implements NMSHandler {

    private CustomEntities plugin;
    private EntityRegistry entityRegistry;

    public NMSHandler_v1_11_R1(CustomEntities plugin) {
        this.plugin = plugin;
        this.entityRegistry = new EntityRegistry(EntityTypes.class);
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
