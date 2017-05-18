package me.minidigger.customentities;

import com.artemis.ComponentMapper;
import me.minidigger.customentities.api.components.EntityBaseComponent;
import me.minidigger.customentities.api.world.EntityWorld;
import me.minidigger.customentities.api.world.WorldHandler;
import me.minidigger.customentities.components.ExampleComponent;
import me.minidigger.customentities.systems.ExampleSystem;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Example plugin used to test and demonstrate how this api works
 */
public class ExamplePlugin extends JavaPlugin {

    // Component mappers are injected automatically
    private ComponentMapper<EntityBaseComponent> entityBaseComponentMapper;
    private ComponentMapper<ExampleComponent> exampleComponentMapper;
    private WorldHandler worldHandler;

    @Override
    public void onEnable() {
        worldHandler = WorldHandler.getInstance();

        worldHandler.getWorldConfigurationBuilder(this)
                .with(new ExampleSystem()); // add custom systems here

        EntityWorld world = worldHandler.getWorld(this); // create the world object
        world.inject(this); // always inject the plugin before, else you can't use component mappers and stuff here!
        int entity = world.create(); // create a new entity

        entityBaseComponentMapper.create(entity); // add a base type component
        entityBaseComponentMapper.get(entity).baseType = EntityType.PIG; // change the base type

        exampleComponentMapper.create(entity); // add an example component
    }
}
