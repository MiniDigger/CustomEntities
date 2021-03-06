package me.minidigger.customentities.api.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import me.minidigger.customentities.api.components.EntityBaseComponent;
import me.minidigger.customentities.api.injection.Internal;

import java.util.logging.Logger;

/**
 * Created by Martin on 15/05/2017.
 */
public class SpawnSystem extends IteratingSystem {

    private ComponentMapper<EntityBaseComponent> baseComponentMapper;
    @Internal
    private Logger logger;

    public SpawnSystem() {
        super(Aspect.all(EntityBaseComponent.class));
    }

    @Override
    protected void process(int entityId) {
        EntityBaseComponent baseComponent = baseComponentMapper.get(entityId);
        if (!baseComponent.spawned) {
            //TODO spawn entity
            baseComponent.spawned = true;
            logger.info("spawned entity");
        }
    }

    @Override
    protected void inserted(int entityId) {
        System.out.println("inserted " + entityId);
    }
}
