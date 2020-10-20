//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.DecimalFormat;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.PotionEffects;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.PotionEffect;

public class PotionEffectsComponent extends Panel {
  PotionEffects mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  int count;
  
  DecimalFormat format1;
  
  DecimalFormat format2;
  
  public PotionEffectsComponent(double ix, double iy, ClickGUI parent) {
    super("PotionEffects", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (PotionEffects)ModuleManager.getModuleByName("PotionEffects");
    this.format1 = new DecimalFormat("0");
    this.format2 = new DecimalFormat("00");
    this.isHudComponent = true;
    this.isHudComponentPinned = true;
  }
  
  public void drawHud() {
    doStuff();
    if (this.mod.rainbow.getValBoolean()) {
      this.c = Rainbow.getColor();
    } else {
      this.c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
    } 
    this.count = 0;
    try {
      mc.player.getActivePotionEffects().forEach(effect -> {
            ChatFormatting numbercf = null;
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("None"))
              numbercf = ChatFormatting.RESET; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("White"))
              numbercf = ChatFormatting.WHITE; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Black"))
              numbercf = ChatFormatting.BLACK; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Gray"))
              numbercf = ChatFormatting.GRAY; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Gray"))
              numbercf = ChatFormatting.DARK_GRAY; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Aqua"))
              numbercf = ChatFormatting.AQUA; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Aqua"))
              numbercf = ChatFormatting.DARK_AQUA; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Blue"))
              numbercf = ChatFormatting.BLUE; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Blue"))
              numbercf = ChatFormatting.DARK_BLUE; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Green"))
              numbercf = ChatFormatting.GREEN; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Green"))
              numbercf = ChatFormatting.DARK_GREEN; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Light Purple"))
              numbercf = ChatFormatting.LIGHT_PURPLE; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Purple"))
              numbercf = ChatFormatting.DARK_PURPLE; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Red"))
              numbercf = ChatFormatting.RED; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Red"))
              numbercf = ChatFormatting.DARK_RED; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Yellow"))
              numbercf = ChatFormatting.YELLOW; 
            if (this.mod.numbercolour.getValString().equalsIgnoreCase("Gold"))
              numbercf = ChatFormatting.GOLD; 
            String name = I18n.format(effect.getPotion().getName(), new Object[0]);
            int duration = effect.getDuration() / 20;
            int amplifier = effect.getAmplifier() + 1;
            int color = effect.getPotion().getLiquidColor();
            double p1 = (duration % 60);
            double p2 = (duration / 60);
            double p3 = p2 % 60.0D;
            String minutes = this.format1.format(p3);
            String seconds = this.format2.format(p1);
            String s = name + " " + amplifier + numbercf + " " + minutes + ":" + seconds;
            float hi = ImpactPlus.CustomFont2.getStringWidth(s);
            float hi2 = mc.fontRenderer.getStringWidth(s);
            if (this.mod.customFont.getValBoolean()) {
              if (this.mod.normalcolour.getValBoolean()) {
                if (this.mod.sortUp.getValBoolean()) {
                  if (this.mod.right.getValBoolean()) {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * 10), color);
                  } else {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10), color);
                  } 
                  this.count++;
                } else {
                  if (this.mod.right.getValBoolean()) {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * -10), color);
                  } else {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10), color);
                  } 
                  this.count++;
                } 
              } else if (this.mod.sortUp.getValBoolean()) {
                if (this.mod.right.getValBoolean()) {
                  ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * 10), this.c.getRGB());
                } else {
                  ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10), this.c.getRGB());
                } 
                this.count++;
              } else {
                if (this.mod.right.getValBoolean()) {
                  ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * -10), this.c.getRGB());
                } else {
                  ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10), this.c.getRGB());
                } 
                this.count++;
              } 
            } else if (this.mod.normalcolour.getValBoolean()) {
              if (this.mod.sortUp.getValBoolean()) {
                if (this.mod.right.getValBoolean()) {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * 10), color);
                } else {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10), color);
                } 
                this.count++;
              } else {
                if (this.mod.right.getValBoolean()) {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * -10), color);
                } else {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10), color);
                } 
                this.count++;
              } 
            } else if (this.mod.sortUp.getValBoolean()) {
              if (this.mod.right.getValBoolean()) {
                mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * 10), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10), this.c.getRGB());
              } 
              this.count++;
            } else {
              if (this.mod.right.getValBoolean()) {
                mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * -10), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10), this.c.getRGB());
              } 
              this.count++;
            } 
          });
    } catch (NullPointerException e) {
      e.printStackTrace();
    } 
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
      this.count = 0;
      try {
        mc.player.getActivePotionEffects().forEach(effect -> {
              ChatFormatting numbercf = null;
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("None"))
                numbercf = ChatFormatting.RESET; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("White"))
                numbercf = ChatFormatting.WHITE; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Black"))
                numbercf = ChatFormatting.BLACK; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Gray"))
                numbercf = ChatFormatting.GRAY; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Gray"))
                numbercf = ChatFormatting.DARK_GRAY; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Aqua"))
                numbercf = ChatFormatting.AQUA; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Aqua"))
                numbercf = ChatFormatting.DARK_AQUA; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Blue"))
                numbercf = ChatFormatting.BLUE; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Blue"))
                numbercf = ChatFormatting.DARK_BLUE; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Green"))
                numbercf = ChatFormatting.GREEN; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Green"))
                numbercf = ChatFormatting.DARK_GREEN; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Light Purple"))
                numbercf = ChatFormatting.LIGHT_PURPLE; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Purple"))
                numbercf = ChatFormatting.DARK_PURPLE; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Red"))
                numbercf = ChatFormatting.RED; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Dark Red"))
                numbercf = ChatFormatting.DARK_RED; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Yellow"))
                numbercf = ChatFormatting.YELLOW; 
              if (this.mod.numbercolour.getValString().equalsIgnoreCase("Gold"))
                numbercf = ChatFormatting.GOLD; 
              String name = I18n.format(effect.getPotion().getName(), new Object[0]);
              int duration = effect.getDuration() / 20;
              int amplifier = effect.getAmplifier() + 1;
              int color = effect.getPotion().getLiquidColor();
              double p1 = (duration % 60);
              double p2 = (duration / 60);
              double p3 = p2 % 60.0D;
              String minutes = this.format1.format(p3);
              String seconds = this.format2.format(p1);
              String s = name + " " + amplifier + numbercf + " " + minutes + ":" + seconds;
              float hi = ImpactPlus.CustomFont2.getStringWidth(s);
              float hi2 = mc.fontRenderer.getStringWidth(s);
              if (this.mod.customFont.getValBoolean()) {
                if (this.mod.normalcolour.getValBoolean()) {
                  if (this.mod.sortUp.getValBoolean()) {
                    if (this.mod.right.getValBoolean()) {
                      ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * 10) + 10.0F, color);
                    } else {
                      ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10) + 10.0F, color);
                    } 
                    this.count++;
                  } else {
                    if (this.mod.right.getValBoolean()) {
                      ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * -10) + 10.0F, color);
                    } else {
                      ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10) + 10.0F, color);
                    } 
                    this.count++;
                  } 
                } else if (this.mod.sortUp.getValBoolean()) {
                  if (this.mod.right.getValBoolean()) {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * 10) + 10.0F, this.c.getRGB());
                  } else {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10) + 10.0F, this.c.getRGB());
                  } 
                  this.count++;
                } else {
                  if (this.mod.right.getValBoolean()) {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x - hi, (float)this.y + (this.count * -10) + 10.0F, this.c.getRGB());
                  } else {
                    ImpactPlus.CustomFont2.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10) + 10.0F, this.c.getRGB());
                  } 
                  this.count++;
                } 
              } else if (this.mod.normalcolour.getValBoolean()) {
                if (this.mod.sortUp.getValBoolean()) {
                  if (this.mod.right.getValBoolean()) {
                    mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * 10) + 10.0F, color);
                  } else {
                    mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10) + 10.0F, color);
                  } 
                  this.count++;
                } else {
                  if (this.mod.right.getValBoolean()) {
                    mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * -10) + 10.0F, color);
                  } else {
                    mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10) + 10.0F, color);
                  } 
                  this.count++;
                } 
              } else if (this.mod.sortUp.getValBoolean()) {
                if (this.mod.right.getValBoolean()) {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * 10) + 10.0F, this.c.getRGB());
                } else {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * 10) + 10.0F, this.c.getRGB());
                } 
                this.count++;
              } else {
                if (this.mod.right.getValBoolean()) {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x - hi2, (float)this.y + (this.count * -10) + 10.0F, this.c.getRGB());
                } else {
                  mc.fontRenderer.drawStringWithShadow(s, (float)this.x, (float)this.y + (this.count * -10) + 10.0F, this.c.getRGB());
                } 
                this.count++;
              } 
            });
      } catch (NullPointerException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
  }
}
