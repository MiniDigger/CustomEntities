package me.minidigger.customentities.api.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;
import org.bukkit.entity.EntityType;

/**
 * Species the base {@link EntityType} a entity should have
 */
@PooledWeaver
public class BaseTypeComponent extends Component {

    public EntityType baseType = EntityType.UNKNOWN;
}
