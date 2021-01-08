//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.CurrentHole;
import me.axua.impactplus.util.BlockUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HoleComponent extends Panel {
  CurrentHole mod;
  
  Color c;
  
  boolean font;
  
  Color color;
  
  boolean bedrock;
  
  boolean obby;
  
  boolean safe;
  
  public HoleComponent(double ix, double iy, ClickGUI parent) {
    super("Hole", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (CurrentHole)ModuleManager.getModuleByName("Hole");
    this.isHudComponent = true;
  }
  
  public void drawHud() {
    doStuff();
    if (this.mod.mode.getValString().equalsIgnoreCase("Texture")) {
      renderHole(this.x, this.y);
    } else if (this.mod.customFont.getValBoolean()) {
      ImpactPlus.CustomFont2.drawStringWithShadow(text(), (int)this.x, (int)this.y, this.color.getRGB());
    } else {
      mc.fontRenderer.drawStringWithShadow(text(), (int)this.x, (int)this.y, this.color.getRGB());
    } 
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    double w = (ImpactPlus.CustomFont2.getStringWidth(text()) + 2.0F);
    this.c = new Color(50, 50, 50, 100);
    if (this.isHudComponentPinned)
      this.c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100); 
    if (this.dragging) {
      this.x = this.x2 + mouseX;
      this.y = this.y2 + mouseY;
    } 
    if (this.mod.mode.getValString().equalsIgnoreCase("Texture")) {
      this.width = 48.0D;
    } else {
      this.width = w;
    } 
    if (this.mod.mode.getValString().equalsIgnoreCase("Texture")) {
      this.height = 48.0D;
    } else {
      this.height = 12.0D;
    } 
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, this.c.getRGB());
    if (!this.extended)
      ImpactPlus.CustomFont2.drawStringWithShadow(this.title, (int)this.x, ((int)(this.y + this.height / 2.0D - (FontUtil.getFontHeight() / 2.0F)) - 2), -1); 
    if (this.extended) {
      double startY = this.y + this.height;
      Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)this.y + (int)this.height, this.c.getRGB());
      Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)this.y + (int)this.height, this.c.getRGB());
      if (this.mod.mode.getValString().equalsIgnoreCase("Texture")) {
        renderHole(this.x, this.y);
      } else if (this.mod.customFont.getValBoolean()) {
        ImpactPlus.CustomFont2.drawStringWithShadow(text(), (int)this.x, (int)this.y, this.color.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(text(), (int)this.x, (int)this.y, this.color.getRGB());
      } 
    } 
  }
  
  private void doStuff() {
    this.color = this.safe ? Color.GREEN : Color.RED;
    this.bedrock = (northBrock() && eastBrock() && southBrock() && westBrock());
    this.obby = (!this.bedrock && (northObby() || northBrock()) && (eastObby() || eastBrock()) && (southObby() || southBrock()) && (westObby() || westBrock()));
    this.safe = (this.obby || this.bedrock);
  }
  
  private String text() {
    String text;
    if (this.mod.mode.getValString().equalsIgnoreCase("Safe/Unsafe")) {
      text = this.safe ? "Safe" : "Unsafe";
    } else {
      text = this.bedrock ? "Bedrock" : (this.obby ? "Obsidian" : "None");
    } 
    return ChatFormatting.GRAY + "Hole " + ChatFormatting.RESET + text;
  }
  
  private void renderHole(double x, double y) {
    double leftX = x;
    double leftY = y + 16.0D;
    double upX = x + 16.0D;
    double upY = y;
    double rightX = x + 32.0D;
    double rightY = y + 16.0D;
    double bottomX = x + 16.0D;
    double bottomY = y + 32.0D;
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    switch (mc.getRenderViewEntity().getHorizontalFacing()) {
      case NORTH:
        if (northObby() || northBrock())
          renderItem(upX, upY, new ItemStack(mc.world.getBlockState(playerPos.north()).getBlock())); 
        if (westObby() || westBrock())
          renderItem(leftX, leftY, new ItemStack(mc.world.getBlockState(playerPos.west()).getBlock())); 
        if (eastObby() || eastBrock())
          renderItem(rightX, rightY, new ItemStack(mc.world.getBlockState(playerPos.east()).getBlock())); 
        if (southObby() || southBrock())
          renderItem(bottomX, bottomY, new ItemStack(mc.world.getBlockState(playerPos.south()).getBlock())); 
        break;
      case SOUTH:
        if (southObby() || southBrock())
          renderItem(upX, upY, new ItemStack(mc.world.getBlockState(playerPos.south()).getBlock())); 
        if (eastObby() || eastBrock())
          renderItem(leftX, leftY, new ItemStack(mc.world.getBlockState(playerPos.east()).getBlock())); 
        if (westObby() || westBrock())
          renderItem(rightX, rightY, new ItemStack(mc.world.getBlockState(playerPos.west()).getBlock())); 
        if (northObby() || northBrock())
          renderItem(bottomX, bottomY, new ItemStack(mc.world.getBlockState(playerPos.north()).getBlock())); 
        break;
      case WEST:
        if (westObby() || westBrock())
          renderItem(upX, upY, new ItemStack(mc.world.getBlockState(playerPos.west()).getBlock())); 
        if (southObby() || southBrock())
          renderItem(leftX, leftY, new ItemStack(mc.world.getBlockState(playerPos.south()).getBlock())); 
        if (northObby() || northBrock())
          renderItem(rightX, rightY, new ItemStack(mc.world.getBlockState(playerPos.north()).getBlock())); 
        if (eastObby() || eastBrock())
          renderItem(bottomX, bottomY, new ItemStack(mc.world.getBlockState(playerPos.east()).getBlock())); 
        break;
      case EAST:
        if (eastObby() || eastBrock())
          renderItem(upX, upY, new ItemStack(mc.world.getBlockState(playerPos.east()).getBlock())); 
        if (northObby() || northBrock())
          renderItem(leftX, leftY, new ItemStack(mc.world.getBlockState(playerPos.north()).getBlock())); 
        if (southObby() || southBrock())
          renderItem(rightX, rightY, new ItemStack(mc.world.getBlockState(playerPos.south()).getBlock())); 
        if (westObby() || westBrock())
          renderItem(bottomX, bottomY, new ItemStack(mc.world.getBlockState(playerPos.west()).getBlock())); 
        break;
    } 
  }
  
  private void renderItem(double x, double y, ItemStack is) {
    RenderHelper.enableGUIStandardItemLighting();
    mc.getRenderItem().renderItemAndEffectIntoGUI(is, (int)x, (int)y);
    RenderHelper.disableStandardItemLighting();
  }
  
  private boolean northObby() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.north()).getBlock() == Blocks.OBSIDIAN);
  }
  
  private boolean eastObby() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.east()).getBlock() == Blocks.OBSIDIAN);
  }
  
  private boolean southObby() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.south()).getBlock() == Blocks.OBSIDIAN);
  }
  
  private boolean westObby() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.west()).getBlock() == Blocks.OBSIDIAN);
  }
  
  private boolean northBrock() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.north()).getBlock() == Blocks.BEDROCK);
  }
  
  private boolean eastBrock() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.east()).getBlock() == Blocks.BEDROCK);
  }
  
  private boolean southBrock() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.south()).getBlock() == Blocks.BEDROCK);
  }
  
  private boolean westBrock() {
    Vec3d vec3d = BlockUtils.getInterpolatedPos((Entity)mc.player, 0.0F);
    BlockPos playerPos = new BlockPos(vec3d);
    return (mc.world.getBlockState(playerPos.west()).getBlock() == Blocks.BEDROCK);
  }
}
