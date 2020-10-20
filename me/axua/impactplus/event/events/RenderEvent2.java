//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.Vec3d;

public class RenderEvent2 extends ImpactPlusEvent {
  private final Tessellator tessellator;
  
  private final Vec3d renderPos;
  
  public RenderEvent2(Tessellator tessellator, Vec3d renderPos) {
    this.tessellator = tessellator;
    this.renderPos = renderPos;
  }
  
  public Tessellator getTessellator() {
    return this.tessellator;
  }
  
  public BufferBuilder getBuffer() {
    return this.tessellator.getBuffer();
  }
  
  public Vec3d getRenderPos() {
    return this.renderPos;
  }
  
  public void setTranslation(Vec3d translation) {
    getBuffer().setTranslation(-translation.x, -translation.y, -translation.z);
  }
  
  public void resetTranslation() {
    setTranslation(this.renderPos);
  }
}
