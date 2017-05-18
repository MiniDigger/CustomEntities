package me.minidigger.customentities.api.components;

import me.minidigger.customentities.api.Documentation;

/**
 * Allows the entity to fight targets at melee range
 */
@Documentation(val = "Allows the entity to fight targets at melee range")
public class MeleeCombatComponent extends CombatComponent {

    public float attackDamage;
    public float knockback;
    public float attackRange;

}
