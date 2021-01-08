//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityUtil {
  public static boolean isPassive(Entity e) {
    if (e instanceof EntityWolf && ((EntityWolf)e).isAngry())
      return false; 
    if (e instanceof net.minecraft.entity.passive.EntityAnimal || e instanceof net.minecraft.entity.EntityAgeable || e instanceof net.minecraft.entity.passive.EntityTameable || e instanceof net.minecraft.entity.passive.EntityAmbientCreature || e instanceof net.minecraft.entity.passive.EntitySquid)
      return true; 
    if (e instanceof EntityIronGolem && ((EntityIronGolem)e).getRevengeTarget() == null)
      return true; 
    return false;
  }
  
  public static boolean isLiving(Entity e) {
    return e instanceof net.minecraft.entity.EntityLivingBase;
  }
  
  public static boolean isFakeLocalPlayer(Entity entity) {
    return (entity != null && entity.getEntityId() == -100 && (Minecraft.getMinecraft()).player != entity);
  }
  
  public static Vec3d getInterpolatedAmount(Entity entity, double x, double y, double z) {
    return new Vec3d((entity.posX - entity.lastTickPosX) * x, 0.0D * y, (entity.posZ - entity.lastTickPosZ) * z);
  }
  
  public static Vec3d getInterpolatedAmount(Entity entity, Vec3d vec) {
    return getInterpolatedAmount(entity, vec.x, vec.y, vec.z);
  }
  
  public static Vec3d getInterpolatedAmount(Entity entity, double ticks) {
    return getInterpolatedAmount(entity, ticks, ticks, ticks);
  }
  
  public static boolean isMobAggressive(Entity entity) {
    if (entity instanceof EntityPigZombie) {
      if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry())
        return true; 
    } else {
      if (entity instanceof EntityWolf)
        return (((EntityWolf)entity).isAngry() && 
          !(Minecraft.getMinecraft()).player.equals(((EntityWolf)entity).getOwner())); 
      if (entity instanceof EntityEnderman)
        return ((EntityEnderman)entity).isScreaming(); 
    } 
    return isHostileMob(entity);
  }
  
  public static boolean isNeutralMob(Entity entity) {
    return (entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman);
  }
  
  public static boolean isFriendlyMob(Entity entity) {
    return ((entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity)) || entity
      .isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof net.minecraft.entity.passive.EntityVillager || entity instanceof EntityIronGolem || (
      isNeutralMob(entity) && !isMobAggressive(entity)));
  }
  
  public static boolean isHostileMob(Entity entity) {
    return (entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity));
  }
  
  public static Vec3d getInterpolatedPos(Entity entity, float ticks) {
    return (new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ))
      .add(getInterpolatedAmount(entity, ticks));
  }
  
  public static Vec3d getInterpolatedRenderPos(Entity entity, float ticks) {
    return getInterpolatedPos(entity, ticks).subtract((Minecraft.getMinecraft().getRenderManager()).renderPosX, 
        (Minecraft.getMinecraft().getRenderManager()).renderPosY, 
        (Minecraft.getMinecraft().getRenderManager()).renderPosZ);
  }
  
  public static boolean isInWater(Entity entity) {
    if (entity == null)
      return false; 
    double y = entity.posY + 0.01D;
    for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); x++) {
      for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); z++) {
        BlockPos pos = new BlockPos(x, (int)y, z);
        if ((Minecraft.getMinecraft()).world.getBlockState(pos).getBlock() instanceof net.minecraft.block.BlockLiquid)
          return true; 
      } 
    } 
    return false;
  }
  
  public static boolean isDrivenByPlayer(Entity entityIn) {
    return ((Minecraft.getMinecraft()).player != null && entityIn != null && entityIn
      .equals((Minecraft.getMinecraft()).player.getRidingEntity()));
  }
  
  public static boolean isAboveWater(Entity entity) {
    return isAboveWater(entity, false);
  }
  
  public static boolean isAboveWater(Entity entity, boolean packet) {
    if (entity == null)
      return false; 
    double y = entity.posY - (packet ? 0.03D : (isPlayer(entity) ? 0.2D : 0.5D));
    for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); x++) {
      for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); z++) {
        BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
        if ((Minecraft.getMinecraft()).world.getBlockState(pos).getBlock() instanceof net.minecraft.block.BlockLiquid)
          return true; 
      } 
    } 
    return false;
  }
  
  public static double[] calculateLookAt(double px, double py, double pz, EntityPlayer me) {
    double dirx = me.posX - px;
    double diry = me.posY - py;
    double dirz = me.posZ - pz;
    double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
    dirx /= len;
    diry /= len;
    dirz /= len;
    double pitch = Math.asin(diry);
    double yaw = Math.atan2(dirz, dirx);
    pitch = pitch * 180.0D / Math.PI;
    yaw = yaw * 180.0D / Math.PI;
    yaw += 90.0D;
    return new double[] { yaw, pitch };
  }
  
  public static boolean isPlayer(Entity entity) {
    return entity instanceof EntityPlayer;
  }
  
  public static double getRelativeX(float yaw) {
    return MathHelper.sin(-yaw * 0.017453292F);
  }
  
  public static double getRelativeZ(float yaw) {
    return MathHelper.cos(yaw * 0.017453292F);
  }
  
  public static int GetPlayerMS(EntityPlayer p_Player) {
    if (p_Player.getUniqueID() == null)
      return 0; 
    NetworkPlayerInfo playerInfo = Minecraft.getMinecraft().getConnection().getPlayerInfo(p_Player.getUniqueID());
    if (playerInfo == null)
      return 0; 
    return playerInfo.getResponseTime();
  }
  
  public static Vec3d CalculateExpectedPosition(EntityPlayer p_Player) {
    Vec3d l_Position = new Vec3d(p_Player.posX, p_Player.posY, p_Player.posZ);
    if (p_Player.lastTickPosX != p_Player.posX && p_Player.lastTickPosY != p_Player.posY && p_Player.lastTickPosZ != p_Player.posZ)
      return l_Position; 
    int l_PlayerMS = GetPlayerMS(p_Player);
    double deltaX = p_Player.posX - p_Player.prevPosX;
    double deltaZ = p_Player.posZ - p_Player.prevPosZ;
    float tickRate = (Minecraft.getMinecraft()).timer.tickLength / 1000.0F;
    float l_Distance = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
    double l_Facing = MathUtil.calculateAngle(p_Player.posX, p_Player.posZ, p_Player.lastTickPosX, p_Player.lastTickPosZ) / 45.0D;
    return new Vec3d(p_Player.posX + 
        Math.cos(l_Facing) * l_Distance, p_Player.posY, p_Player.posZ + 
        
        Math.sin(l_Facing) * l_Distance);
  }
  
  public static double GetDistance(double p_X, double p_Y, double p_Z, double x, double y, double z) {
    double d0 = p_X - x;
    double d1 = p_Y - y;
    double d2 = p_Z - z;
    return MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
  }
  
  public static double GetDistanceOfEntityToBlock(Entity p_Entity, BlockPos p_Pos) {
    return GetDistance(p_Entity.posX, p_Entity.posY, p_Entity.posZ, p_Pos.getX(), p_Pos.getY(), p_Pos.getZ());
  }
}
