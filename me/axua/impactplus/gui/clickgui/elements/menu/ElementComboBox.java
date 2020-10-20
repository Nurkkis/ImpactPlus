//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.gui.clickgui.elements.menu;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.elements.Element;
import me.axua.impactplus.gui.clickgui.elements.ModuleButton;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.gui.settings.Setting;
import net.minecraft.client.gui.Gui;

public class ElementComboBox extends Element {
  public ElementComboBox(ModuleButton iparent, Setting iset) {
    this.parent = iparent;
    this.set = iset;
    setup();
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    Color temp = ColorUtil.getClickGUIColor();
    int color = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150)).getRGB();
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, -1156377837);
    ImpactPlus.CustomFont2.drawCenteredString(this.setstrg, (int)(this.x + this.width / 2.0D), (int)(this.y + 7.0D - 5.0D), -1);
    int clr1 = color;
    int clr2 = temp.getRGB();
    Gui.drawRect((int)this.x, (int)this.y + 14, (int)this.x + (int)this.width, (int)this.y + 15, 1996488704);
    if (this.comboextended) {
      Gui.drawRect((int)this.x, (int)this.y + 15, (int)this.x + (int)this.width, (int)this.y + (int)this.height, -1441656302);
      double ay = this.y + 15.0D;
      for (String sld : this.set.getOptions()) {
        String elementtitle = sld.substring(0, 1).toUpperCase() + sld.substring(1, sld.length());
        ImpactPlus.CustomFont2.drawCenteredString(elementtitle, (int)(this.x + this.width / 2.0D), (int)ay, -1);
        if (sld.equalsIgnoreCase(this.set.getValString()))
          Gui.drawRect((int)this.x, (int)ay, (int)this.x + 1, (int)ay + FontUtil.getFontHeight() + 2, clr1); 
        if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= ay && mouseY < ay + FontUtil.getFontHeight() + 2.0D)
          Gui.drawRect((int)this.x + (int)this.width - 1, (int)ay, (int)this.x + (int)this.width, (int)ay + FontUtil.getFontHeight() + 2, clr2); 
        ay += (FontUtil.getFontHeight() + 2);
      } 
    } 
  }
  
  public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if (mouseButton == 0) {
      if (isButtonHovered(mouseX, mouseY)) {
        this.comboextended = !this.comboextended;
        return true;
      } 
      if (!this.comboextended)
        return false; 
      double ay = this.y + 15.0D;
      for (String slcd : this.set.getOptions()) {
        if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= ay && mouseY <= ay + FontUtil.getFontHeight() + 2.0D) {
          if (this.clickgui != null && this.clickgui.setmgr != null)
            this.clickgui.setmgr.getSettingByID(this.set.getId()).setValString(slcd); 
          return true;
        } 
        ay += (FontUtil.getFontHeight() + 2);
      } 
    } 
    return super.mouseClicked(mouseX, mouseY, mouseButton);
  }
  
  public boolean isButtonHovered(int mouseX, int mouseY) {
    return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + 15.0D);
  }
}
