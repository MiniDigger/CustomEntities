package me.minidigger.customentities.api.components;

import me.minidigger.customentities.api.Documentation;
import org.bukkit.entity.Projectile;

/**
 * Allows the entity to shoot projectiles at targets
 */
@Documentation("Allows the entity to shoot projectiles at targets")
public class RangedCombatComponent extends CombatComponent {

    public Class<? extends Projectile> projectileClass;
    public float projectileDamage;
    public float projectileKnockback;
    public float attackRange;

}
