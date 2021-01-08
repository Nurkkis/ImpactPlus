//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.event;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.Minecraft;

public class ImpactPlusEvent extends Cancellable {
  private Era era = Era.PRE;
  
  private final float partialTicks;
  
  public ImpactPlusEvent() {
    this.partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
  }
  
  public Era getEra() {
    return this.era;
  }
  
  public float getPartialTicks() {
    return this.partialTicks;
  }
  
  public enum Era {
    PRE, PERI, POST;
  }
}
