package me.minidigger.customentities.api.components;

import me.minidigger.customentities.api.Documentation;

/**
 * Adds the ability to move to the entity
 */
@Documentation("Adds the ability to move to the entity")
public class MotionComponent {

    public float forwardSpeed;
    public float strafeSpeed;
    public float backMod;

    public float stepHeight;

}
