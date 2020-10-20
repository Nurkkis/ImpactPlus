//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class BlockInteractionHelper {
  public static final List<Block> blackList = Arrays.asList(new Block[] { 
        Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, 
        Blocks.ENCHANTING_TABLE });
  
  public static final List<Block> shulkerList = Arrays.asList(new Block[] { 
        Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, 
        Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX });
  
  private static final Minecraft mc = Minecraft.getMinecraft();
  
  public static void placeBlockScaffold(BlockPos pos) {
    Vec3d eyesPos = new Vec3d((Minecraft.getMinecraft()).player.posX, (Minecraft.getMinecraft()).player.posY + (Minecraft.getMinecraft()).player.getEyeHeight(), (Minecraft.getMinecraft()).player.posZ);
    for (EnumFacing side : EnumFacing.values()) {
      BlockPos neighbor = pos.offset(side);
      EnumFacing side2 = side.getOpposite();
      if (canBeClicked(neighbor)) {
        Vec3d hitVec = (new Vec3d((Vec3i)neighbor)).add(0.5D, 0.5D, 0.5D).add((new Vec3d(side2.getDirectionVec())).scale(0.5D));
        if (eyesPos.squareDistanceTo(hitVec) <= 18.0625D) {
          faceVectorPacketInstant(hitVec);
          processRightClickBlock(neighbor, side2, hitVec);
          (Minecraft.getMinecraft()).player.swingArm(EnumHand.MAIN_HAND);
          mc.rightClickDelayTimer = 4;
          return;
        } 
      } 
    } 
  }
  
  public static float[] getLegitRotations(Vec3d vec) {
    Vec3d eyesPos = getEyesPos();
    double diffX = vec.x - eyesPos.x;
    double diffY = vec.y - eyesPos.y;
    double diffZ = vec.z - eyesPos.z;
    double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
    float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
    float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));
    return new float[] { (Minecraft.getMinecraft()).player.rotationYaw + MathHelper.wrapDegrees(yaw - (Minecraft.getMinecraft()).player.rotationYaw), 
        (Minecraft.getMinecraft()).player.rotationPitch + MathHelper.wrapDegrees(pitch - (Minecraft.getMinecraft()).player.rotationPitch) };
  }
  
  private static Vec3d getEyesPos() {
    return new Vec3d((Minecraft.getMinecraft()).player.posX, (Minecraft.getMinecraft()).player.posY + (Minecraft.getMinecraft()).player.getEyeHeight(), (Minecraft.getMinecraft()).player.posZ);
  }
  
  public static void faceVectorPacketInstant(Vec3d vec) {
    float[] rotations = getLegitRotations(vec);
    (Minecraft.getMinecraft()).player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], (Minecraft.getMinecraft()).player.onGround));
  }
  
  private static void processRightClickBlock(BlockPos pos, EnumFacing side, Vec3d hitVec) {
    getPlayerController().processRightClickBlock((Minecraft.getMinecraft()).player, mc.world, pos, side, hitVec, EnumHand.MAIN_HAND);
  }
  
  public static boolean canBeClicked(BlockPos pos) {
    return getBlock(pos).canCollideCheck(getState(pos), false);
  }
  
  private static Block getBlock(BlockPos pos) {
    return getState(pos).getBlock();
  }
  
  private static PlayerControllerMP getPlayerController() {
    return (Minecraft.getMinecraft()).playerController;
  }
  
  private static IBlockState getState(BlockPos pos) {
    return (Minecraft.getMinecraft()).world.getBlockState(pos);
  }
  
  public static boolean hasNeighbour(BlockPos blockPos) {
    for (EnumFacing side : EnumFacing.values()) {
      BlockPos neighbour = blockPos.offset(side);
      if (!(Minecraft.getMinecraft()).world.getBlockState(neighbour).getMaterial().isReplaceable())
        return true; 
    } 
    return false;
  }
  
  public static List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
    List<BlockPos> circleblocks = new ArrayList<>();
    int cx = loc.getX();
    int cy = loc.getY();
    int cz = loc.getZ();
    for (int x = cx - (int)r; x <= cx + r; x++) {
      for (int z = cz - (int)r; z <= cz + r; ) {
        int y = sphere ? (cy - (int)r) : cy;
        for (;; z++) {
          if (y < (sphere ? (cy + r) : (cy + h))) {
            double dist = ((cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0));
            if (dist < (r * r) && (!hollow || dist >= ((r - 1.0F) * (r - 1.0F))))
              circleblocks.add(new BlockPos(x, y + plus_y, z)); 
            y++;
            continue;
          } 
        } 
      } 
    } 
    return circleblocks;
  }
  
  public enum ValidResult {
    NoEntityCollision, AlreadyBlockThere, NoNeighbors, Ok;
  }
  
  public enum PlaceResult {
    NotReplaceable, Neighbors, CantPlace, Placed;
  }
  
  public static boolean IsLiquidOrAir(BlockPos p_Pos) {
    IBlockState l_State = mc.world.getBlockState(p_Pos);
    return (l_State.getBlock() == Blocks.WATER || l_State.getBlock() == Blocks.LAVA || l_State.getBlock() == Blocks.AIR);
  }
  
  public static PlaceResult place(BlockPos pos, float p_Distance, boolean p_Rotate, boolean p_UseSlabRule) {
    IBlockState l_State = mc.world.getBlockState(pos);
    boolean l_Replaceable = l_State.getMaterial().isReplaceable();
    boolean l_IsSlabAtBlock = l_State.getBlock() instanceof net.minecraft.block.BlockSlab;
    if (!l_Replaceable && !l_IsSlabAtBlock)
      return PlaceResult.NotReplaceable; 
    if (p_UseSlabRule)
      if (l_IsSlabAtBlock && !l_State.isFullCube())
        return PlaceResult.CantPlace;  
    Vec3d eyesPos = new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
    for (EnumFacing side : EnumFacing.values()) {
      BlockPos neighbor = pos.offset(side);
      EnumFacing side2 = side.getOpposite();
      boolean l_IsWater = (mc.world.getBlockState(neighbor).getBlock() == Blocks.WATER);
      if (mc.world.getBlockState(neighbor).getBlock().canCollideCheck(mc.world.getBlockState(neighbor), false)) {
        Vec3d hitVec = (new Vec3d((Vec3i)neighbor)).add(0.5D, 0.5D, 0.5D).add((new Vec3d(side2.getDirectionVec())).scale(0.5D));
        if (eyesPos.distanceTo(hitVec) <= p_Distance) {
          Block neighborPos = mc.world.getBlockState(neighbor).getBlock();
          boolean activated = neighborPos.onBlockActivated((World)mc.world, pos, mc.world.getBlockState(pos), (EntityPlayer)mc.player, EnumHand.MAIN_HAND, side, 0.0F, 0.0F, 0.0F);
          if (blackList.contains(neighborPos) || shulkerList.contains(neighborPos) || activated)
            mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SNEAKING)); 
          if (p_Rotate)
            faceVectorPacketInstant(hitVec); 
          EnumActionResult l_Result2 = mc.playerController.processRightClickBlock(mc.player, mc.world, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
          if (l_Result2 != EnumActionResult.FAIL) {
            mc.player.swingArm(EnumHand.MAIN_HAND);
            if (activated)
              mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SNEAKING)); 
            return PlaceResult.Placed;
          } 
        } 
      } 
    } 
    return PlaceResult.CantPlace;
  }
}
