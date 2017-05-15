package me.minidigger.customentities;

import com.artemis.ComponentMapper;
import me.minidigger.customentities.api.components.BaseComponent;
import me.minidigger.customentities.api.components.BaseTypeComponent;
import me.minidigger.customentities.api.world.EntityWorld;
import me.minidigger.customentities.components.ExampleComponent;
import me.minidigger.customentities.systems.ExampleSystem;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Example plugin used to test and demonstrate how this api works
 */
public class ExamplePlugin extends JavaPlugin {

    // component mappers get automatically injected
    private ComponentMapper<BaseTypeComponent> baseTypeComponentMapper;
    private ComponentMapper<BaseComponent> baseComponentMapper;
    private ComponentMapper<ExampleComponent> exampleComponentMapper;

    @Override
    public void onEnable() {
        CustomEntitiesImpl customEntities = getPlugin(CustomEntitiesImpl.class);
        if (customEntities == null) {
            getLogger().severe("Could not found dependency plugin CustomEntities, disabling...");
            getPluginLoader().disablePlugin(this);
            return;
        }

        customEntities.getWorldHandler().getWorldConfigurationBuilder(this)
                .with(new ExampleSystem()); // add custom systems here
        EntityWorld world = customEntities.getWorldHandler().getWorld(this); // create the world object
        world.inject(this); // always inject the plugin before, else you can't use component mappers and stuff here!
        int entity = world.create(); // create a new entity
        baseTypeComponentMapper.create(entity); // add a base type component
        baseTypeComponentMapper.get(entity).baseType = EntityType.PIG; // change the base type

        baseComponentMapper.create(entity); // add a base component

        exampleComponentMapper.create(entity); // add an example component
    }
}
