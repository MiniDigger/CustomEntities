package me.minidigger.customentities.api.nms;

import me.minidigger.customentities.api.CustomEntities;

/**
 * Created by Yamakaja on 15.05.17.
 */
public interface NMSHandler {

    void registerTransformer();

    String getVersion();

    EntityRegistry getEntityRegistry();

    CustomEntities getPlugin();

}
