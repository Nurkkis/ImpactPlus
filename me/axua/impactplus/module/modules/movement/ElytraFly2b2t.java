//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.movement;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.event.events.PlayerTravelEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.util.Wrapper;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.MathHelper;

public class ElytraFly2b2t extends Module {
  Setting hover;
  
  Setting AutoOpen;
  
  Setting speed;
  
  Setting downSpeed;
  
  Setting sinkSpeed;
  
  private double hoverTarget;
  
  private boolean hoverState;
  
  @EventHandler
  private Listener<PacketEvent.Send> sendListener;
  
  @EventHandler
  private Listener<PacketEvent.Receive> recvListener;
  
  @EventHandler
  private Listener<PlayerTravelEvent> playerTravelListener;
  
  public ElytraFly2b2t() {
    super("ElytraFly2b2t", Module.Category.MOVEMENT);
    this.sendListener = new Listener(event -> {
          if (mc.player == null)
            return; 
          if (event.getPacket() instanceof CPacketPlayer) {
            if (!mc.player.isElytraFlying())
              return; 
            CPacketPlayer packet = (CPacketPlayer)event.getPacket();
            packet.pitch = 0.0F;
            packet.yaw = this.packetYaw;
          } 
          if (event.getPacket() instanceof CPacketEntityAction) {
            CPacketEntityAction packet = (CPacketEntityAction)event.getPacket();
            if (packet.getAction() == CPacketEntityAction.Action.START_FALL_FLYING)
              this.hoverTarget = mc.player.posY + 0.35D; 
          } 
        }new java.util.function.Predicate[0]);
    this.recvListener = new Listener(event -> {
          if (mc.player == null || !mc.player.isElytraFlying())
            return; 
          if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
            packet.pitch = mc.player.rotationPitch;
          } 
        }new java.util.function.Predicate[0]);
    this.playerTravelListener = new Listener(event -> {
          if (mc.player == null)
            return; 
          if (!mc.player.isElytraFlying()) {
            if (this.AutoOpen.getValBoolean() && !mc.player.onGround && mc.player.motionY < -0.04D) {
              CPacketEntityAction packet = new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_FALL_FLYING);
              (Wrapper.getPlayer()).connection.sendPacket((Packet)packet);
              mc.timer.tickLength = 200.0F;
              event.cancel();
              return;
            } 
            return;
          } 
          mc.timer.tickLength = 50.0F;
          if (this.hoverTarget < 0.0D)
            this.hoverTarget = mc.player.posY; 
          boolean moveForward = mc.gameSettings.keyBindForward.isKeyDown();
          boolean moveBackward = mc.gameSettings.keyBindBack.isKeyDown();
          boolean moveLeft = mc.gameSettings.keyBindLeft.isKeyDown();
          boolean moveRight = mc.gameSettings.keyBindRight.isKeyDown();
          boolean moveUp = mc.gameSettings.keyBindJump.isKeyDown();
          boolean moveDown = mc.gameSettings.keyBindSneak.isKeyDown();
          float moveForwardFactor = moveForward ? 1.0F : -1.0F;
          float yawDeg = mc.player.rotationYaw;
          if (moveLeft && (moveForward || moveBackward)) {
            yawDeg -= 40.0F * moveForwardFactor;
          } else if (moveRight && (moveForward || moveBackward)) {
            yawDeg += 40.0F * moveForwardFactor;
          } else if (moveLeft) {
            yawDeg -= 90.0F;
          } else if (moveRight) {
            yawDeg += 90.0F;
          } 
          if (moveBackward)
            yawDeg -= 180.0F; 
          this.packetYaw = yawDeg;
          float yaw = (float)Math.toRadians(yawDeg);
          float pitch = (float)Math.toRadians(mc.player.rotationPitch);
          double d8 = Math.sqrt(mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ);
          if (this.hoverState) {
            this.hoverState = (mc.player.posY < this.hoverTarget + 0.1D);
          } else {
            this.hoverState = (mc.player.posY < this.hoverTarget + 0.0D);
          } 
          boolean doHover = (this.hoverState && this.hover.getValBoolean());
          if (moveUp || moveForward || moveBackward || moveLeft || moveRight || autoFly || ModuleManager.isModuleEnabled("AutoWalk")) {
            if ((moveUp || doHover || flyUp) && d8 > 1.0D) {
              if (mc.player.motionX == 0.0D && mc.player.motionZ == 0.0D) {
                mc.player.motionY = this.downSpeed.getValDouble();
              } else {
                double d6 = 1.0D;
                double d10 = d8 * 0.2D * 0.04D;
                mc.player.motionY += d10 * 3.2D;
                mc.player.motionX -= -MathHelper.sin(yaw) * d10 / d6;
                mc.player.motionZ -= MathHelper.cos(yaw) * d10 / d6;
                if (d6 > 0.0D) {
                  mc.player.motionX += (-MathHelper.sin(yaw) / d6 * d8 - mc.player.motionX) * 0.3D;
                  mc.player.motionZ += (MathHelper.cos(yaw) / d6 * d8 - mc.player.motionZ) * 0.3D;
                } 
                mc.player.motionX *= 0.9900000095367432D;
                mc.player.motionY *= 0.9800000190734863D;
                mc.player.motionZ *= 0.9900000095367432D;
              } 
            } else {
              mc.player.motionX = -MathHelper.sin(yaw) * this.speed.getValDouble();
              mc.player.motionY = -this.sinkSpeed.getValDouble();
              mc.player.motionZ = MathHelper.cos(yaw) * this.speed.getValDouble();
            } 
          } else {
            mc.player.motionX = 0.0D;
            mc.player.motionY = 0.0D;
            mc.player.motionZ = 0.0D;
          } 
          if (moveDown)
            mc.player.motionY = -this.downSpeed.getValDouble(); 
          if (moveUp || moveDown)
            this.hoverTarget = mc.player.posY; 
          event.cancel();
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.hover = new Setting("Hover", this, true, "ElytraFly2b2tHover"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.AutoOpen = new Setting("Auto Open", this, true, "ElytraFly2b2tAutoOpen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.speed = new Setting("Speed", this, 1.8D, 0.0D, 10.0D, false, "ElytraFly2b2tSpeed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.downSpeed = new Setting("Down Speed", this, 2.0D, 0.0D, 10.0D, false, "ElytraFly2b2tDownSpeed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.sinkSpeed = new Setting("Sink Speed", this, 0.001D, 0.0D, 10.0D, false, "ElytraFly2b2tSinkSpeed"));
    this.hoverTarget = -1.0D;
    this.hoverState = false;
    this.packetYaw = 0.0F;
  }
  
  public static boolean flyUp = false;
  
  public static boolean autoFly = false;
  
  public float packetYaw;
  
  protected void onEnable() {
    this.hoverTarget = -1.0D;
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  protected void onDisable() {
    mc.timer.tickLength = 50.0F;
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
