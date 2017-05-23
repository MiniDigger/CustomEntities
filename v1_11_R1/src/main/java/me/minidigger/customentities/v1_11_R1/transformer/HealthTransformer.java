package me.minidigger.customentities.v1_11_R1.transformer;

import me.yamakaja.runtimetransformer.annotation.Inject;
import me.yamakaja.runtimetransformer.annotation.InjectionType;
import me.yamakaja.runtimetransformer.annotation.Transform;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.World;

@Transform(EntityLiving.class)
public abstract class HealthTransformer extends EntityLiving {

    private HealthTransformer(World world) {
        super(world);
    }

    @Inject(InjectionType.INSERT)
    public void setHealth(float f) {

    }
}
