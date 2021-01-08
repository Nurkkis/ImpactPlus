//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.RenderEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.util.ImpactPlusTessellator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class BlockHighlight2 extends Module {
  Setting r;
  
  Setting g;
  
  Setting b;
  
  Setting a;
  
  Setting w;
  
  Setting a2;
  
  Setting boundingbox;
  
  Setting box;
  
  Setting rainbow;
  
  public BlockHighlight2() {
    super("BlockHighlight2", Module.Category.RENDER, "Highlights the block you're looking at");
  }
  
  public void setup() {
    this.box = new Setting("Box", this, true, "Box");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.box);
    this.boundingbox = new Setting("boundingbox", this, true, "boundingbox");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.boundingbox);
    this.r = new Setting("Red", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlight2Red");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.r);
    this.g = new Setting("Green", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlight2Green");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.g);
    this.b = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlight2Blue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.b);
    this.a = new Setting("BoundingBoxAlpha", this, 255.0D, 0.0D, 255.0D, true, "BlockHighlight2Alpha");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.a);
    this.a2 = new Setting("Alpha", this, 26.0D, 0.0D, 255.0D, true, "BlockHighlight2AlphaBounding");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.a2);
    this.w = new Setting("Width", this, 2.0D, 1.0D, 10.0D, true, "BlockHighlight2Width");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.w);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "BlockHighlight2Rainbow"));
  }
  
  public void onWorldRender(RenderEvent event) {
    float[] hue = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int rgb = Color.HSBtoRGB(hue[0], 1.0F, 1.0F);
    int r = rgb >> 16 & 0xFF;
    int g = rgb >> 8 & 0xFF;
    int b = rgb & 0xFF;
    Minecraft mc = Minecraft.getMinecraft();
    RayTraceResult ray = mc.objectMouseOver;
    if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
      BlockPos blockpos = ray.getBlockPos();
      IBlockState iblockstate = mc.world.getBlockState(blockpos);
      if (iblockstate.getMaterial() != Material.AIR && mc.world.getWorldBorder().contains(blockpos)) {
        if (this.box.getValBoolean()) {
          ImpactPlusTessellator.prepare(7);
          if (this.rainbow.getValBoolean()) {
            ImpactPlusTessellator.drawBox(blockpos, r, g, b, this.a2.getValInt(), 63);
          } else {
            ImpactPlusTessellator.drawBox(blockpos, this.r.getValInt(), this.g.getValInt(), this.b.getValInt(), this.a2.getValInt(), 63);
          } 
          ImpactPlusTessellator.release();
        } 
        if (this.boundingbox.getValBoolean()) {
          ImpactPlusTessellator.prepare(7);
          if (this.rainbow.getValBoolean()) {
            ImpactPlusTessellator.drawBoundingBoxBlockPos(blockpos, this.w.getValInt(), r, g, b, this.a.getValInt());
          } else {
            ImpactPlusTessellator.drawBoundingBoxBlockPos(blockpos, this.w.getValInt(), this.r.getValInt(), this.g.getValInt(), this.b.getValInt(), this.a.getValInt());
          } 
          ImpactPlusTessellator.release();
        } 
      } 
    } 
  }
}
