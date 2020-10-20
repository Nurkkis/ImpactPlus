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
import me.axua.impactplus.module.modules.components2.WelcomerGui;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;

public class WelcomerComponent extends Panel {
  WelcomerGui mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  String msg;
  
  public WelcomerComponent(double ix, double iy, ClickGUI parent) {
    super("Welcome", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (WelcomerGui)ModuleManager.getModuleByName("Welcome");
    this.isHudComponent = true;
    this.isHudComponentPinned = true;
  }
  
  public void drawHud() {
    doStuff();
    float hi = ImpactPlus.CustomFont2.getStringWidth(this.msg);
    float hi2 = mc.fontRenderer.getStringWidth(this.msg);
    if (this.mod.right.getValBoolean()) {
      if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(this.msg, (float)this.x - hi, (float)this.y, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(this.msg, (float)this.x - hi2, (float)this.y, this.text.getRGB());
      } 
    } else if (this.font) {
      ImpactPlus.CustomFont2.drawStringWithShadow(this.msg, (float)this.x, (float)this.y, this.text.getRGB());
    } else {
      mc.fontRenderer.drawStringWithShadow(this.msg, (float)this.x, (float)this.y, this.text.getRGB());
    } 
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    double w = (ImpactPlus.CustomFont2.getStringWidth(this.msg) + 2.0F);
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
      float hi = ImpactPlus.CustomFont2.getStringWidth(this.msg);
      float hi2 = mc.fontRenderer.getStringWidth(this.msg);
      if (this.mod.right.getValBoolean()) {
        if (this.font) {
          ImpactPlus.CustomFont2.drawStringWithShadow(this.msg, (float)this.x - hi, (float)this.y + 10.0F, this.text.getRGB());
        } else {
          mc.fontRenderer.drawStringWithShadow(this.msg, (float)this.x - hi2, (float)this.y + 10.0F, this.text.getRGB());
        } 
      } else if (this.font) {
        ImpactPlus.CustomFont2.drawStringWithShadow(this.msg, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
      } else {
        mc.fontRenderer.drawStringWithShadow(this.msg, (float)this.x, (float)this.y + 10.0F, this.text.getRGB());
      } 
    } 
  }
  
  private void doStuff() {
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
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.font = this.mod.customFont.getValBoolean();
    if (this.mod.message.getValString().equalsIgnoreCase("Welcome1"))
      this.msg = "Welcome, " + numbercf + mc.player.getName(); 
    if (this.mod.message.getValString().equalsIgnoreCase("Welcome2"))
      this.msg = "Welcome, " + numbercf + mc.player.getName() + ChatFormatting.RESET + " ^_^"; 
    if (this.mod.message.getValString().equalsIgnoreCase("Hello1"))
      this.msg = "Hello " + numbercf + mc.player.getName(); 
    if (this.mod.message.getValString().equalsIgnoreCase("Hello2"))
      this.msg = "Hello " + numbercf + mc.player.getName() + ChatFormatting.RESET + " ^_^"; 
  }
}
