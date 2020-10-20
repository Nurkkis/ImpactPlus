//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.Entitys;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;

public class EntitysComponent extends Panel {
  Entitys mod;
  
  Color c;
  
  boolean font;
  
  Color text;
  
  Color color;
  
  String s;
  
  String fristr;
  
  int count;
  
  public EntitysComponent(double ix, double iy, ClickGUI parent) {
    super("Entitys", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (Entitys)ModuleManager.getModuleByName("Entitys");
    this.s = "";
    this.fristr = "";
    this.isHudComponent = true;
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
    List<Entity> entityList = new ArrayList<>(mc.world.loadedEntityList);
    Map<String, Integer> entityCounts = (Map<String, Integer>)entityList.stream().filter(Objects::nonNull).filter(e -> !(e instanceof net.minecraft.entity.player.EntityPlayer)).collect(Collectors.groupingBy(EntitysComponent::getEntityName, 
          Collectors.reducing(Integer.valueOf(0), ent -> (ent instanceof EntityItem) ? Integer.valueOf(((EntityItem)ent).getItem().getCount()) : Integer.valueOf(1), Integer::sum)));
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
    ChatFormatting finalNumbercf = numbercf;
    entityCounts.entrySet().stream()
      .sorted(Map.Entry.comparingByValue())
      .map(entry -> (String)entry.getKey() + " " + finalNumbercf + entry.getValue())
      .forEach(entry -> {
          if (this.mod.right.getValBoolean()) {
            if (font) {
              ImpactPlus.CustomFont2.drawStringWithShadow(entry, (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(entry), ((int)(float)this.y + this.count), this.c.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(entry, ((int)(float)this.x - mc.fontRenderer.getStringWidth(entry)), ((int)(float)this.y + this.count), this.c.getRGB());
            } 
          } else if (font) {
            ImpactPlus.CustomFont2.drawStringWithShadow(entry, (int)(float)this.x, ((int)(float)this.y + this.count), this.c.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(entry, (int)(float)this.x, ((int)(float)this.y + this.count), this.c.getRGB());
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
      List<Entity> entityList = new ArrayList<>(mc.world.loadedEntityList);
      Map<String, Integer> entityCounts = (Map<String, Integer>)entityList.stream().filter(Objects::nonNull).filter(e -> !(e instanceof net.minecraft.entity.player.EntityPlayer)).collect(Collectors.groupingBy(EntitysComponent::getEntityName, 
            Collectors.reducing(Integer.valueOf(0), ent -> (ent instanceof EntityItem) ? Integer.valueOf(((EntityItem)ent).getItem().getCount()) : Integer.valueOf(1), Integer::sum)));
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
      ChatFormatting finalNumbercf = numbercf;
      entityCounts.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .map(entry -> (String)entry.getKey() + " " + finalNumbercf + "x" + entry.getValue())
        .forEach(entry -> {
            if (this.mod.right.getValBoolean()) {
              if (font) {
                ImpactPlus.CustomFont2.drawStringWithShadow(entry, (int)(float)this.x - ImpactPlus.CustomFont2.getStringWidth(entry), ((int)(float)this.y + this.count + 10), this.c.getRGB());
              } else {
                mc.fontRenderer.drawStringWithShadow(entry, ((int)(float)this.x - mc.fontRenderer.getStringWidth(entry)), ((int)(float)this.y + this.count + 10), this.c.getRGB());
              } 
            } else if (font) {
              ImpactPlus.CustomFont2.drawStringWithShadow(entry, (int)(float)this.x, ((int)(float)this.y + this.count + 10), this.c.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(entry, (int)(float)this.x, ((int)(float)this.y + this.count + 10), this.c.getRGB());
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
  
  private static String getEntityName(@Nonnull Entity entity) {
    Entitys mod = (Entitys)ModuleManager.getModuleByName("Entitys");
    ChatFormatting bracketcf = null;
    if (mod.bracketcolour.getValString().equalsIgnoreCase("None"))
      bracketcf = ChatFormatting.RESET; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("White"))
      bracketcf = ChatFormatting.WHITE; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Black"))
      bracketcf = ChatFormatting.BLACK; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Gray"))
      bracketcf = ChatFormatting.GRAY; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Dark Gray"))
      bracketcf = ChatFormatting.DARK_GRAY; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Aqua"))
      bracketcf = ChatFormatting.AQUA; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Dark Aqua"))
      bracketcf = ChatFormatting.DARK_AQUA; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Blue"))
      bracketcf = ChatFormatting.BLUE; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Dark Blue"))
      bracketcf = ChatFormatting.DARK_BLUE; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Green"))
      bracketcf = ChatFormatting.GREEN; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Dark Green"))
      bracketcf = ChatFormatting.DARK_GREEN; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Light Purple"))
      bracketcf = ChatFormatting.LIGHT_PURPLE; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Dark Purple"))
      bracketcf = ChatFormatting.DARK_PURPLE; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Red"))
      bracketcf = ChatFormatting.RED; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Dark Red"))
      bracketcf = ChatFormatting.DARK_RED; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Yellow"))
      bracketcf = ChatFormatting.YELLOW; 
    if (mod.bracketcolour.getValString().equalsIgnoreCase("Gold"))
      bracketcf = ChatFormatting.GOLD; 
    ChatFormatting numbercf = null;
    if (mod.numbercolour.getValString().equalsIgnoreCase("None"))
      numbercf = ChatFormatting.RESET; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("White"))
      numbercf = ChatFormatting.WHITE; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Black"))
      numbercf = ChatFormatting.BLACK; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Gray"))
      numbercf = ChatFormatting.GRAY; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Dark Gray"))
      numbercf = ChatFormatting.DARK_GRAY; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Aqua"))
      numbercf = ChatFormatting.AQUA; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Dark Aqua"))
      numbercf = ChatFormatting.DARK_AQUA; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Blue"))
      numbercf = ChatFormatting.BLUE; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Dark Blue"))
      numbercf = ChatFormatting.DARK_BLUE; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Green"))
      numbercf = ChatFormatting.GREEN; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Dark Green"))
      numbercf = ChatFormatting.DARK_GREEN; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Light Purple"))
      numbercf = ChatFormatting.LIGHT_PURPLE; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Dark Purple"))
      numbercf = ChatFormatting.DARK_PURPLE; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Red"))
      numbercf = ChatFormatting.RED; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Dark Red"))
      numbercf = ChatFormatting.DARK_RED; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Yellow"))
      numbercf = ChatFormatting.YELLOW; 
    if (mod.numbercolour.getValString().equalsIgnoreCase("Gold"))
      numbercf = ChatFormatting.GOLD; 
    if (entity instanceof EntityItem)
      return ((EntityItem)entity).getItem().getItem().getItemStackDisplayName(((EntityItem)entity).getItem()) + " " + bracketcf + "(" + numbercf + "Item" + bracketcf + ")"; 
    if (entity instanceof net.minecraft.entity.projectile.EntityWitherSkull)
      return "Wither skull"; 
    if (entity instanceof net.minecraft.entity.item.EntityEnderCrystal)
      return "End crystal"; 
    if (entity instanceof net.minecraft.entity.item.EntityEnderPearl)
      return "Thrown ender pearl"; 
    if (entity instanceof net.minecraft.entity.item.EntityMinecart)
      return "Minecart"; 
    if (entity instanceof net.minecraft.entity.item.EntityItemFrame)
      return "Item frame"; 
    if (entity instanceof net.minecraft.entity.projectile.EntityEgg)
      return "Thrown egg"; 
    if (entity instanceof net.minecraft.entity.projectile.EntitySnowball)
      return "Thrown snowball"; 
    return entity.getName();
  }
}
