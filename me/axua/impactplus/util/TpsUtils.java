//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import java.util.Arrays;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.PacketEvent;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.math.MathHelper;

public class TpsUtils {
  private static float[] tickRates = new float[20];
  
  private int nextIndex = 0;
  
  private long timeLastTimeUpdate;
  
  @EventHandler
  Listener<PacketEvent.Receive> listener;
  
  public static float getTickRate() {
    float numTicks = 0.0F;
    float sumTickRates = 0.0F;
    for (float tickRate : tickRates) {
      if (tickRate > 0.0F) {
        sumTickRates += tickRate;
        numTicks++;
      } 
    } 
    return MathHelper.clamp(sumTickRates / numTicks, 0.0F, 20.0F);
  }
  
  private void onTimeUpdate() {
    if (this.timeLastTimeUpdate != -1L) {
      float timeElapsed = (float)(System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0F;
      tickRates[this.nextIndex % tickRates.length] = MathHelper.clamp(20.0F / timeElapsed, 0.0F, 20.0F);
      this.nextIndex++;
    } 
    this.timeLastTimeUpdate = System.currentTimeMillis();
  }
  
  public TpsUtils() {
    this.listener = new Listener(event -> {
          if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketTimeUpdate)
            onTimeUpdate(); 
        }new java.util.function.Predicate[0]);
    this.nextIndex = 0;
    this.timeLastTimeUpdate = -1L;
    Arrays.fill(tickRates, 0.0F);
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
}
