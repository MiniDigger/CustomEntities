package me.minidigger.customentities;

import com.artemis.ComponentMapper;
import me.minidigger.customentities.api.components.BaseComponent;
import me.minidigger.customentities.api.components.BaseTypeComponent;
import me.minidigger.customentities.api.world.EntityWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Martin on 15/05/2017.
 */
public class ExamplePlugin extends JavaPlugin {

    private ComponentMapper<BaseTypeComponent> baseTypeComponentMapper;
    private ComponentMapper<BaseComponent> baseComponentMapper;

    @Override
    public void onEnable() {
        CustomEntitiesImpl customEntities = getPlugin(CustomEntitiesImpl.class);
        if (customEntities == null) {
            getLogger().severe("Could not found dependency plugin CustomEntities, disabling...");
            getPluginLoader().disablePlugin(this);
            return;
        }

        customEntities.getWorldHandler().getWorldConfigurationBuilder(this)
        // .with(SomeSystem.class) // register systems here
        ;
        EntityWorld world = customEntities.getWorldHandler().getWorld(this);
        world.inject(this); // always inject the plugin before, else you can't use component mappers and stuff here!
        int entity = world.create();
        baseTypeComponentMapper.create(entity);
        baseTypeComponentMapper.get(entity).baseType = EntityType.PIG;

        baseComponentMapper.create(entity);
    }
}
