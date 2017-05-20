package me.minidigger.customentities.v1_11_R1.transformer;

import me.yamakaja.runtimetransformer.annotation.Inject;
import me.yamakaja.runtimetransformer.annotation.InjectionType;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.World;

import me.yamakaja.runtimetransformer.annotation.Transform;
import org.bukkit.Bukkit;

@Transform(EntityLiving.class)
public abstract class EntityLivingTransformer extends EntityLiving {

    private EntityLivingTransformer(World world) {
        super(world);
    }

    @Inject(InjectionType.INSERT)
    public void setHealth(float f) {
        Bukkit.broadcastMessage(this.getName() + ": " + this.getHealth() + " -> " + f);
    }
}
