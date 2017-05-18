package me.minidigger.customentities.api.components;

import org.bukkit.entity.Projectile;

/**
 * Created by Yamakaja on 18.05.17.
 */
public class RangedCombatComponent extends CombatComponent {

    public Class<? extends Projectile> projectileClass;
    public float projectileDamage;
    public float projectileKnockback;

}
