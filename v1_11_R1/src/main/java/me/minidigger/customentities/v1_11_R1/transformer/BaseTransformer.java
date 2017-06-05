package me.minidigger.customentities.v1_11_R1.transformer;

import me.yamakaja.runtimetransformer.annotation.Inject;
import me.yamakaja.runtimetransformer.annotation.InjectionType;
import me.yamakaja.runtimetransformer.annotation.Transform;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.World;

@Transform(EntityLiving.class)
public abstract class BaseTransformer extends EntityLiving {

    private static final String NBT_KEY_CUSTOM_ENTITIES = "CustomEntities";

    private BaseTransformer(World world) {
        super(world);
    }

    // NMS: b -> writeEntityToNBT
    @Inject(InjectionType.INSERT)
    public void b(NBTTagCompound nbttagcompound) {
    }

    // NMS: a -> readEntityFromNBT
    @Inject(InjectionType.INSERT)
    public void a(NBTTagCompound nbttagcompound) {
    }
}
