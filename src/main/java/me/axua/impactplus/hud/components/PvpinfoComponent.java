//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.PvpInfo;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;

public class PvpinfoComponent extends Panel {
  PvpInfo mod;
  
  Color c;
  
  boolean font;
  
  Color off;
  
  Color on;
  
  public PvpinfoComponent(double ix, double iy, ClickGUI parent) {
    super("PvpInfo", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (PvpInfo)ModuleManager.getModuleByName("PvpInfo");
    this.isHudComponent = true;
  }
  
  public void drawHud() {
    doStuff();
    draw();
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    double w = (ImpactPlus.CustomFont2.getStringWidth(this.title) + 2.0F);
    this.c = new Color(50, 50, 50, 100);
    if (this.isHudComponentPinned)
      this.c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100); 
    if (this.dragging) {
      this.x = this.x2 + mouseX;
      this.y = this.y2 + mouseY;
    } 
    this.width = w;
    this.height = (FontUtil.getFontHeight() + 2);
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, this.c.getRGB());
    ImpactPlus.CustomFont2.drawStringWithShadow(this.title, (int)this.x, ((int)(this.y + this.height / 2.0D - (FontUtil.getFontHeight() / 2.0F)) - 2), -1);
    if (this.extended) {
      double startY = this.y + this.height;
      Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)this.height + 42, this.c.getRGB());
      if (ModuleManager.isModuleEnabled("AutoCrystal")) {
        drawText("CA ON", (int)this.x, (int)startY, this.on.getRGB());
      } else {
        drawText("CA OFF", (int)this.x, (int)startY, this.off.getRGB());
      } 
      if (ModuleManager.isModuleEnabled("KillAura")) {
        drawText("KA ON", (int)this.x, (int)startY + 10, this.on.getRGB());
      } else {
        drawText("KA OFF", (int)this.x, (int)startY + 10, this.off.getRGB());
      } 
      if (ModuleManager.isModuleEnabled("Surround")) {
        drawText("SU ON", (int)this.x, (int)startY + 20, this.on.getRGB());
      } else {
        drawText("SU OFF", (int)this.x, (int)startY + 20, this.off.getRGB());
      } 
      if (ModuleManager.isModuleEnabled("AutoTrap")) {
        drawText("AT ON", (int)this.x, (int)startY + 30, this.on.getRGB());
      } else {
        drawText("AT OFF", (int)this.x, (int)startY + 30, this.off.getRGB());
      } 
      if (ModuleManager.isModuleEnabled("HoleFill")) {
        drawText("HF ON", (int)this.x, (int)startY + 40, this.on.getRGB());
      } else {
        drawText("HF OFF", (int)this.x, (int)startY + 40, this.off.getRGB());
      } 
    } 
  }
  
  private void doStuff() {
    this.font = this.mod.customFont.getValBoolean();
    this.off = new Color(this.mod.offR.getValInt(), this.mod.offG.getValInt(), this.mod.offB.getValInt());
    this.on = new Color(this.mod.onR.getValInt(), this.mod.onG.getValInt(), this.mod.onB.getValInt());
    if (this.mod.offRainbow.getValBoolean())
      this.off = Rainbow.getColor(); 
    if (this.mod.onRainbow.getValBoolean())
      this.on = Rainbow.getColor(); 
  }
  
  private void drawText(String s, int x, int y, int c) {
    if (this.font) {
      ImpactPlus.CustomFont2.drawStringWithShadow(s, x, y, c);
    } else {
      mc.fontRenderer.drawStringWithShadow(s, x, y, c);
    } 
  }
  
  private void draw() {
    if (ModuleManager.isModuleEnabled("AutoCrystal")) {
      drawText("CA ON", (int)this.x, (int)this.y, this.on.getRGB());
    } else {
      drawText("CA OFF", (int)this.x, (int)this.y, this.off.getRGB());
    } 
    if (ModuleManager.isModuleEnabled("KillAura")) {
      drawText("KA ON", (int)this.x, (int)this.y + 10, this.on.getRGB());
    } else {
      drawText("KA OFF", (int)this.x, (int)this.y + 10, this.off.getRGB());
    } 
    if (ModuleManager.isModuleEnabled("Surround")) {
      drawText("SU ON", (int)this.x, (int)this.y + 20, this.on.getRGB());
    } else {
      drawText("SU OFF", (int)this.x, (int)this.y + 20, this.off.getRGB());
    } 
    if (ModuleManager.isModuleEnabled("AutoTrap")) {
      drawText("AT ON", (int)this.x, (int)this.y + 30, this.on.getRGB());
    } else {
      drawText("AT OFF", (int)this.x, (int)this.y + 30, this.off.getRGB());
    } 
    if (ModuleManager.isModuleEnabled("HoleFill")) {
      drawText("HF ON", (int)this.x, (int)this.y + 40, this.on.getRGB());
    } else {
      drawText("HF OFF", (int)this.x, (int)this.y + 40, this.off.getRGB());
    } 
  }
}
