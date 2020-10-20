//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.player;

import java.util.ArrayList;
import java.util.Iterator;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.ImpactPlusEvent;
import me.axua.impactplus.event.events.EventPlayerMotionUpdate;
import me.axua.impactplus.event.events.EventRenderLayers;
import me.axua.impactplus.event.events.RenderEvent2;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.util.BlockInteractionHelper;
import me.axua.impactplus.util.MathUtil;
import me.axua.impactplus.util.Pair;
import me.axua.impactplus.util.PlayerUtil;
import me.axua.impactplus.util.RenderUtil;
import me.axua.impactplus.util.Timer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class AutoBuilder extends Module {
  public Setting Mode;
  
  public Setting Rotate;
  
  public Setting BlocksPerTick;
  
  public Setting Delay;
  
  private Vec3d Center;
  
  private ICamera camera;
  
  private Timer timer;
  
  private float PitchHead;
  
  private boolean SentPacket;
  
  ArrayList<BlockPos> l_Array;
  
  @EventHandler
  private Listener<EventRenderLayers> OnRender;
  
  @EventHandler
  private Listener<EventPlayerMotionUpdate> OnPlayerUpdate;
  
  @EventHandler
  private Listener<RenderEvent2> OnRenderEvent;
  
  public AutoBuilder() {
    super("AutoBuilder", Module.Category.PLAYER);
    this.Center = Vec3d.ZERO;
    this.camera = (ICamera)new Frustum();
    this.timer = new Timer();
    this.PitchHead = 0.0F;
    this.SentPacket = false;
    this.l_Array = new ArrayList<>();
    this.OnRender = new Listener(p_Event -> {
          if (p_Event.getEntityLivingBase() == mc.player)
            p_Event.SetHeadPitch((this.PitchHead == -420.0F) ? mc.player.rotationPitch : this.PitchHead); 
        }new java.util.function.Predicate[0]);
    this.OnPlayerUpdate = new Listener(p_Event -> {
          if (p_Event.getEra() != ImpactPlusEvent.Era.PRE)
            return; 
          if (!this.timer.passed(this.Delay.getValDouble() * 1000.0D))
            return; 
          this.timer.reset();
          Vec3d pos = MathUtil.interpolateEntity((Entity)mc.player, mc.getRenderPartialTicks());
          BlockPos orignPos = new BlockPos(pos.x, pos.y + 0.5D, pos.z);
          BlockPos interpPos = (new BlockPos(pos.x, pos.y, pos.z)).north().north();
          this.l_Array.clear();
          Pair<Integer, Block> l_Pair = findStackHotbar();
          int slot = -1;
          double l_Offset = pos.y - orignPos.getY();
          if (l_Pair != null) {
            slot = ((Integer)l_Pair.getFirst()).intValue();
            if (l_Pair.getSecond() instanceof net.minecraft.block.BlockSlab)
              if (l_Offset == 0.5D) {
                orignPos = new BlockPos(pos.x, pos.y + 0.5D, pos.z);
                interpPos = (new BlockPos(pos.x, pos.y + 1.0D, pos.z)).north().north();
              }  
          } 
          if (this.Mode.getValString().equals("Wall")) {
            this.l_Array.add(orignPos.north().north().east().east());
            this.l_Array.add(orignPos.north().north().east());
            this.l_Array.add(orignPos.north().north());
            this.l_Array.add(orignPos.north().north().west());
            this.l_Array.add(orignPos.north().north().west().west());
            this.l_Array.add(orignPos.north().north().west().west().west());
            this.l_Array.add(orignPos.up().north().north().east().east());
            this.l_Array.add(orignPos.up().north().north().east());
            this.l_Array.add(orignPos.up().north().north());
            this.l_Array.add(orignPos.up().north().north().west());
            this.l_Array.add(orignPos.up().north().north().west().west());
            this.l_Array.add(orignPos.up().north().north().west().west().west());
            this.l_Array.add(orignPos.up().up().north().north().east().east());
            this.l_Array.add(orignPos.up().up().north().north().east());
            this.l_Array.add(orignPos.up().up().north().north());
            this.l_Array.add(orignPos.up().up().north().north().west());
            this.l_Array.add(orignPos.up().up().north().north().west().west());
            this.l_Array.add(orignPos.up().up().north().north().west().west().west());
          } 
          if (this.Mode.getValString().equals("Highway")) {
            this.l_Array.add(orignPos.down());
            this.l_Array.add(orignPos.down().north());
            this.l_Array.add(orignPos.down().north().east());
            this.l_Array.add(orignPos.down().north().west());
            this.l_Array.add(orignPos.down().north().east().east());
            this.l_Array.add(orignPos.down().north().west().west());
            this.l_Array.add(orignPos.down().north().east().east().east());
            this.l_Array.add(orignPos.down().north().west().west().west());
            this.l_Array.add(orignPos.down().north().east().east().east().up());
            this.l_Array.add(orignPos.down().north().west().west().west().up());
          } 
          if (this.Mode.getValString().equals("HighwayTunnel")) {
            this.l_Array.add(orignPos.down());
            this.l_Array.add(orignPos.down().north());
            this.l_Array.add(orignPos.down().north().east());
            this.l_Array.add(orignPos.down().north().west());
            this.l_Array.add(orignPos.down().north().east().east());
            this.l_Array.add(orignPos.down().north().west().west());
            this.l_Array.add(orignPos.down().north().east().east().east());
            this.l_Array.add(orignPos.down().north().west().west().west());
            this.l_Array.add(orignPos.down().north().east().east().east().up());
            this.l_Array.add(orignPos.down().north().west().west().west().up());
            this.l_Array.add(orignPos.down().north().east().east().east().up().up());
            this.l_Array.add(orignPos.down().north().west().west().west().up().up());
            this.l_Array.add(orignPos.down().north().east().east().east().up().up().up());
            this.l_Array.add(orignPos.down().north().west().west().west().up().up().up());
            this.l_Array.add(orignPos.down().north().east().east().east().up().up().up().up());
            this.l_Array.add(orignPos.down().north().west().west().west().up().up().up().up());
            this.l_Array.add(orignPos.down().north().east().east().east().up().up().up().up().west());
            this.l_Array.add(orignPos.down().north().west().west().west().up().up().up().up().east());
            this.l_Array.add(orignPos.down().north().east().east().east().up().up().up().up().west().west());
            this.l_Array.add(orignPos.down().north().west().west().west().up().up().up().up().east().east());
            this.l_Array.add(orignPos.down().north().east().east().east().up().up().up().up().west().west().west());
            this.l_Array.add(orignPos.down().north().west().west().west().up().up().up().up().east().east().east());
          } 
          if (this.Mode.getValString().equals("Swastika")) {
            this.l_Array.add(interpPos);
            this.l_Array.add(interpPos.west());
            this.l_Array.add(interpPos.west().west());
            this.l_Array.add(interpPos.up());
            this.l_Array.add(interpPos.up().up());
            this.l_Array.add(interpPos.up().up().west());
            this.l_Array.add(interpPos.up().up().west().west());
            this.l_Array.add(interpPos.up().up().west().west().up());
            this.l_Array.add(interpPos.up().up().west().west().up().up());
            this.l_Array.add(interpPos.up().up().east());
            this.l_Array.add(interpPos.up().up().east().east());
            this.l_Array.add(interpPos.up().up().east().east().down());
            this.l_Array.add(interpPos.up().up().east().east().down().down());
            this.l_Array.add(interpPos.up().up().up());
            this.l_Array.add(interpPos.up().up().up().up());
            this.l_Array.add(interpPos.up().up().up().up().east());
            this.l_Array.add(interpPos.up().up().up().up().east().east());
          } 
          if (this.Mode.getValString().equals("Portal")) {
            this.l_Array.add(interpPos.east());
            this.l_Array.add(interpPos.east().east());
            this.l_Array.add(interpPos);
            this.l_Array.add(interpPos.east().east().up());
            this.l_Array.add(interpPos.east().east().up().up());
            this.l_Array.add(interpPos.east().east().up().up().up());
            this.l_Array.add(interpPos.east().east().up().up().up().up());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west().west());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west().west().west());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west().west().west().down());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west().west().west().down().down());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west().west().west().down().down().down());
            this.l_Array.add(interpPos.east().east().up().up().up().up().west().west().west().down().down().down().down());
          } 
          if (this.Mode.getValString().equals("Flat"))
            for (int l_X = -3; l_X < 3; l_X++) {
              for (int l_Y = -3; l_Y < 3; l_Y++)
                this.l_Array.add(orignPos.down().add(l_X, 0, l_Y)); 
            }  
          boolean l_NeedPlace = false;
          float[] rotations = null;
          if (slot != -1)
            if (mc.player.onGround) {
              int lastSlot = mc.player.inventory.currentItem;
              mc.player.inventory.currentItem = slot;
              mc.playerController.updateController();
              int l_BlocksPerTick = (int)this.BlocksPerTick.getValDouble();
              for (BlockPos l_Pos : this.l_Array) {
                BlockInteractionHelper.PlaceResult l_Place = BlockInteractionHelper.place(l_Pos, 5.0F, false, (l_Offset == -0.5D));
                if (l_Place != BlockInteractionHelper.PlaceResult.Placed)
                  continue; 
                l_NeedPlace = true;
                rotations = BlockInteractionHelper.getLegitRotations(new Vec3d(l_Pos.getX(), l_Pos.getY(), l_Pos.getZ()));
                if (--l_BlocksPerTick <= 0)
                  break; 
              } 
              if (!slotEqualsBlock(lastSlot, (Block)l_Pair.getSecond()))
                mc.player.inventory.currentItem = lastSlot; 
              mc.playerController.updateController();
            }  
          if (!l_NeedPlace && this.Mode.getValString().equals("Portal")) {
            if (mc.world.getBlockState(((BlockPos)this.l_Array.get(0)).up()).getBlock() == Blocks.PORTAL)
              return; 
            for (int l_I = 0; l_I < 9; l_I++) {
              ItemStack l_Stack = mc.player.inventory.getStackInSlot(l_I);
              if (!l_Stack.isEmpty())
                if (l_Stack.getItem() == Items.FLINT_AND_STEEL) {
                  mc.player.inventory.currentItem = l_I;
                  mc.playerController.updateController();
                  break;
                }  
            } 
            if (this.SentPacket)
              mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.l_Array.get(0), EnumFacing.UP, EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F)); 
            rotations = BlockInteractionHelper.getLegitRotations(new Vec3d(((BlockPos)this.l_Array.get(0)).getX(), ((BlockPos)this.l_Array.get(0)).getY(), ((BlockPos)this.l_Array.get(0)).getZ()));
            l_NeedPlace = true;
          } 
          if (!this.Rotate.getValBoolean() || !l_NeedPlace || rotations == null) {
            this.PitchHead = -420.0F;
            this.SentPacket = false;
            return;
          } 
          p_Event.cancel();
          boolean l_IsSprinting = mc.player.isSprinting();
          if (l_IsSprinting != mc.player.serverSprintState) {
            if (l_IsSprinting) {
              mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SPRINTING));
            } else {
              mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            } 
            mc.player.serverSprintState = l_IsSprinting;
          } 
          boolean l_IsSneaking = mc.player.isSneaking();
          if (l_IsSneaking != mc.player.serverSneakState) {
            if (l_IsSneaking) {
              mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SNEAKING));
            } else {
              mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            } 
            mc.player.serverSneakState = l_IsSneaking;
          } 
          if (PlayerUtil.isCurrentViewEntity()) {
            float l_Pitch = rotations[1];
            float l_Yaw = rotations[0];
            mc.player.rotationYawHead = l_Yaw;
            this.PitchHead = l_Pitch;
            AxisAlignedBB axisalignedbb = mc.player.getEntityBoundingBox();
            double l_PosXDifference = mc.player.posX - mc.player.lastReportedPosX;
            double l_PosYDifference = axisalignedbb.minY - mc.player.lastReportedPosY;
            double l_PosZDifference = mc.player.posZ - mc.player.lastReportedPosZ;
            double l_YawDifference = (l_Yaw - mc.player.lastReportedYaw);
            double l_RotationDifference = (l_Pitch - mc.player.lastReportedPitch);
            mc.player.positionUpdateTicks++;
            boolean l_MovedXYZ = (l_PosXDifference * l_PosXDifference + l_PosYDifference * l_PosYDifference + l_PosZDifference * l_PosZDifference > 9.0E-4D || mc.player.positionUpdateTicks >= 20);
            boolean l_MovedRotation = (l_YawDifference != 0.0D || l_RotationDifference != 0.0D);
            if (mc.player.isRiding()) {
              mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(mc.player.motionX, -999.0D, mc.player.motionZ, l_Yaw, l_Pitch, mc.player.onGround));
              l_MovedXYZ = false;
            } else if (l_MovedXYZ && l_MovedRotation) {
              mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(mc.player.posX, axisalignedbb.minY, mc.player.posZ, l_Yaw, l_Pitch, mc.player.onGround));
            } else if (l_MovedXYZ) {
              mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(mc.player.posX, axisalignedbb.minY, mc.player.posZ, mc.player.onGround));
            } else if (l_MovedRotation) {
              mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(l_Yaw, l_Pitch, mc.player.onGround));
            } else if (mc.player.prevOnGround != mc.player.onGround) {
              mc.player.connection.sendPacket((Packet)new CPacketPlayer(mc.player.onGround));
            } 
            if (l_MovedXYZ) {
              mc.player.lastReportedPosX = mc.player.posX;
              mc.player.lastReportedPosY = axisalignedbb.minY;
              mc.player.lastReportedPosZ = mc.player.posZ;
              mc.player.positionUpdateTicks = 0;
            } 
            if (l_MovedRotation) {
              mc.player.lastReportedYaw = l_Yaw;
              mc.player.lastReportedPitch = l_Pitch;
            } 
            this.SentPacket = true;
            mc.player.prevOnGround = mc.player.onGround;
            mc.player.autoJumpEnabled = mc.player.mc.gameSettings.autoJump;
          } 
        }new java.util.function.Predicate[0]);
    this.OnRenderEvent = new Listener(p_Event -> {
          Iterator<BlockPos> l_Itr = this.l_Array.iterator();
          while (l_Itr.hasNext()) {
            BlockPos l_Pos = l_Itr.next();
            AxisAlignedBB bb = new AxisAlignedBB(l_Pos.getX() - (mc.getRenderManager()).viewerPosX, l_Pos.getY() - (mc.getRenderManager()).viewerPosY, l_Pos.getZ() - (mc.getRenderManager()).viewerPosZ, (l_Pos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (l_Pos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (l_Pos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);
            this.camera.setPosition((mc.getRenderViewEntity()).posX, (mc.getRenderViewEntity()).posY, (mc.getRenderViewEntity()).posZ);
            if (this.camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + (mc.getRenderManager()).viewerPosX, bb.minY + (mc.getRenderManager()).viewerPosY, bb.minZ + (mc.getRenderManager()).viewerPosZ, bb.maxX + (mc.getRenderManager()).viewerPosX, bb.maxY + (mc.getRenderManager()).viewerPosY, bb.maxZ + (mc.getRenderManager()).viewerPosZ))) {
              GlStateManager.pushMatrix();
              GlStateManager.enableBlend();
              GlStateManager.disableDepth();
              GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
              GlStateManager.disableTexture2D();
              GlStateManager.depthMask(false);
              GL11.glEnable(2848);
              GL11.glHint(3154, 4354);
              GL11.glLineWidth(1.5F);
              double dist = mc.player.getDistance((l_Pos.getX() + 0.5F), (l_Pos.getY() + 0.5F), (l_Pos.getZ() + 0.5F)) * 0.75D;
              float alpha = MathUtil.clamp((float)(dist * 255.0D / 5.0D / 255.0D), 0.0F, 0.3F);
              int l_Color = 268500991;
              RenderUtil.drawBoundingBox(bb, 1.0F, l_Color);
              RenderUtil.drawFilledBox(bb, l_Color);
              GL11.glDisable(2848);
              GlStateManager.depthMask(true);
              GlStateManager.enableDepth();
              GlStateManager.enableTexture2D();
              GlStateManager.disableBlend();
              GlStateManager.popMatrix();
            } 
          } 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    ArrayList<String> Modes = new ArrayList<>();
    Modes.add("Highway");
    Modes.add("HighwayTunnel");
    Modes.add("Portal");
    Modes.add("Flat");
    Modes.add("Wall");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.Mode = new Setting("Mode", this, "Highway", Modes, "AutoBuilderMode"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.Rotate = new Setting("Rotate", this, true, "AutoBuilderRotate"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.BlocksPerTick = new Setting("BlocksPerTick", this, 1.0D, 1.0D, 10.0D, false, "AutoBuilderBlocksPerTick"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.Delay = new Setting("Delay", this, 0.0D, 0.0D, 1.0D, false, "AutoBuilderDelay"));
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
    super.onEnable();
    if (mc.player == null) {
      toggle();
      return;
    } 
    this.timer.reset();
  }
  
  public String getMetaData() {
    return this.Mode.getValString();
  }
  
  private boolean slotEqualsBlock(int slot, Block type) {
    if (mc.player.inventory.getStackInSlot(slot).getItem() instanceof ItemBlock) {
      ItemBlock block = (ItemBlock)mc.player.inventory.getStackInSlot(slot).getItem();
      return (block.getBlock() == type);
    } 
    return false;
  }
  
  private Pair<Integer, Block> findStackHotbar() {
    if (mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)
      return new Pair(Integer.valueOf(mc.player.inventory.currentItem), ((ItemBlock)mc.player.getHeldItemMainhand().getItem()).getBlock()); 
    for (int i = 0; i < 9; i++) {
      ItemStack stack = (Minecraft.getMinecraft()).player.inventory.getStackInSlot(i);
      if (stack.getItem() instanceof ItemBlock) {
        ItemBlock block = (ItemBlock)stack.getItem();
        return new Pair(Integer.valueOf(i), block.getBlock());
      } 
    } 
    return null;
  }
  
  public Vec3d GetCenter(double posX, double posY, double posZ) {
    double x = Math.floor(posX) + 0.5D;
    double y = Math.floor(posY);
    double z = Math.floor(posZ) + 0.5D;
    return new Vec3d(x, y, z);
  }
  
  protected void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
