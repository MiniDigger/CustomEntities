package me.minidigger.customentities.api.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;
import org.bukkit.entity.EntityType;

/**
 * Created by Martin on 15/05/2017.
 */
@PooledWeaver
public class EntityBaseComponent extends Component {

    public boolean spawned = false;

    public EntityType baseType = EntityType.UNKNOWN;

}
