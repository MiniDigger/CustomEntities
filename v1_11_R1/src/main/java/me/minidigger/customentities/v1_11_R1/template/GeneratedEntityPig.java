//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.minidigger.customentities.v1_11_R1.template;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.server.v1_11_R1.AchievementList;
import net.minecraft.server.v1_11_R1.Block;
import net.minecraft.server.v1_11_R1.BlockPosition;
import net.minecraft.server.v1_11_R1.DamageSource;
import net.minecraft.server.v1_11_R1.DataConverterManager;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityAgeable;
import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityInsentient;
import net.minecraft.server.v1_11_R1.EntityLightning;
import net.minecraft.server.v1_11_R1.EntityPig;
import net.minecraft.server.v1_11_R1.EntityPigZombie;
import net.minecraft.server.v1_11_R1.EnumHand;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.GenericAttributes;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.Items;
import net.minecraft.server.v1_11_R1.LootTables;
import net.minecraft.server.v1_11_R1.MathHelper;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.PathfinderGoalBreed;
import net.minecraft.server.v1_11_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_11_R1.PathfinderGoalFollowParent;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoalPanic;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_11_R1.PathfinderGoalTempt;
import net.minecraft.server.v1_11_R1.SoundEffect;
import net.minecraft.server.v1_11_R1.SoundEffects;
import net.minecraft.server.v1_11_R1.World;
import org.bukkit.craftbukkit.v1_11_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class GeneratedEntityPig extends EntityPig implements IHealthTemplate {

  private static final DataWatcherObject<Boolean> bw;
  private static final DataWatcherObject<Integer> bx;
  private static final Set<Item> by;
  private boolean bA;
  private int bB;
  private int bC;

  static {
    bw = DataWatcher.a(EntityPig.class, DataWatcherRegistry.h);
    bx = DataWatcher.a(EntityPig.class, DataWatcherRegistry.b);
    by = Sets.newHashSet(new Item[]{Items.CARROT, Items.POTATO, Items.BEETROOT});
  }

  public GeneratedEntityPig(World world) {
    super(world);
    this.setSize(0.9F, 0.9F);
  }

  protected void r() {
    this.goalSelector.a(0, new PathfinderGoalFloat(this));
    this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.25D));
    this.goalSelector.a(3, new PathfinderGoalBreed(this, 1.0D));
    this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.2D, Items.CARROT_ON_A_STICK, false));
    this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.2D, false, by));
    this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.1D));
    this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 1.0D));
    this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
  }

  protected void initAttributes() {
    super.initAttributes();
    this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
    this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
  }

  @Nullable
  public Entity bw() {
    return this.bx().isEmpty() ? null : (Entity) this.bx().get(0);
  }

  public boolean cR() {
    Entity entity = this.bw();
    if (!(entity instanceof EntityHuman)) {
      return false;
    } else {
      EntityHuman entityhuman = (EntityHuman) entity;
      return entityhuman.getItemInMainHand().getItem() == Items.CARROT_ON_A_STICK
          || entityhuman.getItemInOffHand().getItem() == Items.CARROT_ON_A_STICK;
    }
  }

  public void a(DataWatcherObject<?> datawatcherobject) {
    if (bx.equals(datawatcherobject) && this.world.isClientSide) {
      this.bA = true;
      this.bB = 0;
      this.bC = ((Integer) this.datawatcher.get(bx)).intValue();
    }

    super.a(datawatcherobject);
  }

  protected void i() {
    super.i();
    this.datawatcher.register(bw, false);
    this.datawatcher.register(bx, Integer.valueOf(0));
  }

  public static void a(DataConverterManager dataconvertermanager) {
    EntityInsentient.a(dataconvertermanager, EntityPig.class);
  }

  public void b(NBTTagCompound nbttagcompound) {
    super.b(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", this.hasSaddle());
  }

  public void a(NBTTagCompound nbttagcompound) {
    super.a(nbttagcompound);
    this.setSaddle(nbttagcompound.getBoolean("Saddle"));
  }

  protected SoundEffect G() {
    return SoundEffects.ep;
  }

  protected SoundEffect bW() {
    return SoundEffects.er;
  }

  protected SoundEffect bX() {
    return SoundEffects.eq;
  }

  protected void a(BlockPosition blockposition, Block block) {
    this.a(SoundEffects.et, 0.15F, 1.0F);
  }

  public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
    if (!super.a(entityhuman, enumhand)) {
      ItemStack itemstack = entityhuman.b(enumhand);
      if (itemstack.getItem() == Items.NAME_TAG) {
        itemstack.a(entityhuman, this, enumhand);
        return true;
      } else if (this.hasSaddle() && !this.isVehicle()) {
        if (!this.world.isClientSide) {
          entityhuman.startRiding(this);
        }

        return true;
      } else if (itemstack.getItem() == Items.SADDLE) {
        itemstack.a(entityhuman, this, enumhand);
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  public void die(DamageSource damagesource) {
    if (!this.world.isClientSide && this.hasSaddle()) {
      this.a(Items.SADDLE, 1);
    }

    super.die(damagesource);
  }

  @Nullable
  protected MinecraftKey J() {
    return LootTables.E;
  }

  public boolean hasSaddle() {
    return ((Boolean) this.datawatcher.get(bw)).booleanValue();
  }

  public void setSaddle(boolean flag) {
    if (flag) {
      this.datawatcher.set(bw, true);
    } else {
      this.datawatcher.set(bw, false);
    }

  }

  public void onLightningStrike(EntityLightning entitylightning) {
    if (!this.world.isClientSide && !this.dead) {
      EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
      if (CraftEventFactory.callPigZapEvent(this, entitylightning, entitypigzombie).isCancelled()) {
        return;
      }

      entitypigzombie.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
      entitypigzombie.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
      entitypigzombie.setAI(this.hasAI());
      if (this.hasCustomName()) {
        entitypigzombie.setCustomName(this.getCustomName());
        entitypigzombie.setCustomNameVisible(this.getCustomNameVisible());
      }

      this.world.addEntity(entitypigzombie, SpawnReason.LIGHTNING);
      this.die();
    }

  }

  public void e(float f, float f1) {
    super.e(f, f1);
    if (f > 5.0F) {
      Iterator iterator = this.b((Class) EntityHuman.class).iterator();

      while (iterator.hasNext()) {
        EntityHuman entityhuman = (EntityHuman) iterator.next();
        entityhuman.b(AchievementList.u);
      }
    }

  }

  public void g(float f, float f1) {
    Entity entity = this.bx().isEmpty() ? null : (Entity) this.bx().get(0);
    if (this.isVehicle() && this.cR()) {
      this.yaw = entity.yaw;
      this.lastYaw = this.yaw;
      this.pitch = entity.pitch * 0.5F;
      this.setYawPitch(this.yaw, this.pitch);
      this.aN = this.yaw;
      this.aP = this.yaw;
      this.P = 1.0F;
      this.aR = this.cq() * 0.1F;
      if (this.bA && this.bB++ > this.bC) {
        this.bA = false;
      }

      if (this.bA()) {
        float f2 =
            (float) this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue() * 0.225F;
        if (this.bA) {
          f2 += f2 * 1.15F * MathHelper.sin((float) this.bB / (float) this.bC * 3.1415927F);
        }

        this.l(f2);
        super.g(0.0F, 1.0F);
      } else {
        this.motX = 0.0D;
        this.motY = 0.0D;
        this.motZ = 0.0D;
      }

      this.aF = this.aG;
      double d0 = this.locX - this.lastX;
      double d1 = this.locZ - this.lastZ;
      float f3 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
      if (f3 > 1.0F) {
        f3 = 1.0F;
      }

      this.aG += (f3 - this.aG) * 0.4F;
      this.aH += this.aG;
    } else {
      this.P = 0.5F;
      this.aR = 0.02F;
      super.g(f, f1);
    }

  }

  public boolean di() {
    if (this.bA) {
      return false;
    } else {
      this.bA = true;
      this.bB = 0;
      this.bC = this.getRandom().nextInt(841) + 140;
      this.getDataWatcher().set(bx, this.bC);
      return true;
    }
  }

  public EntityPig b(EntityAgeable entityageable) {
    return new EntityPig(this.world);
  }

  public boolean e(ItemStack itemstack) {
    return by.contains(itemstack.getItem());
  }

  public EntityAgeable createChild(EntityAgeable entityageable) {
    return this.b(entityageable);
  }


  /*
  GENERATED CLASSES
   */

  @Override
  public void setHealth(float f) {
    // code to set health
  }
}
