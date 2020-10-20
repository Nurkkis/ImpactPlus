//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.combat;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class FastBow extends Module {
  public FastBow() {
    super("FastBow", Module.Category.COMBAT, "FastBow");
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
  
  public void onUpdate() {
    if (mc.player.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemBow && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3) {
      mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
      mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
      mc.player.stopActiveHand();
    } 
  }
}
