//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.gui.clickgui;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.elements.ModuleButton;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Panel {
  public String title;
  
  public double x;
  
  public double y;
  
  public double x2;
  
  public double y2;
  
  public double width;
  
  public double height;
  
  public boolean dragging;
  
  public boolean extended;
  
  public boolean visible;
  
  public ArrayList<ModuleButton> Elements = new ArrayList<>();
  
  public ClickGUI clickgui;
  
  protected static final Minecraft mc = Minecraft.getMinecraft();
  
  public boolean isHudComponent = false;
  
  public boolean isHudComponentPinned = false;
  
  public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended, ClickGUI parent) {
    this.title = ititle;
    this.x = ix;
    this.y = iy;
    this.width = iwidth;
    this.height = iheight;
    this.extended = iextended;
    this.dragging = false;
    this.visible = true;
    this.clickgui = parent;
    setup();
  }
  
  public void setup() {}
  
  public void drawHud() {}
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    if (!this.visible)
      return; 
    if (this.dragging) {
      this.x = this.x2 + mouseX;
      this.y = this.y2 + mouseY;
    } 
    Color temp = ColorUtil.getClickGUIColor().brighter();
    int outlineColor = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170)).getRGB();
    if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("Old")) {
      Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, -1156377837);
      Gui.drawRect((int)this.x, (int)(this.y + this.height - 2.0D), (int)(this.x + this.width), (int)(this.y + this.height), ColorUtil.getClickGUIColor().brighter().getRGB());
      ImpactPlus.CustomFont2.drawCenteredStringWithShadow(this.title, (int)(this.x + this.width / 2.0D), (int)(this.y + this.height / 2.0D - 6.0D), ColorUtil.getClickGUIColor().brighter().getRGB());
    } else if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
      String title33 = "";
      if (this.title.equals("RENDER")) {
        title33 = "Render";
      } else if (this.title.equals("PLAYER")) {
        title33 = "Player";
      } else if (this.title.equals("COMBAT")) {
        title33 = "Combat";
      } else if (this.title.equals("MOVEMENT")) {
        title33 = "Movement";
      } else if (this.title.equals("MISC")) {
        title33 = "Misc";
      } else if (this.title.equals("CHAT")) {
        title33 = "Chat";
      } else if (this.title.equals("COMPONENTS1")) {
        title33 = "Components1";
      } else if (this.title.equals("COMPONENTS2")) {
        title33 = "Components2";
      } 
      Gui.drawRect((int)this.x, (int)(this.y + 5.0D), (int)this.x + (int)this.width, (int)(this.y + 4.0D), 872415231);
      Gui.drawRect((int)this.x, (int)(this.y + this.height), (int)this.x + (int)this.width, (int)(this.y + this.height - 1.0D), 872415231);
      Gui.drawRect((int)this.x, (int)(this.y + 5.0D), (int)this.x - 1, (int)(this.y + this.height), 872415231);
      Gui.drawRect((int)(this.x + this.width), (int)(this.y + 5.0D), (int)(this.x + this.width + 1.0D), (int)(this.y + this.height), 872415231);
      Gui.drawRect((int)this.x, (int)this.y + 5, (int)this.x + (int)this.width, (int)this.y + (int)this.height - 1, -1777134829);
      Gui.drawRect((int)this.x + 68, (int)this.y + 8, (int)this.x + 68 + 3, (int)this.y + 11, -1777134829);
      Gui.drawRect((int)this.x + 68, (int)this.y + 8, (int)this.x + 68 + 3, (int)this.y + 7, 872415231);
      Gui.drawRect((int)this.x + 68, (int)this.y + 11, (int)this.x + 68 + 3, (int)this.y + 12, 872415231);
      Gui.drawRect((int)this.x + 68, (int)this.y + 8, (int)this.x + 67, (int)this.y + 11, 872415231);
      Gui.drawRect((int)this.x + 68 + 3, (int)this.y + 8, (int)this.x + 68 + 4, (int)this.y + 11, 872415231);
      Gui.drawRect((int)this.x + 62, (int)this.y + 8, (int)this.x + 62 + 3, (int)this.y + 11, -1777134829);
      Gui.drawRect((int)this.x + 62, (int)this.y + 8, (int)this.x + 62 + 3, (int)this.y + 7, 872415231);
      Gui.drawRect((int)this.x + 62, (int)this.y + 11, (int)this.x + 62 + 3, (int)this.y + 12, 872415231);
      Gui.drawRect((int)this.x + 62, (int)this.y + 8, (int)this.x + 61, (int)this.y + 11, 872415231);
      Gui.drawRect((int)this.x + 62 + 3, (int)this.y + 8, (int)this.x + 62 + 4, (int)this.y + 11, 872415231);
      mc.fontRenderer.drawStringWithShadow(title33, (int)(this.x + 1.0D), (int)(this.y + 6.0D), -171);
    } else {
      String title22 = "";
      if (this.title.equals("RENDER")) {
        title22 = "Render " + ChatFormatting.WHITE + "(11)";
      } else if (this.title.equals("PLAYER")) {
        title22 = "Player " + ChatFormatting.WHITE + "(5)";
      } else if (this.title.equals("COMBAT")) {
        title22 = "Combat " + ChatFormatting.WHITE + "(5)";
      } else if (this.title.equals("MOVEMENT")) {
        title22 = "Movement " + ChatFormatting.WHITE + "(2)";
      } else if (this.title.equals("MISC")) {
        title22 = "Misc " + ChatFormatting.WHITE + "(9)";
      } else if (this.title.equals("CHAT")) {
        title22 = "Chat " + ChatFormatting.WHITE + "(8)";
      } else if (this.title.equals("COMPONENTS1")) {
        title22 = "Components1 " + ChatFormatting.WHITE + "(18)";
      } else if (this.title.equals("COMPONENTS2")) {
        title22 = "Components2 " + ChatFormatting.WHITE + "(12)";
      } 
      Gui.drawRect((int)this.x, (int)this.y + 5, (int)this.x + (int)this.width, (int)this.y + (int)this.height, -771422971);
      ImpactPlus.CustomFont2.drawStringWithShadow(title22, (int)(this.x + 1.0D), (int)(this.y + 4.0D), ColorUtil.getClickGUIColor().brighter().getRGB());
    } 
    if (this.extended && !this.Elements.isEmpty()) {
      double startY = this.y + this.height;
      int epanelcolor = -1156377837;
      for (ModuleButton et : this.Elements) {
        if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("Old")) {
          Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)et.height + 1, -1156377837);
        } else if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
          Gui.drawRect((int)this.x, (int)startY, (int)this.x - 1, (int)(startY + (int)et.height + 1.0D), 872415231);
          Gui.drawRect((int)(this.x + this.width), (int)startY, (int)this.x + (int)this.width + 1, (int)startY + (int)et.height + 1, 872415231);
          Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)et.height + 1, -1777134829);
        } else {
          Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)et.height + 1, -1777134829);
        } 
        et.x = this.x + 2.0D;
        et.y = startY;
        et.width = this.width - 4.0D;
        et.drawScreen(mouseX, mouseY, partialTicks);
        startY += et.height + 1.0D;
      } 
      if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("Old")) {
        Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + 2, ColorUtil.getClickGUIColor().brighter().getRGB());
      } else if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
        Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + 1, 872415231);
      } 
      Gui.drawRect((int)this.x, (int)startY + 1, (int)this.x + (int)this.width, (int)startY + 1, epanelcolor);
    } 
  }
  
  public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if (!this.visible)
      return false; 
    if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
      this.x2 = this.x - mouseX;
      this.y2 = this.y - mouseY;
      this.dragging = true;
      return true;
    } 
    if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
      this.extended = !this.extended;
      return true;
    } 
    if (mouseButton == 2 && isHovered(mouseX, mouseY)) {
      if (this.isHudComponent)
        this.isHudComponentPinned = !this.isHudComponentPinned; 
    } else if (this.extended) {
      for (ModuleButton et : this.Elements) {
        if (et.mouseClicked(mouseX, mouseY, mouseButton))
          return true; 
      } 
    } 
    return false;
  }
  
  public void mouseReleased(int mouseX, int mouseY, int state) {
    if (!this.visible)
      return; 
    if (state == 0)
      this.dragging = false; 
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height);
  }
}
