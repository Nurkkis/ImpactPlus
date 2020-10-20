//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.gui.clickgui.elements.menu;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.elements.Element;
import me.axua.impactplus.gui.clickgui.elements.ModuleButton;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.settings.Setting;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;

public class ElementSlider extends Element {
  public boolean dragging;
  
  public ElementSlider(ModuleButton iparent, Setting iset) {
    this.parent = iparent;
    this.set = iset;
    this.dragging = false;
    setup();
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    String displayval = "" + (Math.round(this.set.getValDouble() * 100.0D) / 100.0D);
    boolean hoveredORdragged = (isSliderHovered(mouseX, mouseY) || this.dragging);
    Color temp = ColorUtil.getClickGUIColor();
    int color = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 250 : 200)).getRGB();
    int color2 = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 255 : 230)).getRGB();
    double percentBar = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, -1156377837);
    ImpactPlus.CustomFont2.drawString(this.setstrg, (int)(this.x + 1.0D), (int)this.y, -1);
    ImpactPlus.CustomFont2.drawString(displayval, (int)(this.x + this.width - ImpactPlus.CustomFont2.getStringWidth(displayval) - 2.0D), (int)this.y, -1);
    Gui.drawRect((int)this.x, (int)this.y + 12, (int)this.x + (int)this.width, (int)this.y + 13, -15724528);
    Gui.drawRect((int)this.x, (int)this.y + 12, (int)this.x + (int)percentBar * (int)this.width, (int)this.y + 13, color);
    if (percentBar > 0.0D && percentBar < 1.0D)
      Gui.drawRect((int)this.x + (int)percentBar * (int)this.width - 1, (int)this.y + 12, (int)this.x + Math.min((int)percentBar * (int)this.width, (int)this.width), (int)this.y + 13, color2); 
    if (this.dragging) {
      double diff = this.set.getMax() - this.set.getMin();
      double val = this.set.getMin() + MathHelper.clamp((mouseX - this.x) / this.width, 0.0D, 1.0D) * diff;
      this.set.setValDouble(val);
    } 
  }
  
  public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if (mouseButton == 0 && isSliderHovered(mouseX, mouseY)) {
      this.dragging = true;
      return true;
    } 
    return super.mouseClicked(mouseX, mouseY, mouseButton);
  }
  
  public void mouseReleased(int mouseX, int mouseY, int state) {
    this.dragging = false;
  }
  
  public boolean isSliderHovered(int mouseX, int mouseY) {
    return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + 11.0D && mouseY <= this.y + 14.0D);
  }
}
