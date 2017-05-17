package me.minidigger.customentities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import me.minidigger.customentities.ExamplePlugin;
import me.minidigger.customentities.api.CustomEntities;
import me.minidigger.customentities.api.injection.Internal;
import me.minidigger.customentities.components.ExampleComponent;

import java.util.logging.Logger;

/**
 * Example system that counts up a number for all entities that have a {@link ExampleComponent}
 */
public class ExampleSystem extends IteratingSystem {

    // component mappers get automatically injected
    private ComponentMapper<ExampleComponent> exampleComponentMapper;

    // some stuff gets automatically injected
    private Logger logger;
    private ExamplePlugin plugin;
    private CustomEntities customEntities;

    public ExampleSystem() {
        // create an aspect for the entities we want to process in this system
        super(Aspect.all(ExampleComponent.class));
    }

    @Override
    protected void process(int entityId) {
        ExampleComponent exampleComponent = exampleComponentMapper.get(entityId); // get the example component

        if (exampleComponent.age % 100 == 0) {
            logger.info("Age: " + exampleComponent.age);
        }

        exampleComponent.age++; // change the age
    }
}
