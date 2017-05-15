package me.minidigger.customentities;

import com.comphenix.protocol.ProtocolLibrary;
import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.nms.NMSHandler;
import me.minidigger.customentities.api.world.WorldHandler;
import me.minidigger.customentities.protocol.EntityPacketTranslator;
import me.minidigger.customentities.v1_11_R1.NMSHandler_v1_11_R1;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * Created by Yamakaja on 15.05.17.
 */
public class CustomEntitiesImpl extends JavaPlugin implements CustomEntities {

    private NMSHandler nmsHandler;
    private WorldHandler worldHandler;

    @Override
    public void onEnable() {
        if (!loadNMSHandler()) {
            getPluginLoader().disablePlugin(this);
            return;
        }

        worldHandler = new WorldHandler();

        ProtocolLibrary.getProtocolManager().addPacketListener(new EntityPacketTranslator(this));

        getLogger().info("Loaded successfully with NMS version " + getNmsHandler().getVersion() + "!");
    }

    /**
     * Loads the {@link NMSHandler} for the current version
     *
     * @return Whether loading a suitable handler was successful
     */
    private boolean loadNMSHandler() {
        String nmsVersion;
        try {
            nmsVersion = Bukkit.getServer().getClass().getPackage().getName().split(Pattern.quote("."))[3];
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "An error occurred while determining server version! Disabling plugin ...", ex);
            return false;
        }

        switch (nmsVersion) {
            case "v1_11_R1":
                nmsHandler = new NMSHandler_v1_11_R1(this);
                break;
            default:
                getLogger().severe("Unsupported version: \"" + nmsVersion + "\". Disabling plugin!");
                getPluginLoader().disablePlugin(this);
        }

        return true;
    }

    @Override
    public NMSHandler getNmsHandler() {
        return nmsHandler;
    }

    @Override
    public WorldHandler getWorldHandler() {
        return worldHandler;
    }
}
