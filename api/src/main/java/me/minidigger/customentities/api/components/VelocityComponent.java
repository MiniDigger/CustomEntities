package me.minidigger.customentities.api.components;

import com.artemis.Component;
import me.minidigger.customentities.api.Documentation;

/**
 * Tracks the velocity of the entity for each axis
 */
@Documentation("Tracks the velocity of the entity for each axis")
public class VelocityComponent extends Component {

    public double motX;
    public double motY;
    public double motZ;

}
