//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.RenderEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.util.ImpactPlusTessellator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class HoleESP extends Module {
  Setting rainbow;
  
  Setting render;
  
  Setting rendermode;
  
  Setting r1;
  
  Setting g1;
  
  Setting b1;
  
  Setting r2;
  
  Setting g2;
  
  Setting b2;
  
  Setting a;
  
  Setting brainbow;
  
  Setting renderdistance;
  
  private final BlockPos[] surroundOffset;
  
  private ConcurrentHashMap<BlockPos, Boolean> safeHoles;
  
  public HoleESP() {
    super("HoleESP", Module.Category.RENDER, "Shows safe holes");
    this.surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
  }
  
  public void setup() {
    ArrayList<String> rendermodes = new ArrayList<>();
    rendermodes.add("Down");
    rendermodes.add("Block");
    ArrayList<String> renders = new ArrayList<>();
    renders.add("Bedrock");
    renders.add("Obby");
    renders.add("Both");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.renderdistance = new Setting("Render Distance", this, 7.0D, 1.0D, 15.0D, true, "HoleEspRenderDistance"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.a = new Setting("Alpha", this, 50.0D, 1.0D, 255.0D, true, "HoleEspAlpha"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.r1 = new Setting("Red (Obby)", this, 255.0D, 1.0D, 255.0D, true, "HoleEspRedObby"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.g1 = new Setting("Green (Obby)", this, 0.0D, 1.0D, 255.0D, true, "HoleEspGreenObby"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.b1 = new Setting("Blue (Obby)", this, 0.0D, 1.0D, 255.0D, true, "HoleEspBlueObby"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow (Obby)", this, false, "HoleEspRainbowObby"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.r2 = new Setting("Red (Bedrock)", this, 0.0D, 1.0D, 255.0D, true, "HoleEspRedBedrock"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.g2 = new Setting("Green (Bedrock)", this, 255.0D, 1.0D, 255.0D, true, "HoleEspGreenBedrock"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.b2 = new Setting("Blue (Bedrock)", this, 0.0D, 1.0D, 255.0D, true, "HoleEspBlueBedrock"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.brainbow = new Setting("Rainbow (Bedrock)", this, false, "HoleEspRainbowBedrock"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rendermode = new Setting("Render Mode", this, "Block", rendermodes, "HoleEspRenderMode"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.render = new Setting("Render", this, "Both", renders, "HoleEspRender"));
  }
  
  public void onUpdate() {
    if (this.safeHoles == null) {
      this.safeHoles = new ConcurrentHashMap<>();
    } else {
      this.safeHoles.clear();
    } 
    int range = (int)Math.ceil(this.renderdistance.getValDouble());
    List<BlockPos> blockPosList = getSphere(getPlayerPos(), range, range, false, true, 0);
    for (BlockPos pos : blockPosList) {
      if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR))
        continue; 
      if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR))
        continue; 
      if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR))
        continue; 
      boolean isSafe = true;
      boolean isBedrock = true;
      for (BlockPos offset : this.surroundOffset) {
        Block block = mc.world.getBlockState(pos.add((Vec3i)offset)).getBlock();
        if (block != Blocks.BEDROCK)
          isBedrock = false; 
        if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
          isSafe = false;
          break;
        } 
      } 
      if (isSafe)
        this.safeHoles.put(pos, Boolean.valueOf(isBedrock)); 
    } 
  }
  
  public void onWorldRender(RenderEvent event) {
    if (mc.player == null || this.safeHoles == null)
      return; 
    if (this.safeHoles.isEmpty())
      return; 
    ImpactPlusTessellator.prepare(7);
    this.safeHoles.forEach((blockPos, isBedrock) -> {
          if (this.render.getValString().equals("Both")) {
            if (isBedrock.booleanValue()) {
              drawBox(blockPos, this.r2.getValInt(), this.g2.getValInt(), this.b2.getValInt());
            } else {
              drawBox(blockPos, this.r1.getValInt(), this.g1.getValInt(), this.b1.getValInt());
            } 
          } else if (this.render.getValString().equals("Obby")) {
            if (!isBedrock.booleanValue())
              drawBox(blockPos, this.r1.getValInt(), this.g1.getValInt(), this.b1.getValInt()); 
          } else if (this.render.getValString().equals("Bedrock") && isBedrock.booleanValue()) {
            drawBox(blockPos, this.r2.getValInt(), this.g2.getValInt(), this.b2.getValInt());
          } 
        });
    ImpactPlusTessellator.release();
  }
  
  private void drawBox(BlockPos blockPos, int r, int g, int b) {
    Color color = new Color(r, g, b, this.a.getValInt());
    if (this.rendermode.getValString().equals("Down")) {
      ImpactPlusTessellator.drawBox(blockPos, color.getRGB(), 1);
    } else if (this.rendermode.getValString().equals("Block")) {
      ImpactPlusTessellator.drawBox(blockPos, color.getRGB(), 63);
    } 
  }
  
  public List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
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
            if (dist < (r * r) && (!hollow || dist >= ((r - 1.0F) * (r - 1.0F)))) {
              BlockPos l = new BlockPos(x, y + plus_y, z);
              circleblocks.add(l);
            } 
            y++;
            continue;
          } 
        } 
      } 
    } 
    return circleblocks;
  }
  
  public static BlockPos getPlayerPos() {
    return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
  }
}
