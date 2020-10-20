//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.player;

import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

public class AntiForceLook extends Module {
  @EventHandler
  Listener<PacketEvent.Receive> receiveListener;
  
  public AntiForceLook() {
    super("AntiForceLook", Module.Category.PLAYER, "Stops server packets from turning your head");
    this.receiveListener = new Listener(event -> {
          if (mc.player == null)
            return; 
          if (event.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
            packet.yaw = mc.player.rotationYaw;
            packet.pitch = mc.player.rotationPitch;
          } 
        }new java.util.function.Predicate[0]);
  }
}
