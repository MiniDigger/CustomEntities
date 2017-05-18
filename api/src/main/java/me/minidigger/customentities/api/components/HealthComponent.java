package me.minidigger.customentities.api.components;

import com.artemis.Component;
import me.minidigger.customentities.api.Documentation;

/**
 * Adds health to the entity (and the ability to die)
 */
@Documentation("Adds health to the entity (and the ability to die)")
public class HealthComponent extends Component {

    public float maxHealth;
    public float health;

}
