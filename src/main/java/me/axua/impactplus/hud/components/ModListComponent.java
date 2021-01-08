//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.Comparator;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.ModList;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;

public class ModListComponent extends Panel {
  ModList mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  int sort;
  
  int modCount;
  
  int lolok;
  
  int lolok2;
  
  int yyy;
  
  public ModListComponent(double ix, double iy, ClickGUI parent) {
    super("ModList", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (ModList)ModuleManager.getModuleByName("ModList");
    this.isHudComponent = true;
  }
  
  public void drawHud() {
    doStuff();
    if (this.mod.rainbow.getValBoolean()) {
      this.c = Rainbow.getColor();
    } else {
      this.c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
    } 
    if (this.mod.sortUp.getValBoolean()) {
      this.sort = -1;
    } else {
      this.sort = 1;
    } 
    this.modCount = 0;
    this.lolok = 0;
    this.lolok2 = 0;
    ModuleManager.getModules()
      .stream()
      .filter(Module::isEnabled)
      .filter(Module::isDrawn)
      .sorted(Comparator.comparing(module -> Float.valueOf(ImpactPlus.CustomFont2.getStringWidth(module.getName() + ChatFormatting.GRAY + " " + module.getHudInfo()) * -1.0F)))
      .forEach(m -> {
          String bruhmoment = m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo();
          int bruhmomentnumber = (int)ImpactPlus.CustomFont2.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo());
          int bruhmomentnumber2 = (int)ImpactPlus.CustomFont2.getStringHeight(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo());
          if (this.mod.customFont.getValBoolean()) {
            if (this.mod.sortUp.getValBoolean()) {
              if (this.mod.right.getValBoolean()) {
                if (this.mod.rects.getValBoolean()) {
                  Gui.drawRect((int)this.x - bruhmomentnumber - 1, (int)this.y + this.lolok + 2, (int)this.x - 1, (int)this.y + bruhmomentnumber2 + this.lolok, 2097744137);
                  Gui.drawRect((int)this.x - bruhmomentnumber - 2, (int)this.y + this.lolok + 2, (int)this.x - bruhmomentnumber - 1, (int)this.y + bruhmomentnumber2 + this.lolok, this.text.getRGB());
                } 
                ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo()), ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
              } else {
                if (this.mod.rects.getValBoolean()) {
                  Gui.drawRect((int)this.x, (int)this.y + this.lolok + 2, (int)this.x + bruhmomentnumber, (int)this.y + bruhmomentnumber2 + this.lolok, 2097744137);
                  Gui.drawRect((int)this.x + bruhmomentnumber, (int)this.y + this.lolok + 2, (int)this.x + bruhmomentnumber + 1, (int)this.y + bruhmomentnumber2 + this.lolok, this.text.getRGB());
                } 
                ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
              } 
              this.lolok += 9;
              this.modCount++;
            } else {
              if (this.mod.right.getValBoolean()) {
                if (this.mod.rects.getValBoolean()) {
                  Gui.drawRect((int)this.x - bruhmomentnumber - 1, (int)this.y + this.lolok2 + 2, (int)this.x - 1, (int)this.y + bruhmomentnumber2 + this.lolok2, 2097744137);
                  Gui.drawRect((int)this.x - bruhmomentnumber - 2, (int)this.y + this.lolok2 + 2, (int)this.x - bruhmomentnumber - 1, (int)this.y + bruhmomentnumber2 + this.lolok2, this.text.getRGB());
                } 
                ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo()), ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
              } else {
                if (this.mod.rects.getValBoolean()) {
                  Gui.drawRect((int)this.x, (int)this.y + this.lolok2 + 2, (int)this.x + bruhmomentnumber, (int)this.y + bruhmomentnumber2 + this.lolok2, 2097744137);
                  Gui.drawRect((int)this.x + bruhmomentnumber, (int)this.y + this.lolok2 + 2, (int)this.x + bruhmomentnumber + 1, (int)this.y + bruhmomentnumber2 + this.lolok2, this.text.getRGB());
                } 
                ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
              } 
              this.lolok2 -= 9;
              this.modCount++;
            } 
          } else if (this.mod.sortUp.getValBoolean()) {
            if (this.mod.right.getValBoolean()) {
              if (this.mod.rects.getValBoolean()) {
                Gui.drawRect((int)this.x - bruhmomentnumber - 1, (int)this.y + this.lolok + 2, (int)this.x - 1, (int)this.y + bruhmomentnumber2 + this.lolok, 2097744137);
                Gui.drawRect((int)this.x - bruhmomentnumber - 2, (int)this.y + this.lolok + 2, (int)this.x - bruhmomentnumber - 1, (int)this.y + bruhmomentnumber2 + this.lolok, this.text.getRGB());
              } 
              mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), ((int)(float)this.x - mc.fontRenderer.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo())), ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
            } else {
              if (this.mod.rects.getValBoolean()) {
                Gui.drawRect((int)this.x, (int)this.y + this.lolok + 2, (int)this.x + bruhmomentnumber, (int)this.y + bruhmomentnumber2 + this.lolok, 2097744137);
                Gui.drawRect((int)this.x + bruhmomentnumber, (int)this.y + this.lolok + 2, (int)this.x + bruhmomentnumber + 1, (int)this.y + bruhmomentnumber2 + this.lolok, this.text.getRGB());
              } 
              mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
            } 
            this.lolok += 9;
            this.modCount++;
          } else {
            if (this.mod.right.getValBoolean()) {
              if (this.mod.rects.getValBoolean()) {
                Gui.drawRect((int)this.x - bruhmomentnumber - 1, (int)this.y + this.lolok2 + 2, (int)this.x - 1, (int)this.y + bruhmomentnumber2 + this.lolok2, 2097744137);
                Gui.drawRect((int)this.x - bruhmomentnumber - 2, (int)this.y + this.lolok2 + 2, (int)this.x - bruhmomentnumber - 1, (int)this.y + bruhmomentnumber2 + this.lolok2, this.text.getRGB());
              } 
              mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), ((int)(float)this.x - mc.fontRenderer.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo())), ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
            } else {
              if (this.mod.rects.getValBoolean()) {
                Gui.drawRect((int)this.x, (int)this.y + this.lolok2 + 2, (int)this.x + bruhmomentnumber, (int)this.y + bruhmomentnumber2 + this.lolok2, 2097744137);
                Gui.drawRect((int)this.x + bruhmomentnumber, (int)this.y + this.lolok2 + 2, (int)this.x + bruhmomentnumber + 1, (int)this.y + bruhmomentnumber2 + this.lolok2, this.text.getRGB());
              } 
              mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
            } 
            this.lolok2 -= 9;
            this.modCount++;
          } 
        });
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    this.c = new Color(50, 50, 50, 100);
    if (this.isHudComponentPinned)
      this.c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100); 
    if (this.dragging) {
      this.x = this.x2 + mouseX;
      this.y = this.y2 + mouseY;
    } 
    this.height = (FontUtil.getFontHeight() + 2);
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, this.c.getRGB());
    ImpactPlus.CustomFont2.drawStringWithShadow(this.title, (int)this.x, ((int)(this.y + this.height / 2.0D - (FontUtil.getFontHeight() / 2.0F)) - 2), -1);
    if (this.extended) {
      double startY = this.y + this.height;
      Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)this.height, this.c.getRGB());
      if (this.mod.rainbow.getValBoolean()) {
        this.c = Rainbow.getColor();
      } else {
        this.c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
      } 
      if (this.mod.sortUp.getValBoolean()) {
        this.sort = -1;
      } else {
        this.sort = 1;
      } 
      this.modCount = 0;
      this.lolok = 0;
      this.lolok2 = 0;
      ModuleManager.getModules()
        .stream()
        .filter(Module::isEnabled)
        .filter(Module::isDrawn)
        .sorted(Comparator.comparing(module -> Float.valueOf(ImpactPlus.CustomFont2.getStringWidth(module.getName() + ChatFormatting.GRAY + " " + module.getHudInfo()) * -1.0F)))
        .forEach(m -> {
            int bruhmomentnumber = (int)ImpactPlus.CustomFont2.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo());
            int bruhmomentnumber2 = (int)ImpactPlus.CustomFont2.getStringHeight(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo());
            if (this.mod.customFont.getValBoolean()) {
              if (this.mod.sortUp.getValBoolean()) {
                if (this.mod.right.getValBoolean()) {
                  ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo()), ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
                } else {
                  ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
                } 
                this.lolok += 9;
                this.modCount++;
                this.yyy = (int)this.y + 10;
              } else {
                if (this.mod.right.getValBoolean()) {
                  ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo()), ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
                } else {
                  ImpactPlus.CustomFont2.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
                } 
                this.lolok2 -= 9;
                this.modCount++;
              } 
            } else if (this.mod.sortUp.getValBoolean()) {
              if (this.mod.right.getValBoolean()) {
                mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), ((int)(float)this.x - mc.fontRenderer.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo())), ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * 9), this.c.getRGB());
              } 
              this.lolok += 9;
              this.modCount++;
              this.yyy = (int)this.y + 10;
            } else {
              if (this.mod.right.getValBoolean()) {
                mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), ((int)(float)this.x - mc.fontRenderer.getStringWidth(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo())), ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(m.getName() + ChatFormatting.GRAY + " " + m.getHudInfo(), (int)(float)this.x, ((int)(float)this.y + this.modCount * -9), this.c.getRGB());
              } 
              this.lolok2 -= 9;
              this.modCount++;
            } 
          });
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.font = this.mod.customFont.getValBoolean();
  }
}
