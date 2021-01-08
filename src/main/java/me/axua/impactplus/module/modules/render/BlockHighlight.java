//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.RenderEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.util.ImpactPlusTessellator;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockHighlight extends Module {
  Setting r;
  
  Setting g;
  
  Setting b;
  
  Setting a;
  
  Setting w;
  
  Setting rainbow;
  
  public BlockHighlight() {
    super("BlockHighlight", Module.Category.RENDER, "Highlights the block you're looking at");
  }
  
  public void setup() {
    this.r = new Setting("Red", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlightRed");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.r);
    this.g = new Setting("Green", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlightGreen");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.g);
    this.b = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlightBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.b);
    this.a = new Setting("Alpha", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlightAlpha");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.a);
    this.w = new Setting("Width", this, 2.0D, 1.0D, 10.0D, true, "BlockHighlightWidth");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.w);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "BlockHighlightRainbow"));
  }
  
  public void onWorldRender(RenderEvent event) {
    Color c;
    RayTraceResult ray = mc.objectMouseOver;
    Color color = Rainbow.getColor();
    if (this.rainbow.getValBoolean()) {
      c = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.a.getValDouble());
    } else {
      c = new Color((int)this.r.getValDouble(), (int)this.g.getValDouble(), (int)this.b.getValDouble(), (int)this.a.getValDouble());
    } 
    if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
      BlockPos pos = ray.getBlockPos();
      AxisAlignedBB bb = mc.world.getBlockState(pos).getSelectedBoundingBox((World)mc.world, pos);
      if (bb != null && pos != null && mc.world.getBlockState(pos).getMaterial() != Material.AIR) {
        ImpactPlusTessellator.prepareGL();
        ImpactPlusTessellator.drawBoundingBox(bb, (int)this.w.getValDouble(), c.getRGB());
        ImpactPlusTessellator.releaseGL();
      } 
    } 
  }
}
