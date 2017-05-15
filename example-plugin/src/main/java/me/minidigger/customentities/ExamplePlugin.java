package me.minidigger.customentities;

import com.artemis.ComponentMapper;
import me.minidigger.customentities.api.components.BaseTypeComponent;
import me.minidigger.customentities.api.world.EntityWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Martin on 15/05/2017.
 */
public class ExamplePlugin extends JavaPlugin {

    private ComponentMapper<BaseTypeComponent> baseTypeComponentMapper;

    @Override
    public void onEnable() {
        CustomEntitiesImpl customEntities = getPlugin(CustomEntitiesImpl.class);
        if (customEntities == null) {
            getLogger().severe("Could not found dependency plugin CustomEntities, disabling...");
            getPluginLoader().disablePlugin(this);
            return;
        }

        customEntities.getWorldHandler().getWorldConfigurationBuilder(this)
        // .with(SomeSystem.class)
        ;

        EntityWorld world = customEntities.getWorldHandler().getWorld(this);
        int entity = world.create();
        baseTypeComponentMapper.create(entity);
        baseTypeComponentMapper.get(entity).baseType = EntityType.PIG;
    }
}
