//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.DecimalFormat;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.TextRadar;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;

public class TextRadarComponent extends Panel {
  TextRadar mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  String s;
  
  String h;
  
  String fristr;
  
  int count;
  
  DecimalFormat decimalFormat;
  
  public TextRadarComponent(double ix, double iy, ClickGUI parent) {
    super("TextRadar", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (TextRadar)ModuleManager.getModuleByName("TextRadar");
    this.s = "";
    this.h = "";
    this.fristr = "";
    this.decimalFormat = new DecimalFormat("00.0");
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
    boolean font = this.mod.customFont.getValBoolean();
    this.count = 0;
    mc.world.loadedEntityList.stream()
      .filter(e -> e instanceof EntityPlayer)
      .filter(e -> (e != mc.player))
      .forEach(e -> {
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
          if (this.mod.showfands.getValBoolean())
            if (Friends.isFriend(e.getName()) && ((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
              this.fristr = ChatFormatting.GREEN + "F " + ChatFormatting.RED + "S " + ChatFormatting.RESET;
            } else if (((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
              this.fristr = ChatFormatting.RED + "S " + ChatFormatting.RESET;
            } else if (Friends.isFriend(e.getName())) {
              this.fristr = ChatFormatting.GREEN + "F " + ChatFormatting.RESET;
            } else if (!Friends.isFriend(e.getName()) && !((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
              this.fristr = "";
            } else if (!((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
              this.fristr = "";
            } else if (!Friends.isFriend(e.getName())) {
              this.fristr = "";
            }  
          if (this.mod.healthcolour.getValBoolean()) {
            if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() <= 5.0F)
              this.s = ChatFormatting.RED + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " "; 
            if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() > 5.0F && ((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() <= 15.0F)
              this.s = ChatFormatting.YELLOW + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " "; 
            if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() > 15.0F)
              this.s = ChatFormatting.GREEN + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " "; 
          } else if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() > 0.0F) {
            this.s = numbercf + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " ";
          } 
          if (this.mod.distance.getValBoolean())
            if (this.mod.distancecolour.getValBoolean()) {
              if (mc.player.getDistance(e) <= 15.0F)
                this.h = " " + ChatFormatting.RED + (int)mc.player.getDistance(e); 
              if (mc.player.getDistance(e) > 15.0F)
                this.h = " " + ChatFormatting.YELLOW + (int)mc.player.getDistance(e); 
              if (mc.player.getDistance(e) > 30.0F)
                this.h = " " + ChatFormatting.GREEN + (int)mc.player.getDistance(e); 
            } else {
              this.h = " " + ChatFormatting.RESET + numbercf + (int)mc.player.getDistance(e);
            }  
          if (!this.mod.distance.getValBoolean()) {
            if (this.mod.right.getValBoolean()) {
              if (font) {
                ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName(), (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(this.fristr + e.getName() + this.s), ((int)(float)this.y + this.count), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName(), ((int)(float)this.x - mc.fontRenderer.getStringWidth(this.fristr + e.getName() + this.s)), ((int)(float)this.y + this.count), this.c.getRGB());
              } 
            } else if (font) {
              ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName(), (int)(float)this.x, ((int)(float)this.y + this.count), this.c.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName(), (int)(float)this.x, ((int)(float)this.y + this.count), this.c.getRGB());
            } 
          } else if (this.mod.right.getValBoolean()) {
            if (font) {
              ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(this.fristr + e.getName() + this.s + this.h), ((int)(float)this.y + this.count), this.c.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, ((int)(float)this.x - mc.fontRenderer.getStringWidth(this.fristr + e.getName() + this.s + this.h)), ((int)(float)this.y + this.count), this.c.getRGB());
            } 
          } else if (font) {
            ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, (int)(float)this.x, ((int)(float)this.y + this.count), this.c.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, (int)(float)this.x, ((int)(float)this.y + this.count), this.c.getRGB());
          } 
          this.count += 10;
        });
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    double w = (ImpactPlus.CustomFont2.getStringWidth(this.s) + 2.0F);
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
      if (this.mod.rainbow.getValBoolean()) {
        this.c = Rainbow.getColor();
      } else {
        this.c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
      } 
      boolean font = this.mod.customFont.getValBoolean();
      this.count = 0;
      mc.world.loadedEntityList.stream()
        .filter(e -> e instanceof EntityPlayer)
        .filter(e -> (e != mc.player))
        .forEach(e -> {
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
            if (this.mod.showfands.getValBoolean())
              if (Friends.isFriend(e.getName()) && ((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
                this.fristr = ChatFormatting.GREEN + "F " + ChatFormatting.RED + "S " + ChatFormatting.RESET;
              } else if (((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
                this.fristr = ChatFormatting.RED + "S " + ChatFormatting.RESET;
              } else if (Friends.isFriend(e.getName())) {
                this.fristr = ChatFormatting.GREEN + "F " + ChatFormatting.RESET;
              } else if (!Friends.isFriend(e.getName()) && !((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
                this.fristr = "";
              } else if (!((EntityPlayer)e).isPotionActive(MobEffects.STRENGTH)) {
                this.fristr = "";
              } else if (!Friends.isFriend(e.getName())) {
                this.fristr = "";
              }  
            if (this.mod.healthcolour.getValBoolean()) {
              if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() <= 5.0F)
                this.s = ChatFormatting.RED + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " "; 
              if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() > 5.0F && ((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() <= 15.0F)
                this.s = ChatFormatting.YELLOW + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " "; 
              if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() > 15.0F)
                this.s = ChatFormatting.GREEN + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " "; 
            } else if (((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount() > 0.0F) {
              this.s = numbercf + this.decimalFormat.format((((EntityPlayer)e).getHealth() + ((EntityPlayer)e).getAbsorptionAmount())) + ChatFormatting.RESET + " ";
            } 
            if (this.mod.distance.getValBoolean())
              if (this.mod.distancecolour.getValBoolean()) {
                if (mc.player.getDistance(e) <= 15.0F)
                  this.h = " " + ChatFormatting.RED + (int)mc.player.getDistance(e); 
                if (mc.player.getDistance(e) > 15.0F)
                  this.h = " " + ChatFormatting.YELLOW + (int)mc.player.getDistance(e); 
                if (mc.player.getDistance(e) > 30.0F)
                  this.h = " " + ChatFormatting.GREEN + (int)mc.player.getDistance(e); 
              } else {
                this.h = " " + ChatFormatting.RESET + numbercf + (int)mc.player.getDistance(e);
              }  
            if (!this.mod.distance.getValBoolean()) {
              if (this.mod.right.getValBoolean()) {
                if (font) {
                  ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName(), (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(this.fristr + e.getName() + this.s), ((int)(float)this.y + 10 + this.count), this.c.getRGB());
                } else {
                  mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName(), ((int)(float)this.x - mc.fontRenderer.getStringWidth(this.fristr + e.getName() + this.s)), ((int)(float)this.y + 10 + this.count), this.c.getRGB());
                } 
              } else if (font) {
                ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName(), (int)(float)this.x, ((int)(float)this.y + 10 + this.count), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName(), (int)(float)this.x, ((int)(float)this.y + 10 + this.count), this.c.getRGB());
              } 
            } else if (this.mod.right.getValBoolean()) {
              if (font) {
                ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(this.fristr + e.getName() + this.s + this.h), ((int)(float)this.y + 10 + this.count), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, ((int)(float)this.x - mc.fontRenderer.getStringWidth(this.fristr + e.getName() + this.s + this.h)), ((int)(float)this.y + 10 + this.count), this.c.getRGB());
              } 
            } else if (font) {
              ImpactPlus.CustomFont2.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, (int)(float)this.x, ((int)(float)this.y + 10 + this.count), this.c.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(this.fristr + this.s + e.getName() + this.h, (int)(float)this.x, ((int)(float)this.y + 10 + this.count), this.c.getRGB());
            } 
            this.count += 10;
          });
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.font = this.mod.customFont.getValBoolean();
  }
}
