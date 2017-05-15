package me.minidigger.customentities.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.minidigger.customentities.api.CustomEntities;

/**
 * Created by Yamakaja on 15.05.17.
 */
public class EntityPacketTranslator extends PacketAdapter {

    private CustomEntities plugin;

    public EntityPacketTranslator(CustomEntities plugin) {
        super(plugin, PacketType.Play.Server.SPAWN_ENTITY_LIVING);
        this.plugin = plugin;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        event.getPacket().getIntegers().write(1, (int) plugin.getNmsHandler().getEntityRegistry().getIdForClient(event.getPacket().getIntegers().read(1)));
    }
}
