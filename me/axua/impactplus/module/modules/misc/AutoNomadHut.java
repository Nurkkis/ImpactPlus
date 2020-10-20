//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.modules.combat.Surround;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class AutoNomadHut extends Module {
  private BlockPos basePos;
  
  private int offsetStep;
  
  private int playerHotbarSlot;
  
  private int lastHotbarSlot;
  
  Setting bpt;
  
  Vec3d[] surroundTargets;
  
  public AutoNomadHut() {
    super("AutoNomadHut", Module.Category.MISC, "Builds a nomad hut around you");
    this.surroundTargets = new Vec3d[] { 
        new Vec3d(0.0D, 0.0D, 0.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(1.0D, 0.0D, -1.0D), new Vec3d(-1.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, -1.0D), new Vec3d(2.0D, 0.0D, 0.0D), 
        new Vec3d(2.0D, 0.0D, 1.0D), new Vec3d(2.0D, 0.0D, -1.0D), new Vec3d(-2.0D, 0.0D, 0.0D), new Vec3d(-2.0D, 0.0D, 1.0D), new Vec3d(-2.0D, 0.0D, -1.0D), new Vec3d(0.0D, 0.0D, 2.0D), new Vec3d(1.0D, 0.0D, 2.0D), new Vec3d(-1.0D, 0.0D, 2.0D), new Vec3d(0.0D, 0.0D, -2.0D), new Vec3d(-1.0D, 0.0D, -2.0D), 
        new Vec3d(1.0D, 0.0D, -2.0D), new Vec3d(2.0D, 1.0D, -1.0D), new Vec3d(2.0D, 1.0D, 1.0D), new Vec3d(-2.0D, 1.0D, 0.0D), new Vec3d(-2.0D, 1.0D, 1.0D), new Vec3d(-2.0D, 1.0D, -1.0D), new Vec3d(0.0D, 1.0D, 2.0D), new Vec3d(1.0D, 1.0D, 2.0D), new Vec3d(-1.0D, 1.0D, 2.0D), new Vec3d(0.0D, 1.0D, -2.0D), 
        new Vec3d(1.0D, 1.0D, -2.0D), new Vec3d(-1.0D, 1.0D, -2.0D), new Vec3d(2.0D, 2.0D, -1.0D), new Vec3d(2.0D, 2.0D, 1.0D), new Vec3d(-2.0D, 2.0D, 1.0D), new Vec3d(-2.0D, 2.0D, -1.0D), new Vec3d(1.0D, 2.0D, 2.0D), new Vec3d(-1.0D, 2.0D, 2.0D), new Vec3d(1.0D, 2.0D, -2.0D), new Vec3d(-1.0D, 2.0D, -2.0D), 
        new Vec3d(2.0D, 3.0D, 0.0D), new Vec3d(2.0D, 3.0D, -1.0D), new Vec3d(2.0D, 3.0D, 1.0D), new Vec3d(-2.0D, 3.0D, 0.0D), new Vec3d(-2.0D, 3.0D, 1.0D), new Vec3d(-2.0D, 3.0D, -1.0D), new Vec3d(0.0D, 3.0D, 2.0D), new Vec3d(1.0D, 3.0D, 2.0D), new Vec3d(-1.0D, 3.0D, 2.0D), new Vec3d(0.0D, 3.0D, -2.0D), 
        new Vec3d(1.0D, 3.0D, -2.0D), new Vec3d(-1.0D, 3.0D, -2.0D), new Vec3d(0.0D, 4.0D, 0.0D), new Vec3d(1.0D, 4.0D, 0.0D), new Vec3d(-1.0D, 4.0D, 0.0D), new Vec3d(0.0D, 4.0D, 1.0D), new Vec3d(0.0D, 4.0D, -1.0D), new Vec3d(1.0D, 4.0D, 1.0D), new Vec3d(-1.0D, 4.0D, 1.0D), new Vec3d(-1.0D, 4.0D, -1.0D), 
        new Vec3d(1.0D, 4.0D, -1.0D), new Vec3d(2.0D, 4.0D, 0.0D), new Vec3d(2.0D, 4.0D, 1.0D), new Vec3d(2.0D, 4.0D, -1.0D) };
    this.offsetStep = 0;
    this.playerHotbarSlot = -1;
    this.lastHotbarSlot = -1;
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bpt = new Setting("BlocksPerTick", this, 8.0D, 1.0D, 20.0D, true, "AutoNomadHutBPT"));
  }
  
  public void onUpdate() {
    if (!isEnabled() || mc.player == null)
      return; 
    if (this.offsetStep == 0) {
      this.basePos = (new BlockPos(mc.player.getPositionVector())).down();
      this.playerHotbarSlot = mc.player.inventory.currentItem;
    } 
    for (int i = 0; i < (int)Math.floor(this.bpt.getValDouble()); i++) {
      if (this.offsetStep >= this.surroundTargets.length) {
        endLoop();
        return;
      } 
      Vec3d offset = this.surroundTargets[this.offsetStep];
      placeBlock(new BlockPos((Vec3i)this.basePos.add(offset.x, offset.y, offset.z)));
      this.offsetStep++;
    } 
  }
  
  protected void onEnable() {
    if (mc.player == null) {
      disable();
      return;
    } 
    this.playerHotbarSlot = mc.player.inventory.currentItem;
    this.lastHotbarSlot = -1;
  }
  
  protected void onDisable() {
    if (mc.player == null)
      return; 
    if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1)
      mc.player.inventory.currentItem = this.playerHotbarSlot; 
    this.playerHotbarSlot = -1;
    this.lastHotbarSlot = -1;
  }
  
  private void endLoop() {
    this.offsetStep = 0;
    if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
      mc.player.inventory.currentItem = this.playerHotbarSlot;
      this.lastHotbarSlot = this.playerHotbarSlot;
    } 
  }
  
  private void placeBlock(BlockPos blockPos) {
    if (!mc.world.getBlockState(blockPos).getMaterial().isReplaceable())
      return; 
    if (!Surround.hasNeighbour(blockPos))
      return; 
    placeBlockExecute(blockPos);
  }
  
  private int findObiInHotbar() {
    int slot = -1;
    for (int i = 0; i < 9; i++) {
      ItemStack stack = mc.player.inventory.getStackInSlot(i);
      if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
        Block block = ((ItemBlock)stack.getItem()).getBlock();
        if (block instanceof net.minecraft.block.BlockObsidian) {
          slot = i;
          break;
        } 
      } 
    } 
    return slot;
  }
  
  public void placeBlockExecute(BlockPos pos) {
    Vec3d eyesPos = new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
    for (EnumFacing side : EnumFacing.values()) {
      BlockPos neighbor = pos.offset(side);
      EnumFacing side2 = side.getOpposite();
      if (canBeClicked(neighbor)) {
        Vec3d hitVec = (new Vec3d((Vec3i)neighbor)).add(0.5D, 0.5D, 0.5D).add((new Vec3d(side2.getDirectionVec())).scale(0.5D));
        if (eyesPos.squareDistanceTo(hitVec) <= 18.0625D) {
          boolean needSneak = false;
          Block blockBelow = mc.world.getBlockState(neighbor).getBlock();
          needSneak = true;
          if (needSneak)
            mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SNEAKING)); 
          int obiSlot = findObiInHotbar();
          if (obiSlot == -1) {
            disable();
            return;
          } 
          if (this.lastHotbarSlot != obiSlot) {
            mc.player.inventory.currentItem = obiSlot;
            this.lastHotbarSlot = obiSlot;
          } 
          mc.playerController.processRightClickBlock(mc.player, mc.world, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
          mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
          if (needSneak)
            mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SNEAKING)); 
          return;
        } 
      } 
    } 
  }
  
  private static boolean canBeClicked(BlockPos pos) {
    return getBlock(pos).canCollideCheck(getState(pos), false);
  }
  
  private static Block getBlock(BlockPos pos) {
    return getState(pos).getBlock();
  }
  
  private static IBlockState getState(BlockPos pos) {
    return mc.world.getBlockState(pos);
  }
  
  private static void faceVectorPacketInstant(Vec3d vec) {
    float[] rotations = getLegitRotations(vec);
    mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], mc.player.onGround));
  }
  
  private static float[] getLegitRotations(Vec3d vec) {
    Vec3d eyesPos = getEyesPos();
    double diffX = vec.x - eyesPos.x;
    double diffY = vec.y - eyesPos.y;
    double diffZ = vec.z - eyesPos.z;
    double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
    float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
    float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));
    return new float[] { mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - mc.player.rotationYaw), mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - mc.player.rotationPitch) };
  }
  
  private static Vec3d getEyesPos() {
    return new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
  }
}
