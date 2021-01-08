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

public class ElementCheckBox extends Element {
  public ElementCheckBox(ModuleButton iparent, Setting iset) {
    this.parent = iparent;
    this.set = iset;
    setup();
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    Color temp = ColorUtil.getClickGUIColor();
    int color = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 200)).getRGB();
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, -1156377837);
    ImpactPlus.CustomFont2.drawString(this.setstrg, (int)(this.x + this.width - ImpactPlus.CustomFont2.getStringWidth(this.setstrg) - 2.0D), (int)(this.y + (FontUtil.getFontHeight() / 2.0F) - 0.5D - 2.0D), -1);
    Gui.drawRect((int)this.x + 1, (int)this.y + 2, (int)this.x + 12, (int)this.y + 13, this.set.getValBoolean() ? color : -16777216);
    if (isCheckHovered(mouseX, mouseY))
      Gui.drawRect((int)this.x + 1, (int)this.y + 2, (int)this.x + 12, (int)this.y + 13, 1427181841); 
  }
  
  public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if (mouseButton == 0 && isCheckHovered(mouseX, mouseY)) {
      this.set.setValBoolean(!this.set.getValBoolean());
      return true;
    } 
    return super.mouseClicked(mouseX, mouseY, mouseButton);
  }
  
  public boolean isCheckHovered(int mouseX, int mouseY) {
    return (mouseX >= this.x + 1.0D && mouseX <= this.x + 12.0D && mouseY >= this.y + 2.0D && mouseY <= this.y + 13.0D);
  }
}
