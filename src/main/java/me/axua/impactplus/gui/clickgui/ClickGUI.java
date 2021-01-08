//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.gui.clickgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.elements.Element;
import me.axua.impactplus.gui.clickgui.elements.ModuleButton;
import me.axua.impactplus.gui.clickgui.elements.menu.ElementSlider;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.gui.settings.SettingsManager;
import me.axua.impactplus.hud.HudComponentManager;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ClickGUI extends GuiScreen {
  public static ArrayList<Panel> panels;
  
  public static ArrayList<Panel> rpanels;
  
  private ModuleButton mb = null;
  
  public SettingsManager setmgr;
  
  Color text;
  
  public ClickGUI() {
    this.setmgr = (ImpactPlus.getInstance()).settingsManager;
    panels = new ArrayList<>();
    FontUtil.setupFontUtils();
    double pwidth = 75.0D;
    double pheight = 15.0D;
    double px = 120.0D;
    double py = 5.0D;
    double pxplus = pwidth + 11.0D;
    for (Module.Category c : Module.Category.values()) {
      String title = c.name();
      panels.add(new Panel(title, px, py, pwidth, pheight, true, this) {
            public void setup() {
              for (Module m : ModuleManager.getModules()) {
                if (!m.getCategory().equals(c))
                  continue; 
                this.Elements.add(new ModuleButton(m, this));
              } 
            }
          });
      px += pxplus;
    } 
    rpanels = new ArrayList<>();
    for (Panel p : panels)
      rpanels.add(p); 
    Collections.reverse(rpanels);
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Gui.drawRect(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight(), -1777134829);
    } 
    for (Panel p : panels)
      p.drawScreen(mouseX, mouseY, partialTicks); 
    for (Panel p : HudComponentManager.hudComponents)
      p.drawScreen(mouseX, mouseY, partialTicks); 
    ScaledResolution s = new ScaledResolution(this.mc);
    GL11.glPushMatrix();
    GL11.glTranslated((s.getScaledWidth() - 2), (s.getScaledHeight() - 2), 0.0D);
    GL11.glScaled(0.8D, 0.8D, 0.8D);
    this.text = Rainbow.getColor();
    ImpactPlus.CustomFont2.getClass();
    ImpactPlus.CustomFont2.drawStringWithShadow("by Axua", -ImpactPlus.CustomFont2.getStringWidth("by Axua"), -9, this.text.getRGB());
    GL11.glPopMatrix();
    this.mb = null;
    label78: for (Panel p : panels) {
      if (p != null && p.visible && p.extended && p.Elements != null && p.Elements
        .size() > 0)
        for (ModuleButton e : p.Elements) {
          if (e.listening) {
            this.mb = e;
            break label78;
          } 
        }  
    } 
    for (Panel panel : panels) {
      if (panel.extended && panel.visible && panel.Elements != null)
        for (ModuleButton b : panel.Elements) {
          if (b.extended && b.menuelements != null && !b.menuelements.isEmpty()) {
            double off = 0.0D;
            Color temp = ColorUtil.getClickGUIColor().brighter();
            int outlineColor = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170)).getRGB();
            for (Element e : b.menuelements) {
              e.offset = off;
              e.update();
              e.drawScreen(mouseX, mouseY, partialTicks);
              off += e.height;
            } 
          } 
        }  
    } 
    if (this.mb != null) {
      GL11.glPushMatrix();
      drawRect(0, 0, this.width, this.height, -2012213232);
      GL11.glTranslatef(s.getScaledWidth() / 2.0F, s.getScaledHeight() / 2.0F, 0.0F);
      GL11.glScalef(3.0F, 3.0F, 0.0F);
      ImpactPlus.CustomFont2.drawCenteredStringWithShadow("Listening...", 0.0F, -10.0F, -1);
      GL11.glScalef(0.5F, 0.5F, 0.0F);
      ImpactPlus.CustomFont2.drawCenteredStringWithShadow("Press 'ESCAPE' to unbind " + this.mb.mod.getName() + ((this.mb.mod.getBind() > -1) ? (" (" + Keyboard.getKeyName(this.mb.mod.getBind()) + ")") : ""), 0.0F, 0.0F, -1);
      GL11.glPopMatrix();
    } 
    for (Panel p : panels) {
      for (ModuleButton b : p.Elements) {
        if (b.isHovered(mouseX, mouseY) && (ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiTooltips").getValBoolean()) {
          Color temp = ColorUtil.getClickGUIColor().brighter();
          int outlineColor = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170)).getRGB();
          String desc = b.mod.getDescription();
          int xx = mouseX + 10;
          int yy = mouseY - 5;
          if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("Old")) {
            Gui.drawRect(xx - 2, yy - 2, xx + (int)ImpactPlus.CustomFont2.getWidth(desc) + 2, yy + FontUtil.getFontHeight() + 2, outlineColor);
            ImpactPlus.CustomFont2.drawStringWithShadow(desc, (xx - 1), (yy - 2), -1);
            continue;
          } 
          if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiStyle").getValString().equals("WeepCraft")) {
            Gui.drawRect(xx - 2, yy - 2, xx + (Minecraft.getMinecraft()).fontRenderer.getStringWidth(desc), yy - 1, 872415231);
            Gui.drawRect(xx - 2, yy + FontUtil.getFontHeight(), xx + (Minecraft.getMinecraft()).fontRenderer.getStringWidth(desc), yy + FontUtil.getFontHeight() + 1, 872415231);
            Gui.drawRect(xx - 2, yy - 1, xx - 3, yy + FontUtil.getFontHeight(), 872415231);
            Gui.drawRect(xx + (Minecraft.getMinecraft()).fontRenderer.getStringWidth(desc), yy - 1, xx + (Minecraft.getMinecraft()).fontRenderer.getStringWidth(desc) + 1, yy + FontUtil.getFontHeight(), 872415231);
            Gui.drawRect(xx - 2, yy - 1, xx + (Minecraft.getMinecraft()).fontRenderer.getStringWidth(desc), yy + FontUtil.getFontHeight(), -1777134829);
            (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(desc, (xx - 1), yy, -65536);
            continue;
          } 
          Gui.drawRect(xx - 2, yy - 2, xx + (int)ImpactPlus.CustomFont2.getWidth(desc) + 2, yy + FontUtil.getFontHeight() + 2, -1777134829);
          ImpactPlus.CustomFont2.drawStringWithShadow(desc, (xx - 1), (yy - 2), -1);
        } 
      } 
    } 
    super.drawScreen(mouseX, mouseY, partialTicks);
  }
  
  public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    if (this.mb != null)
      return; 
    for (Panel panel : rpanels) {
      if (panel.extended && panel.visible && panel.Elements != null)
        for (ModuleButton b : panel.Elements) {
          if (b.extended)
            for (Element e : b.menuelements) {
              if (e.mouseClicked(mouseX, mouseY, mouseButton))
                return; 
            }  
        }  
    } 
    for (Panel p : rpanels) {
      if (p.mouseClicked(mouseX, mouseY, mouseButton))
        return; 
    } 
    for (Panel p : HudComponentManager.hudComponents) {
      if (p.mouseClicked(mouseX, mouseY, mouseButton))
        return; 
    } 
    try {
      super.mouseClicked(mouseX, mouseY, mouseButton);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void mouseReleased(int mouseX, int mouseY, int state) {
    if (this.mb != null)
      return; 
    for (Panel panel : rpanels) {
      if (panel.extended && panel.visible && panel.Elements != null)
        for (ModuleButton b : panel.Elements) {
          if (b.extended)
            for (Element e : b.menuelements)
              e.mouseReleased(mouseX, mouseY, state);  
        }  
    } 
    for (Panel p : rpanels)
      p.mouseReleased(mouseX, mouseY, state); 
    for (Panel p : HudComponentManager.hudComponents)
      p.mouseReleased(mouseX, mouseY, state); 
    super.mouseReleased(mouseX, mouseY, state);
  }
  
  protected void keyTyped(char typedChar, int keyCode) {
    for (Panel p : rpanels) {
      if (p != null && p.visible && p.extended && p.Elements != null && p.Elements.size() > 0)
        for (ModuleButton e : p.Elements) {
          try {
            if (e.keyTyped(typedChar, keyCode))
              return; 
          } catch (IOException e1) {
            e1.printStackTrace();
          } 
        }  
    } 
    try {
      super.keyTyped(typedChar, keyCode);
    } catch (IOException e2) {
      e2.printStackTrace();
    } 
  }
  
  public void initGui() {}
  
  public void onGuiClosed() {
    for (Panel panel : rpanels) {
      if (panel.extended && panel.visible && panel.Elements != null)
        for (ModuleButton b : panel.Elements) {
          if (b.extended)
            for (Element e : b.menuelements) {
              if (e instanceof ElementSlider)
                ((ElementSlider)e).dragging = false; 
            }  
        }  
    } 
  }
  
  public void closeAllSettings() {
    for (Panel p : rpanels) {
      if (p != null && p.visible && p.extended && p.Elements != null && p.Elements
        .size() > 0)
        for (ModuleButton e : p.Elements)
          e.extended = false;  
    } 
  }
  
  public static ArrayList<Panel> getPanels() {
    return panels;
  }
  
  public static Panel getPanelByName(String name) {
    Panel pa = null;
    for (Panel p : getPanels()) {
      if (p.title.equalsIgnoreCase(name))
        pa = p; 
    } 
    return pa;
  }
}
