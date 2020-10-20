//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.Inventory;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class InventoryComponent extends Panel {
  Inventory mod;
  
  Color c;
  
  Color text;
  
  Color color;
  
  ResourceLocation resource;
  
  public InventoryComponent(double ix, double iy, ClickGUI parent) {
    super("Inventory", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (Inventory)ModuleManager.getModuleByName("Inventory");
    this.isHudComponent = true;
    this.isHudComponentPinned = true;
    this.resource = new ResourceLocation("textures/gui/container/shulker_box.png");
  }
  
  public void drawHud() {
    doStuff();
    if (this.mod.mode.getValString().equalsIgnoreCase("Rect"))
      if (this.mod.rainbow.getValBoolean()) {
        Gui.drawRect((int)this.x, (int)this.y, (int)this.x + 155, (int)this.y + 54, this.text.getRGB());
      } else {
        Gui.drawRect((int)this.x, (int)this.y, (int)this.x + 155, (int)this.y + 54, ColourConverter.rgbToInt(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt(), this.mod.alpha.getValInt()));
      }  
    if (this.mod.mode.getValString().equalsIgnoreCase("Rect") && this.mod.hotbar.getValBoolean())
      if (this.mod.rainbow.getValBoolean()) {
        Gui.drawRect((int)this.x, (int)this.y + 54, (int)this.x + 155, (int)this.y + 70, this.text.getRGB());
      } else {
        Gui.drawRect((int)this.x, (int)this.y + 54, (int)this.x + 155, (int)this.y + 70, ColourConverter.rgbToInt(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt(), this.mod.alpha.getValInt()));
      }  
    if (this.mod.mode.getValString().equalsIgnoreCase("Rect") && this.mod.crafting.getValBoolean()) {
      ItemStack one1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(1);
      ItemStack two1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(2);
      ItemStack three1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(3);
      ItemStack four1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(4);
      if (!one1.getItem().getItemStackDisplayName(one1).equals("Air") || !two1.getItem().getItemStackDisplayName(two1).equals("Air") || !three1.getItem().getItemStackDisplayName(three1).equals("Air") || !four1.getItem().getItemStackDisplayName(four1).equals("Air"))
        if (this.mod.rainbow.getValBoolean()) {
          Gui.drawRect((int)this.x + 155, (int)this.y, (int)this.x + 189, (int)this.y + 36, this.text.getRGB());
        } else {
          Gui.drawRect((int)this.x + 155, (int)this.y, (int)this.x + 189, (int)this.y + 36, ColourConverter.rgbToInt(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt(), this.mod.alpha.getValInt()));
        }  
    } 
    drawInventory((int)this.x, (int)this.y);
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    ItemStack one1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(1);
    ItemStack two1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(2);
    ItemStack three1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(3);
    ItemStack four1 = (ItemStack)mc.player.inventoryContainer.getInventory().get(4);
    if (this.mod.crafting.getValBoolean() && one1.getItem().getItemStackDisplayName(one1).equals("Air") && two1.getItem().getItemStackDisplayName(two1).equals("Air") && three1.getItem().getItemStackDisplayName(three1).equals("Air") && four1.getItem().getItemStackDisplayName(four1).equals("Air")) {
      this.width = 155.0D;
    } else if (!one1.getItem().getItemStackDisplayName(one1).equals("Air") || !two1.getItem().getItemStackDisplayName(two1).equals("Air") || !three1.getItem().getItemStackDisplayName(three1).equals("Air") || !four1.getItem().getItemStackDisplayName(four1).equals("Air")) {
      this.width = 189.0D;
    } else {
      this.width = 155.0D;
    } 
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
      if (this.mod.mode.getValString().equalsIgnoreCase("Rect"))
        if (this.mod.rainbow.getValBoolean()) {
          Gui.drawRect((int)this.x, (int)this.y + 10, (int)this.x + 155, (int)this.y + 64, this.text.getRGB());
        } else {
          Gui.drawRect((int)this.x, (int)this.y + 10, (int)this.x + 155, (int)this.y + 64, ColourConverter.rgbToInt(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt(), this.mod.alpha.getValInt()));
        }  
      if (this.mod.mode.getValString().equalsIgnoreCase("Rect") && this.mod.hotbar.getValBoolean())
        if (this.mod.rainbow.getValBoolean()) {
          Gui.drawRect((int)this.x, (int)this.y + 64, (int)this.x + 155, (int)this.y + 80, this.text.getRGB());
        } else {
          Gui.drawRect((int)this.x, (int)this.y + 64, (int)this.x + 155, (int)this.y + 80, ColourConverter.rgbToInt(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt(), this.mod.alpha.getValInt()));
        }  
      if (this.mod.mode.getValString().equalsIgnoreCase("Rect") && this.mod.crafting.getValBoolean() && (
        !one1.getItem().getItemStackDisplayName(one1).equals("Air") || !two1.getItem().getItemStackDisplayName(two1).equals("Air") || !three1.getItem().getItemStackDisplayName(three1).equals("Air") || !four1.getItem().getItemStackDisplayName(four1).equals("Air")))
        if (this.mod.rainbow.getValBoolean()) {
          Gui.drawRect((int)this.x + 155, (int)this.y + 10, (int)this.x + 189, (int)this.y + 46, this.text.getRGB());
        } else {
          Gui.drawRect((int)this.x + 155, (int)this.y + 10, (int)this.x + 189, (int)this.y + 46, ColourConverter.rgbToInt(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt(), this.mod.alpha.getValInt()));
        }  
      drawInventory((int)this.x, (int)startY);
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColorWithOpacity(this.mod.alpha.getValInt()) : this.color;
  }
  
  public void drawInventory(int x, int y) {
    GlStateManager.clear(256);
    NonNullList<ItemStack> items = (Minecraft.getMinecraft()).player.inventory.mainInventory;
    for (int size = items.size(), item = 9; item < size; item++) {
      int slotX = x + 1 + item % 9 * 17;
      int slotY = y + 1 + (item / 9 - 1) * 17;
      RenderHelper.enableGUIStandardItemLighting();
      if (this.mod.hotbar.getValBoolean())
        for (int i = 0; i < 9; i++) {
          int offsetX = x + 1 + i % 9 * 17;
          int offsetY = y + 52;
          ItemStack itemStack = (ItemStack)mc.player.inventory.mainInventory.get(i);
          mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, offsetX, offsetY);
          mc.getRenderItem().renderItemOverlays(mc.fontRenderer, itemStack, offsetX, offsetY);
        }  
      mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)items.get(item), slotX, slotY);
      mc.getRenderItem().renderItemOverlays(mc.fontRenderer, (ItemStack)items.get(item), slotX, slotY);
      if (this.mod.crafting.getValBoolean())
        for (int i = 1; i < 5; i++) {
          ItemStack itemStack = (ItemStack)mc.player.inventoryContainer.getInventory().get(i);
          int offsetX = x + 9;
          int offsetY = y + 1;
          switch (i) {
            case 1:
            case 2:
              offsetX += 128 + i * 17;
              break;
            case 3:
            case 4:
              offsetX += 128 + (i - 2) * 17;
              offsetY += 17;
              break;
          } 
          mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, offsetX, offsetY);
          mc.getRenderItem().renderItemOverlays(mc.fontRenderer, itemStack, offsetX, offsetY);
        }  
      RenderHelper.disableStandardItemLighting();
    } 
  }
  
  public static class ColourConverter {
    static int rgbToInt(int r, int g, int b, int a) {
      return r << 16 | g << 8 | b | a << 24;
    }
  }
}
