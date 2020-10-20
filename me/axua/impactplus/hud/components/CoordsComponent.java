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
import me.axua.impactplus.module.modules.components1.Coords;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;

public class CoordsComponent extends Panel {
  Coords mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  public CoordsComponent(double ix, double iy, ClickGUI parent) {
    super("Coords", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (Coords)ModuleManager.getModuleByName("Coords");
    this.isHudComponent = true;
    this.isHudComponentPinned = true;
  }
  
  public void drawHud() {
    doStuff();
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
    ChatFormatting bracketcf = null;
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("None"))
      bracketcf = ChatFormatting.RESET; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("White"))
      bracketcf = ChatFormatting.WHITE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Black"))
      bracketcf = ChatFormatting.BLACK; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Gray"))
      bracketcf = ChatFormatting.GRAY; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Gray"))
      bracketcf = ChatFormatting.DARK_GRAY; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Aqua"))
      bracketcf = ChatFormatting.AQUA; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Aqua"))
      bracketcf = ChatFormatting.DARK_AQUA; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Blue"))
      bracketcf = ChatFormatting.BLUE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Blue"))
      bracketcf = ChatFormatting.DARK_BLUE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Green"))
      bracketcf = ChatFormatting.GREEN; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Green"))
      bracketcf = ChatFormatting.DARK_GREEN; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Light Purple"))
      bracketcf = ChatFormatting.LIGHT_PURPLE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Purple"))
      bracketcf = ChatFormatting.DARK_PURPLE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Red"))
      bracketcf = ChatFormatting.RED; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Red"))
      bracketcf = ChatFormatting.DARK_RED; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Yellow"))
      bracketcf = ChatFormatting.YELLOW; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Gold"))
      bracketcf = ChatFormatting.GOLD; 
    DecimalFormat decimalFormat = new DecimalFormat(this.mod.decimal.getValString());
    String xcoord = decimalFormat.format(mc.player.posX);
    String ycoord = decimalFormat.format(mc.player.posY);
    String zcoord = decimalFormat.format(mc.player.posZ);
    String xcoordok = "";
    String ycoordok = "";
    String zcoordok = "";
    if (mc.player.dimension == -1) {
      String otherX = decimalFormat.format(mc.player.posX * 8.0D);
      String otherZ = decimalFormat.format(mc.player.posZ * 8.0D);
      xcoordok = "X " + numbercf + xcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherX + bracketcf + "]";
      ycoordok = "Y " + numbercf + ycoord;
      zcoordok = "Z " + numbercf + zcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherZ + bracketcf + "]";
    } else {
      String otherX = decimalFormat.format(mc.player.posX / 8.0D);
      String otherZ = decimalFormat.format(mc.player.posZ / 8.0D);
      xcoordok = "X " + numbercf + xcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherX + bracketcf + "]";
      ycoordok = "Y " + numbercf + ycoord;
      zcoordok = "Z " + numbercf + zcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherZ + bracketcf + "]";
    } 
    float hi1 = ImpactPlus.CustomFont2.getStringWidth(xcoordok);
    float hi2 = ImpactPlus.CustomFont2.getStringWidth(ycoordok);
    float hi3 = ImpactPlus.CustomFont2.getStringWidth(zcoordok);
    float hi11 = mc.fontRenderer.getStringWidth(xcoordok);
    float hi22 = mc.fontRenderer.getStringWidth(ycoordok);
    float hi33 = mc.fontRenderer.getStringWidth(zcoordok);
    Color c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
    if (this.mod.rainbow.getValBoolean())
      c = Rainbow.getColor(); 
    float hi = ImpactPlus.CustomFont2.getStringWidth(getCoords());
    float hdsfgadgfa = ImpactPlus.CustomFont2.getStringWidth(getCoords2());
    float hi111 = mc.fontRenderer.getStringWidth(getCoords());
    float hi1111 = mc.fontRenderer.getStringWidth(getCoords2());
    if (this.mod.style.getValString().equals("Future")) {
      if (this.mod.right.getValBoolean()) {
        if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(xcoordok, (float)this.x - hi1, (float)this.y, this.text.getRGB());
          ImpactPlus.CustomFont2.drawStringWithShadow(ycoordok, (float)this.x - hi2, (float)this.y + 10.0F, this.text.getRGB());
          ImpactPlus.CustomFont2.drawStringWithShadow(zcoordok, (float)this.x - hi3, (float)this.y + 20.0F, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(xcoordok, (float)this.x - hi11, (float)this.y, this.text.getRGB());
          mc.fontRenderer.drawStringWithShadow(ycoordok, (float)this.x - hi22, (float)this.y + 10.0F, this.text.getRGB());
          mc.fontRenderer.drawStringWithShadow(zcoordok, (float)this.x - hi33, (float)this.y + 20.0F, this.text.getRGB());
        } 
      } else if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(xcoordok, (float)this.x, (float)this.y, this.text.getRGB());
        ImpactPlus.CustomFont2.drawStringWithShadow(ycoordok, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
        ImpactPlus.CustomFont2.drawStringWithShadow(zcoordok, (float)this.x, (float)this.y + 20.0F, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(xcoordok, (float)this.x, (float)this.y, this.text.getRGB());
        mc.fontRenderer.drawStringWithShadow(ycoordok, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
        mc.fontRenderer.drawStringWithShadow(zcoordok, (float)this.x, (float)this.y + 20.0F, this.text.getRGB());
      } 
    } else if (this.mod.style.getValString().equals("WeepCraft")) {
      if (this.mod.right.getValBoolean()) {
        if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(getCoords2(), (float)this.x - hdsfgadgfa, (float)this.y, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(getCoords2(), (float)this.x - hi1111, (float)this.y, this.text.getRGB());
        } 
      } else if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(getCoords2(), (float)this.x, (float)this.y, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(getCoords2(), (float)this.x, (float)this.y, this.text.getRGB());
      } 
    } else if (this.mod.right.getValBoolean()) {
      if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(getCoords(), (float)this.x - hi, (float)this.y, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(getCoords(), (float)this.x - hi111, (float)this.y, this.text.getRGB());
      } 
    } else if (this.font) {
      ImpactPlus.CustomFont2.drawStringWithShadow(getCoords(), (float)this.x, (float)this.y, this.text.getRGB());
    } else {
      mc.fontRenderer.drawStringWithShadow(getCoords(), (float)this.x, (float)this.y, this.text.getRGB());
    } 
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    double w = ImpactPlus.CustomFont2.getStringWidth(getCoords());
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
      if (this.mod.style.getValString().equals("Future")) {
        Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)this.height + 20, this.c.getRGB());
      } else {
        Gui.drawRect((int)this.x, (int)startY, (int)this.x + (int)this.width, (int)startY + (int)this.height, this.c.getRGB());
      } 
      Color c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
      if (this.mod.rainbow.getValBoolean())
        c = Rainbow.getColor(); 
      float hi = ImpactPlus.CustomFont2.getStringWidth(getCoords());
      float hdsfgadgfa = ImpactPlus.CustomFont2.getStringWidth(getCoords2());
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
      ChatFormatting bracketcf = null;
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("None"))
        bracketcf = ChatFormatting.RESET; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("White"))
        bracketcf = ChatFormatting.WHITE; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Black"))
        bracketcf = ChatFormatting.BLACK; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Gray"))
        bracketcf = ChatFormatting.GRAY; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Gray"))
        bracketcf = ChatFormatting.DARK_GRAY; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Aqua"))
        bracketcf = ChatFormatting.AQUA; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Aqua"))
        bracketcf = ChatFormatting.DARK_AQUA; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Blue"))
        bracketcf = ChatFormatting.BLUE; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Blue"))
        bracketcf = ChatFormatting.DARK_BLUE; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Green"))
        bracketcf = ChatFormatting.GREEN; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Green"))
        bracketcf = ChatFormatting.DARK_GREEN; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Light Purple"))
        bracketcf = ChatFormatting.LIGHT_PURPLE; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Purple"))
        bracketcf = ChatFormatting.DARK_PURPLE; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Red"))
        bracketcf = ChatFormatting.RED; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Red"))
        bracketcf = ChatFormatting.DARK_RED; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Yellow"))
        bracketcf = ChatFormatting.YELLOW; 
      if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Gold"))
        bracketcf = ChatFormatting.GOLD; 
      DecimalFormat decimalFormat = new DecimalFormat(this.mod.decimal.getValString());
      String xcoord = decimalFormat.format(mc.player.posX);
      String ycoord = decimalFormat.format(mc.player.posY);
      String zcoord = decimalFormat.format(mc.player.posZ);
      String xcoordok = "";
      String ycoordok = "";
      String zcoordok = "";
      if (mc.player.dimension == -1) {
        String otherX = decimalFormat.format(mc.player.posX * 8.0D);
        String otherZ = decimalFormat.format(mc.player.posZ * 8.0D);
        xcoordok = "X " + numbercf + xcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherX + bracketcf + "]";
        ycoordok = "Y " + numbercf + ycoord;
        zcoordok = "Z " + numbercf + zcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherZ + bracketcf + "]";
      } else {
        String otherX = decimalFormat.format(mc.player.posX / 8.0D);
        String otherZ = decimalFormat.format(mc.player.posZ / 8.0D);
        xcoordok = "X " + numbercf + xcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherX + bracketcf + "]";
        ycoordok = "Y " + numbercf + ycoord;
        zcoordok = "Z " + numbercf + zcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherZ + bracketcf + "]";
      } 
      float hi1 = ImpactPlus.CustomFont2.getStringWidth(xcoordok);
      float hi2 = ImpactPlus.CustomFont2.getStringWidth(ycoordok);
      float hi3 = ImpactPlus.CustomFont2.getStringWidth(zcoordok);
      float hi11 = mc.fontRenderer.getStringWidth(xcoordok);
      float hi22 = mc.fontRenderer.getStringWidth(ycoordok);
      float hi33 = mc.fontRenderer.getStringWidth(zcoordok);
      if (this.mod.rainbow.getValBoolean())
        c = Rainbow.getColor(); 
      float hi111 = mc.fontRenderer.getStringWidth(getCoords());
      float hi1111 = mc.fontRenderer.getStringWidth(getCoords2());
      if (this.mod.style.getValString().equals("Future")) {
        if (this.mod.right.getValBoolean()) {
          if (this.font) {
            ImpactPlus.CustomFont2.drawStringWithShadow(xcoordok, (float)this.x - hi1, (float)this.y + 10.0F, this.text.getRGB());
            ImpactPlus.CustomFont2.drawStringWithShadow(ycoordok, (float)this.x - hi2, (float)this.y + 20.0F, this.text.getRGB());
            ImpactPlus.CustomFont2.drawStringWithShadow(zcoordok, (float)this.x - hi3, (float)this.y + 30.0F, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(xcoordok, (float)this.x - hi11, (float)this.y + 10.0F, this.text.getRGB());
            mc.fontRenderer.drawStringWithShadow(ycoordok, (float)this.x - hi22, (float)this.y + 20.0F, this.text.getRGB());
            mc.fontRenderer.drawStringWithShadow(zcoordok, (float)this.x - hi33, (float)this.y + 30.0F, this.text.getRGB());
          } 
        } else if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(xcoordok, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
          ImpactPlus.CustomFont2.drawStringWithShadow(ycoordok, (float)this.x, (float)this.y + 20.0F, this.text.getRGB());
          ImpactPlus.CustomFont2.drawStringWithShadow(zcoordok, (float)this.x, (float)this.y + 30.0F, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(xcoordok, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
          mc.fontRenderer.drawStringWithShadow(ycoordok, (float)this.x, (float)this.y + 20.0F, this.text.getRGB());
          mc.fontRenderer.drawStringWithShadow(zcoordok, (float)this.x, (float)this.y + 30.0F, this.text.getRGB());
        } 
      } else if (this.mod.style.getValString().equals("WeepCraft")) {
        if (this.mod.right.getValBoolean()) {
          if (this.font) {
            ImpactPlus.CustomFont2.drawStringWithShadow(getCoords2(), (float)this.x - hdsfgadgfa, (float)this.y + 10.0F, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(getCoords2(), (float)this.x - hi1111, (float)this.y + 10.0F, this.text.getRGB());
          } 
        } else if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(getCoords2(), (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(getCoords2(), (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
        } 
      } else if (this.mod.right.getValBoolean()) {
        if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(getCoords(), (float)this.x - hi, (float)this.y + 10.0F, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(getCoords(), (float)this.x - hi111, (float)this.y + 10.0F, this.text.getRGB());
        } 
      } else if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(getCoords(), (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(getCoords(), (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
      } 
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.font = this.mod.customFont.getValBoolean();
  }
  
  private String getCoords() {
    String coords;
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
    ChatFormatting bracketcf = null;
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("None"))
      bracketcf = ChatFormatting.RESET; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("White"))
      bracketcf = ChatFormatting.WHITE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Black"))
      bracketcf = ChatFormatting.BLACK; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Gray"))
      bracketcf = ChatFormatting.GRAY; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Gray"))
      bracketcf = ChatFormatting.DARK_GRAY; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Aqua"))
      bracketcf = ChatFormatting.AQUA; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Aqua"))
      bracketcf = ChatFormatting.DARK_AQUA; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Blue"))
      bracketcf = ChatFormatting.BLUE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Blue"))
      bracketcf = ChatFormatting.DARK_BLUE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Green"))
      bracketcf = ChatFormatting.GREEN; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Green"))
      bracketcf = ChatFormatting.DARK_GREEN; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Light Purple"))
      bracketcf = ChatFormatting.LIGHT_PURPLE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Purple"))
      bracketcf = ChatFormatting.DARK_PURPLE; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Red"))
      bracketcf = ChatFormatting.RED; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Dark Red"))
      bracketcf = ChatFormatting.DARK_RED; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Yellow"))
      bracketcf = ChatFormatting.YELLOW; 
    if (this.mod.bracketcolour.getValString().equalsIgnoreCase("Gold"))
      bracketcf = ChatFormatting.GOLD; 
    DecimalFormat decimalFormat = new DecimalFormat(this.mod.decimal.getValString());
    String xcoord = decimalFormat.format(mc.player.posX);
    String ycoord = decimalFormat.format(mc.player.posY);
    String zcoord = decimalFormat.format(mc.player.posZ);
    if (mc.player.dimension == -1) {
      String otherX = decimalFormat.format(mc.player.posX * 8.0D);
      String otherZ = decimalFormat.format(mc.player.posZ * 8.0D);
      coords = "XYZ " + numbercf + xcoord + ", " + ycoord + ", " + zcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherX + ", " + otherZ + bracketcf + "]";
    } else {
      String otherX = decimalFormat.format(mc.player.posX / 8.0D);
      String otherZ = decimalFormat.format(mc.player.posZ / 8.0D);
      coords = "XYZ " + numbercf + xcoord + ", " + ycoord + ", " + zcoord + bracketcf + " [" + ChatFormatting.RESET + numbercf + otherX + ", " + otherZ + bracketcf + "]";
    } 
    return coords;
  }
  
  private String getCoords2() {
    String coords2;
    DecimalFormat decimalFormat = new DecimalFormat(this.mod.decimal.getValString());
    String xcoord = decimalFormat.format(mc.player.posX);
    String ycoord = decimalFormat.format(mc.player.posY);
    String zcoord = decimalFormat.format(mc.player.posZ);
    if (mc.player.dimension == -1) {
      String otherX = decimalFormat.format(mc.player.posX * 8.0D);
      String otherZ = decimalFormat.format(mc.player.posZ * 8.0D);
      coords2 = ChatFormatting.YELLOW + "X " + ChatFormatting.DARK_RED + xcoord + ChatFormatting.YELLOW + " Y " + ChatFormatting.DARK_RED + ycoord + ChatFormatting.YELLOW + " Z " + ChatFormatting.DARK_RED + zcoord + " [" + ChatFormatting.YELLOW + "X " + ChatFormatting.DARK_RED + otherX + ChatFormatting.YELLOW + " Z " + ChatFormatting.DARK_RED + otherZ + "]";
    } else {
      String otherX = decimalFormat.format(mc.player.posX / 8.0D);
      String otherZ = decimalFormat.format(mc.player.posZ / 8.0D);
      coords2 = ChatFormatting.YELLOW + "X " + ChatFormatting.DARK_RED + xcoord + ChatFormatting.YELLOW + " Y " + ChatFormatting.DARK_RED + ycoord + ChatFormatting.YELLOW + " Z " + ChatFormatting.DARK_RED + zcoord + " [" + ChatFormatting.YELLOW + "X " + ChatFormatting.DARK_RED + otherX + ChatFormatting.YELLOW + " Z " + ChatFormatting.DARK_RED + otherZ + "]";
    } 
    return coords2;
  }
}
