//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.combat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Surround extends Module {
  private List<Block> whiteList;
  
  Setting sneak;
  
  Setting rotate;
  
  Setting center;
  
  Setting jump;
  
  boolean wasinair;
  
  public Surround() {
    super("Surround", Module.Category.COMBAT, "Places obsidian at your feet");
    this.whiteList = Arrays.asList(new Block[] { Blocks.OBSIDIAN, Blocks.ENDER_CHEST });
    this.wasinair = false;
  }
  
  public void setup() {
    this.sneak = new Setting("SneakOnly", this, false, "SurroundSneakOnly");
    this.jump = new Setting("JumpCheck", this, true, "SurroundJumpCheck");
    this.center = new Setting("Center", this, true, "SurroundCenter");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.sneak);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.jump);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.center);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rotate = new Setting("Rotate", this, true, "SurroundRotate"));
  }
  
  public Vec3d GetCenter(double posX, double posY, double posZ) {
    double x = Math.floor(posX) + 0.5D;
    double y = Math.floor(posY);
    double z = Math.floor(posZ) + 0.5D;
    return new Vec3d(x, y, z);
  }
  
  public void onEnable() {
    if (this.center.getValBoolean()) {
      Vec3d Center = GetCenter(mc.player.posX, mc.player.posY, mc.player.posZ);
      mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Center.x, Center.y, Center.z, true));
      mc.player.setPosition(Center.x, Center.y, Center.z);
    } 
    this.wasinair = !mc.player.onGround;
  }
  
  public static boolean hasNeighbour(BlockPos blockPos) {
    for (EnumFacing side : EnumFacing.values()) {
      BlockPos neighbour = blockPos.offset(side);
      if (!mc.world.getBlockState(neighbour).getMaterial().isReplaceable())
        return true; 
    } 
    return false;
  }
  
  public void onUpdate() {
    if (this.sneak.getValBoolean() && !mc.gameSettings.keyBindSneak.isKeyDown())
      return; 
    if (!isEnabled() || mc.player == null)
      return; 
    if (this.jump.getValBoolean() && !mc.player.onGround && !mc.player.prevOnGround && !this.wasinair)
      disable(); 
    if (this.wasinair)
      this.wasinair = !mc.player.onGround; 
    Vec3d vec3d = getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos northBlockPos = (new BlockPos(vec3d)).north();
    BlockPos southBlockPos = (new BlockPos(vec3d)).south();
    BlockPos eastBlockPos = (new BlockPos(vec3d)).east();
    BlockPos westBlockPos = (new BlockPos(vec3d)).west();
    int newSlot = -1;
    for (int i = 0; i < 9; i++) {
      ItemStack stack = mc.player.inventory.getStackInSlot(i);
      if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
        Block block = ((ItemBlock)stack.getItem()).getBlock();
        if (this.whiteList.contains(block)) {
          newSlot = i;
          break;
        } 
      } 
    } 
    if (newSlot == -1)
      return; 
    int oldSlot = mc.player.inventory.currentItem;
    mc.player.inventory.currentItem = newSlot;
    if (!hasNeighbour(northBlockPos)) {
      EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
      int j = arrayOfEnumFacing.length;
      byte b = 0;
      while (true) {
        if (b < j) {
          EnumFacing side = arrayOfEnumFacing[b];
          BlockPos neighbour = northBlockPos.offset(side);
          if (hasNeighbour(neighbour)) {
            northBlockPos = neighbour;
            break;
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
    if (!hasNeighbour(southBlockPos)) {
      EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
      int j = arrayOfEnumFacing.length;
      byte b = 0;
      while (true) {
        if (b < j) {
          EnumFacing side = arrayOfEnumFacing[b];
          BlockPos neighbour = southBlockPos.offset(side);
          if (hasNeighbour(neighbour)) {
            southBlockPos = neighbour;
            break;
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
    if (!hasNeighbour(eastBlockPos)) {
      EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
      int j = arrayOfEnumFacing.length;
      byte b = 0;
      while (true) {
        if (b < j) {
          EnumFacing side = arrayOfEnumFacing[b];
          BlockPos neighbour = eastBlockPos.offset(side);
          if (hasNeighbour(neighbour)) {
            eastBlockPos = neighbour;
            break;
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
    if (!hasNeighbour(westBlockPos)) {
      EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
      int j = arrayOfEnumFacing.length;
      byte b = 0;
      while (true) {
        if (b < j) {
          EnumFacing side = arrayOfEnumFacing[b];
          BlockPos neighbour = westBlockPos.offset(side);
          if (hasNeighbour(neighbour)) {
            westBlockPos = neighbour;
            break;
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
    if (mc.world.getBlockState(northBlockPos).getMaterial().isReplaceable() && isEntitiesEmpty(northBlockPos))
      placeBlockScaffold(northBlockPos, this.rotate.getValBoolean()); 
    if (mc.world.getBlockState(southBlockPos).getMaterial().isReplaceable() && isEntitiesEmpty(southBlockPos))
      placeBlockScaffold(southBlockPos, this.rotate.getValBoolean()); 
    if (mc.world.getBlockState(eastBlockPos).getMaterial().isReplaceable() && isEntitiesEmpty(eastBlockPos))
      placeBlockScaffold(eastBlockPos, this.rotate.getValBoolean()); 
    if (mc.world.getBlockState(westBlockPos).getMaterial().isReplaceable() && isEntitiesEmpty(westBlockPos))
      placeBlockScaffold(westBlockPos, this.rotate.getValBoolean()); 
    mc.player.inventory.currentItem = oldSlot;
  }
  
  private boolean isEntitiesEmpty(BlockPos pos) {
    List<Entity> entities = (List<Entity>)mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos)).stream().filter(e -> !(e instanceof net.minecraft.entity.item.EntityItem)).filter(e -> !(e instanceof net.minecraft.entity.item.EntityXPOrb)).collect(Collectors.toList());
    return entities.isEmpty();
  }
  
  public static boolean placeBlockScaffold(BlockPos pos, boolean rotate) {
    Vec3d eyesPos = new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
    EnumFacing[] arrayOfEnumFacing;
    int i;
    byte b;
    for (arrayOfEnumFacing = EnumFacing.values(), i = arrayOfEnumFacing.length, b = 0; b < i; ) {
      EnumFacing side = arrayOfEnumFacing[b];
      BlockPos neighbor = pos.offset(side);
      EnumFacing side2 = side.getOpposite();
      if (!canBeClicked(neighbor)) {
        b++;
        continue;
      } 
      Vec3d hitVec = (new Vec3d((Vec3i)neighbor)).add(0.5D, 0.5D, 0.5D).add((new Vec3d(side2.getDirectionVec())).scale(0.5D));
      if (rotate)
        faceVectorPacketInstant(hitVec); 
      mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SNEAKING));
      processRightClickBlock(neighbor, side2, hitVec);
      mc.player.swingArm(EnumHand.MAIN_HAND);
      mc.rightClickDelayTimer = 0;
      mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
      return true;
    } 
    return false;
  }
  
  private static PlayerControllerMP getPlayerController() {
    return mc.playerController;
  }
  
  public static void processRightClickBlock(BlockPos pos, EnumFacing side, Vec3d hitVec) {
    getPlayerController().processRightClickBlock(mc.player, mc.world, pos, side, hitVec, EnumHand.MAIN_HAND);
  }
  
  public static IBlockState getState(BlockPos pos) {
    return mc.world.getBlockState(pos);
  }
  
  public static Block getBlock(BlockPos pos) {
    return getState(pos).getBlock();
  }
  
  public static boolean canBeClicked(BlockPos pos) {
    return getBlock(pos).canCollideCheck(getState(pos), false);
  }
  
  public static void faceVectorPacketInstant(Vec3d vec) {
    float[] rotations = getNeededRotations2(vec);
    mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], mc.player.onGround));
  }
  
  private static float[] getNeededRotations2(Vec3d vec) {
    Vec3d eyesPos = getEyesPos();
    double diffX = vec.x - eyesPos.x;
    double diffY = vec.y - eyesPos.y;
    double diffZ = vec.z - eyesPos.z;
    double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
    float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
    float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));
    return new float[] { mc.player.rotationYaw + 
        
        MathHelper.wrapDegrees(yaw - mc.player.rotationYaw), mc.player.rotationPitch + 
        
        MathHelper.wrapDegrees(pitch - mc.player.rotationPitch) };
  }
  
  public static Vec3d getEyesPos() {
    return new Vec3d(mc.player.posX, mc.player.posY + mc.player
        .getEyeHeight(), mc.player.posZ);
  }
  
  public static Vec3d getInterpolatedPos(Entity entity, float ticks) {
    return (new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ)).add(getInterpolatedAmount(entity, ticks));
  }
  
  public static Vec3d getInterpolatedAmount(Entity entity, double ticks) {
    return getInterpolatedAmount(entity, ticks, ticks, ticks);
  }
  
  public static Vec3d getInterpolatedAmount(Entity entity, double x, double y, double z) {
    return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
  }
}
