package me.minidigger.customentities.api.components;

import com.artemis.Component;
import me.minidigger.customentities.api.Documentation;
import org.bukkit.World;

/**
 * Represents the position of an entity
 */
@Documentation(val = "Represents the position of an entity")
public class PositionComponent extends Component {

    public World world;

    public double x;
    public double y;
    public double z;

    public float yaw;
    public float pitch;

}
