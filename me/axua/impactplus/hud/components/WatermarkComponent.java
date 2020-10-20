//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components2.Watermark;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class WatermarkComponent extends Panel {
  private final Object Watermark = Boolean.valueOf(false);
  
  Watermark mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  public WatermarkComponent(double ix, double iy, ClickGUI parent) {
    super("Watermark", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (Watermark)ModuleManager.getModuleByName("Watermark");
    this.isHudComponent = true;
    this.isHudComponentPinned = true;
  }
  
  public void drawHud() {
    doStuff();
    if (this.mod.watermarkmode.getValString().equalsIgnoreCase("WeepCraft")) {
      String weeb = ChatFormatting.RED + "Weep" + ChatFormatting.DARK_RED + "Craft" + ChatFormatting.GREEN + " 7.6" + ChatFormatting.YELLOW + "v";
      float hi = ImpactPlus.CustomFont2.getStringWidth(weeb);
      float hi2 = mc.fontRenderer.getStringWidth(weeb);
      if (this.mod.right.getValBoolean()) {
        if (this.font) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x - hi), ((int)(float)this.y + 1), 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          ImpactPlus.CustomFont2.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } else {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x - hi2), (int)(float)this.y, 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          mc.fontRenderer.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } 
      } else if (this.font) {
        GL11.glPushMatrix();
        GL11.glTranslated((int)(float)this.x, (int)(float)this.y, 0.0D);
        GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
        ImpactPlus.CustomFont2.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
        GL11.glPopMatrix();
      } else {
        GL11.glPushMatrix();
        GL11.glTranslated((int)(float)this.x, (int)(float)this.y, 0.0D);
        GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
        mc.fontRenderer.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
        GL11.glPopMatrix();
      } 
    } else {
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
      String s = "";
      if (this.mod.watermarkmode.getValString().equalsIgnoreCase("SalHack"))
        s = "SalHack "; 
      if (this.mod.watermarkmode.getValString().equalsIgnoreCase("Impact+"))
        s = "Impact+ "; 
      if (this.mod.version.getValBoolean()) {
        if (this.mod.versionmode.getValString().equalsIgnoreCase("v1.0"))
          s = s + numbercf + "v1.0"; 
        if (this.mod.versionmode.getValString().equalsIgnoreCase("v2.0"))
          s = s + numbercf + "v2.0"; 
        if (this.mod.versionmode.getValString().equalsIgnoreCase("v2.4"))
          s = s + numbercf + "v2.4"; 
      } 
      float hi = ImpactPlus.CustomFont2.getStringWidth(s);
      float hi2 = mc.fontRenderer.getStringWidth(s);
      if (this.mod.right.getValBoolean()) {
        if (this.font) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x - hi), ((int)(float)this.y + 1), 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          ImpactPlus.CustomFont2.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } else {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x - hi2), (int)(float)this.y, 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          mc.fontRenderer.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } 
      } else if (this.font) {
        GL11.glPushMatrix();
        GL11.glTranslated((int)(float)this.x, (int)(float)this.y, 0.0D);
        GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
        ImpactPlus.CustomFont2.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
        GL11.glPopMatrix();
      } else {
        GL11.glPushMatrix();
        GL11.glTranslated((int)(float)this.x, (int)(float)this.y, 0.0D);
        GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
        mc.fontRenderer.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
        GL11.glPopMatrix();
      } 
    } 
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    if (this.mod.watermarkmode.getValString().equalsIgnoreCase("WeepCraft")) {
      String weeb = ChatFormatting.RED + "Weep" + ChatFormatting.DARK_RED + "Craft" + ChatFormatting.GREEN + " 7.6" + ChatFormatting.YELLOW + "v";
      double w = (ImpactPlus.CustomFont2.getStringWidth(weeb) + 2.0F);
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
        float hi = ImpactPlus.CustomFont2.getStringWidth(weeb);
        float hi2 = mc.fontRenderer.getStringWidth(weeb);
        if (this.mod.right.getValBoolean()) {
          if (this.font) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x - hi), ((int)(float)this.y + 10), 0.0D);
            GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
            ImpactPlus.CustomFont2.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
            GL11.glPopMatrix();
          } else {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x - hi2), ((int)(float)this.y + 10), 0.0D);
            GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
            mc.fontRenderer.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
            GL11.glPopMatrix();
          } 
        } else if (this.font) {
          GL11.glPushMatrix();
          GL11.glTranslated((int)(float)this.x, ((int)(float)this.y + 10), 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          ImpactPlus.CustomFont2.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } else {
          GL11.glPushMatrix();
          GL11.glTranslated((int)(float)this.x, ((int)(float)this.y + 10), 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          mc.fontRenderer.drawStringWithShadow(weeb, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } 
      } 
    } else {
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
      String s = "";
      if (this.mod.watermarkmode.getValString().equalsIgnoreCase("SalHack"))
        s = "SalHack "; 
      if (this.mod.watermarkmode.getValString().equalsIgnoreCase("Impact+"))
        s = "Impact+ "; 
      if (this.mod.version.getValBoolean()) {
        if (this.mod.versionmode.getValString().equalsIgnoreCase("v1.0"))
          s = s + numbercf + "v1.0"; 
        if (this.mod.versionmode.getValString().equalsIgnoreCase("v2.0"))
          s = s + numbercf + "v2.0"; 
        if (this.mod.versionmode.getValString().equalsIgnoreCase("v2.4"))
          s = s + numbercf + "v2.4"; 
      } 
      double w = (ImpactPlus.CustomFont2.getStringWidth(s) + 2.0F);
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
        float hi = ImpactPlus.CustomFont2.getStringWidth(s);
        float hi2 = mc.fontRenderer.getStringWidth(s);
        if (this.mod.right.getValBoolean()) {
          if (this.font) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x - hi), ((int)(float)this.y + 10), 0.0D);
            GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
            ImpactPlus.CustomFont2.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
            GL11.glPopMatrix();
          } else {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x - hi2), ((int)(float)this.y + 10), 0.0D);
            GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
            mc.fontRenderer.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
            GL11.glPopMatrix();
          } 
        } else if (this.font) {
          GL11.glPushMatrix();
          GL11.glTranslated((int)(float)this.x, ((int)(float)this.y + 10), 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          ImpactPlus.CustomFont2.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } else {
          GL11.glPushMatrix();
          GL11.glTranslated((int)(float)this.x, ((int)(float)this.y + 10), 0.0D);
          GL11.glScaled(this.mod.size.getValDouble(), this.mod.size.getValDouble(), this.mod.size.getValDouble());
          mc.fontRenderer.drawStringWithShadow(s, 0.0F, 0.0F, this.text.getRGB());
          GL11.glPopMatrix();
        } 
      } 
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.font = this.mod.customFont.getValBoolean();
  }
}
