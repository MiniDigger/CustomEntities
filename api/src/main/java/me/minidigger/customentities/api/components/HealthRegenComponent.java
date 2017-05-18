package me.minidigger.customentities.api.components;

import com.artemis.Component;
import me.minidigger.customentities.api.Documentation;

/**
 * Allows the entity to regenerate health
 */
@Documentation("Allows the entity to regenerate health")
public class HealthRegenComponent extends Component {

    public float regenSpeed;
    public float regenAmount;
}
