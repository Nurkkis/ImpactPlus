//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.ArmorHUD;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

public class ArmorComponent extends Panel {
  ArmorHUD mod;
  
  boolean vertical;
  
  boolean reverse;
  
  Color c;
  
  public ArmorComponent(double ix, double iy, ClickGUI parent) {
    super("Armor", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (ArmorHUD)ModuleManager.getModuleByName("ArmorHUD");
    this.isHudComponent = true;
  }
  
  public void drawHud() {
    drawArmor((int)this.x, (int)this.y);
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    this.c = new Color(50, 50, 50, 100);
    if (this.isHudComponentPinned)
      this.c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100); 
    if (this.dragging) {
      this.x = this.x2 + mouseX;
      this.y = this.y2 + mouseY;
    } 
    Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, this.c.getRGB());
    if (!this.extended)
      ImpactPlus.CustomFont2.drawStringWithShadow(this.title, (int)this.x, (int)(this.y + 5.0D - (FontUtil.getFontHeight() / 2.0F) - 2.0D), -1); 
    this.width = FontUtil.getStringWidth(this.title);
    this.height = 20.0D;
    if (this.extended) {
      double startY = this.y + 15.0D;
      if (this.vertical) {
        this.width = 20.0D;
        this.height = 72.0D;
      } else {
        this.width = 72.0D;
      } 
      drawArmor((int)this.x, (int)this.y);
    } 
  }
  
  private void drawArmor(int x, int y) {
    this.vertical = this.mod.vertical.getValBoolean();
    this.reverse = this.mod.reverse.getValBoolean();
    int i = 0;
    List<ItemStack> armor = new ArrayList<>();
    for (ItemStack is : mc.player.getArmorInventoryList())
      armor.add(is); 
    if (this.reverse)
      Collections.reverse(armor); 
    for (ItemStack is : armor) {
      int yy = y;
      int xx = x + i;
      if (this.vertical) {
        yy = y + i;
        xx = x;
      } 
      RenderHelper.enableGUIStandardItemLighting();
      mc.getRenderItem().renderItemAndEffectIntoGUI(is, xx, yy);
      mc.getRenderItem().renderItemOverlays(mc.fontRenderer, is, xx, yy);
      RenderHelper.disableStandardItemLighting();
      i += 18;
    } 
  }
}
