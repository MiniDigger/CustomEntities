package me.minidigger.customentities.v1_11_R1.template;

import net.minecraft.server.v1_11_R1.EntityPig;
import net.minecraft.server.v1_11_R1.World;

public class HealthTemplate extends EntityPig implements IHealthTemplate {

  public HealthTemplate(World world) {
    super(world);
  }

  @Override
  public void setHealth(float health) {
      // code to set health
  }
}
