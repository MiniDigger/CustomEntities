package me.minidigger.customentities.v1_11_R1;

import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.nms.NMSHandler;

/**
 * Created by Yamakaja on 15.05.17.
 */
public class NMSHandler_v1_11_R1 implements NMSHandler {

    private CustomEntities plugin;

    public NMSHandler_v1_11_R1(CustomEntities plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getVersion() {
        return "v1_11_R1";
    }

}
