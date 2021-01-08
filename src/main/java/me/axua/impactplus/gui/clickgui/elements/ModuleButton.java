//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.gui.clickgui.elements;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.elements.menu.ElementCheckBox;
import me.axua.impactplus.gui.clickgui.elements.menu.ElementComboBox;
import me.axua.impactplus.gui.clickgui.elements.menu.ElementSlider;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModuleButton {
  public Module mod;
  
  public ArrayList<Element> menuelements;
  
  public Panel parent;
  
  public double x;
  
  public double y;
  
  public double width;
  
  public double height;
  
  public boolean extended = false;
  
  public boolean listening = false;
  
  public ModuleButton(Module imod, Panel pl) {
    this.mod = imod;
    this.height = ((Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT + 2);
    this.parent = pl;
    this.menuelements = new ArrayList<>();
    if ((ImpactPlus.getInstance()).settingsManager.getSettingsByMod(imod) != null)
      for (Setting s : (ImpactPlus.getInstance()).settingsManager.getSettingsByMod(imod)) {
        if (s.isCheck()) {
          this.menuelements.add(new ElementCheckBox(this, s));
          continue;
        } 
        if (s.isSlider()) {
          this.menuelements.add(new ElementSlider(this, s));
          continue;
        } 
        if (s.isCombo())
          this.menuelements.add(new ElementComboBox(this, s)); 
      }  
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    int i;
    Color temp = ColorUtil.getClickGUIColor();
    int color = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150)).getRGB();
    color = ColorUtil.getClickGUIColor().brighter().getRGB();
    if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
      i = -65536;
    } else {
      i = -1;
    } 
    if (isHovered(mouseX, mouseY))
      if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("Old")) {
        Gui.drawRect((int)this.x - 2, (int)this.y, (int)this.x + (int)this.width + 2, (int)this.y + (int)this.height + 1, -16777216);
      } else if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
        i = -16711936;
      } else {
        i = -1280;
      }  
    if (this.mod.isEnabled())
      if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
        i = -16733696;
      } else {
        i = ColorUtil.getClickGUIColor().brighter().getRGB();
      }  
    if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
      (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(this.mod.getName(), (int)(this.x - 1.0D), (int)(this.y + 2.0D), i);
    } else if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiTextMode").getValString().equalsIgnoreCase("Left")) {
      ImpactPlus.CustomFont2.drawStringWithShadow(this.mod.getName(), (int)this.x, (int)this.y, i);
    } else {
      ImpactPlus.CustomFont2.drawCenteredStringWithShadow(this.mod.getName(), (int)(this.x + this.width / 2.0D), (int)(this.y + this.height / 2.0D - 5.0D), i);
    } 
  }
  
  public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if (!isHovered(mouseX, mouseY))
      return false; 
    if (mouseButton == 0) {
      this.mod.toggle();
    } else if (mouseButton == 1) {
      if (this.menuelements != null && this.menuelements.size() > 0) {
        boolean b = !this.extended;
        (ImpactPlus.getInstance()).clickGui.closeAllSettings();
        this.extended = b;
      } 
    } else if (mouseButton == 2) {
      this.listening = true;
    } 
    return true;
  }
  
  public boolean keyTyped(char typedChar, int keyCode) throws IOException {
    if (this.listening) {
      if (keyCode != 1) {
        this.mod.setBind(keyCode);
      } else {
        this.mod.setBind(0);
      } 
      this.listening = false;
      return true;
    } 
    return false;
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height);
  }
}
