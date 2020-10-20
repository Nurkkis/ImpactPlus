//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.Time;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class TimeComponent extends Panel {
  Time mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  public TimeComponent(double ix, double iy, ClickGUI parent) {
    super("Time", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (Time)ModuleManager.getModuleByName("Time");
    this.isHudComponent = true;
  }
  
  public void drawHud() {
    doStuff();
    Minecraft mc = Minecraft.getMinecraft();
    String date = (new SimpleDateFormat("h:mm a")).format(new Date());
    float hi = ImpactPlus.CustomFont2.getStringWidth(date);
    float hi2 = mc.fontRenderer.getStringWidth(date);
    if (this.mod.right.getValBoolean()) {
      if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(date, (float)this.x - hi, (float)this.y, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(date, (float)this.x - hi2, (float)this.y, this.text.getRGB());
      } 
    } else if (this.font) {
      ImpactPlus.CustomFont2.drawStringWithShadow(date, (float)this.x, (float)this.y, this.text.getRGB());
    } else {
      mc.fontRenderer.drawStringWithShadow(date, (float)this.x, (float)this.y, this.text.getRGB());
    } 
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    Minecraft mc = Minecraft.getMinecraft();
    String date = (new SimpleDateFormat("h:mm a")).format(new Date());
    double w = (ImpactPlus.CustomFont2.getStringWidth(date) + 2.0F);
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
      Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)this.height, this.c.getRGB());
      float hi = ImpactPlus.CustomFont2.getStringWidth(date);
      float hi2 = mc.fontRenderer.getStringWidth(date);
      if (this.mod.right.getValBoolean()) {
        if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(date, (float)this.x - hi, (float)this.y + 10.0F, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(date, (float)this.x - hi2, (float)this.y + 10.0F, this.text.getRGB());
        } 
      } else if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(date, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(date, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
      } 
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.font = this.mod.customFont.getValBoolean();
  }
}
