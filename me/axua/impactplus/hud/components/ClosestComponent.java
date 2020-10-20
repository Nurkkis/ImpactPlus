//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.clickgui.util.ColorUtil;
import me.axua.impactplus.gui.clickgui.util.FontUtil;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.components1.Closest;
import me.axua.impactplus.util.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

public class ClosestComponent extends Panel {
  Closest mod;
  
  Color c;
  
  Color text;
  
  Color color;
  
  Color bgtext;
  
  Color bgcolor;
  
  public ClosestComponent(double ix, double iy, ClickGUI parent) {
    super("Closest", ix, iy, 10.0D, 10.0D, false, parent);
    this.mod = (Closest)ModuleManager.getModuleByName("Closest");
    this.isHudComponent = true;
  }
  
  public void drawHud() {
    doStuff();
    if (this.mod.rainbow.getValBoolean()) {
      this.c = Rainbow.getColor();
    } else {
      this.c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
    } 
    EntityPlayer l_Player = mc.world.loadedEntityList.stream().filter(p_Entity -> p_Entity instanceof EntityPlayer).filter(p_Entity -> (p_Entity != mc.player)).min(Comparator.comparing(p_Entity -> Float.valueOf(mc.player.getDistance(p_Entity)))).orElse(null);
    if (l_Player != null) {
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
      if (this.mod.bgrainbow.getValBoolean()) {
        Gui.drawRect((int)this.x, (int)this.y, (int)this.x + 160, (int)this.y + 100, this.bgtext.getRGB());
      } else {
        Gui.drawRect((int)this.x, (int)this.y, (int)this.x + 160, (int)this.y + 100, ColourConverter.rgbToInt(this.mod.bgred.getValInt(), this.mod.bggreen.getValInt(), this.mod.bgblue.getValInt(), this.mod.bgalpha.getValInt()));
      } 
      int p = -1;
      if (mc.getConnection() == null || mc.getConnection().getPlayerInfo(l_Player.getName()) == null) {
        p = -1;
      } else {
        p = mc.getConnection().getPlayerInfo(l_Player.getName()).getResponseTime();
      } 
      String Name = l_Player.getName();
      int Healt = Math.round(l_Player.getHealth() + l_Player.getAbsorptionAmount());
      String Health = "" + Healt;
      int Distanc = (int)mc.player.getDistance((Entity)l_Player);
      InventoryPlayer items = l_Player.inventory;
      ItemStack inHand = l_Player.getHeldItemMainhand();
      ItemStack boots = items.armorItemInSlot(0);
      ItemStack leggings = items.armorItemInSlot(1);
      ItemStack body = items.armorItemInSlot(2);
      ItemStack helm = items.armorItemInSlot(3);
      ItemStack offHand = l_Player.getHeldItemOffhand();
      String Distance = "" + Distanc;
      String Ping = "" + p;
      String helm2 = helm.getItem().getItemStackDisplayName(helm);
      String body2 = body.getItem().getItemStackDisplayName(body);
      String leggings2 = leggings.getItem().getItemStackDisplayName(leggings);
      String boots2 = boots.getItem().getItemStackDisplayName(boots);
      GL11.glPushMatrix();
      GL11.glTranslated(((int)(float)this.x + 45), ((int)(float)this.y + 20), 0.0D);
      GL11.glScaled(0.9D, 0.9D, 0.9D);
      if (Friends.isFriend(Name)) {
        ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.AQUA + "Friend" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
      } else if (helm2.equals("Diamond Helmet") && body2.equals("Diamond Chestplate") && leggings2.equals("Diamond Leggings") && boots2.equals("Diamond Boots")) {
        ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.RED + "Threat" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
      } else if (helm2.equals("Diamond Helmet") && body2.equals("Elytra") && leggings2.equals("Diamond Leggings") && boots2.equals("Diamond Boots")) {
        ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.YELLOW + "Wasp" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
      } else if (helm2.equals("Air") && body2.equals("Air") && leggings2.equals("Air") && boots2.equals("Air")) {
        ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.GREEN + "Naked" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
      } else {
        ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.LIGHT_PURPLE + "Newfag" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
      } 
      GL11.glPopMatrix();
      ImpactPlus.CustomFont2.drawStringWithShadow(Name, ((int)(float)this.x + 45), ((int)(float)this.y + 2), this.text.getRGB());
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GuiInventory.drawEntityOnScreen((int)this.x + 21, (int)this.y + 62, 30, (float)((int)(this.x + 51.0D) - this.x + 50.0D), (float)((int)(this.y + 75.0D - 50.0D) - this.y + 12.0D), (EntityLivingBase)l_Player);
      int i = 0;
      List<ItemStack> armor = new ArrayList<>();
      for (ItemStack is : l_Player.getArmorInventoryList())
        armor.add(is); 
      Collections.reverse(armor);
      for (ItemStack is : armor) {
        int j = (int)(this.y + 22.0D);
        int k = (int)(this.x + i + 42.0D);
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(is, k, j);
        mc.getRenderItem().renderItemOverlays(mc.fontRenderer, is, k, j);
        RenderHelper.disableStandardItemLighting();
        i += 18;
      } 
      int yy = (int)(this.y + 22.0D);
      int xx = (int)(this.x + 114.0D);
      RenderHelper.enableGUIStandardItemLighting();
      mc.getRenderItem().renderItemAndEffectIntoGUI(inHand, xx, yy);
      mc.getRenderItem().renderItemOverlays(mc.fontRenderer, inHand, xx, yy);
      RenderHelper.disableStandardItemLighting();
      ItemStack inOffHand = l_Player.getHeldItemOffhand();
      int yyy = (int)(this.y + 22.0D);
      int xxx = (int)(this.x + 132.0D);
      RenderHelper.enableGUIStandardItemLighting();
      mc.getRenderItem().renderItemAndEffectIntoGUI(inOffHand, xxx, yyy);
      mc.getRenderItem().renderItemOverlays(mc.fontRenderer, inOffHand, xxx, yyy);
      RenderHelper.disableStandardItemLighting();
      EnchantEntry[] enchants = { 
          new EnchantEntry(Enchantments.PROTECTION, "Pro"), new EnchantEntry(Enchantments.BLAST_PROTECTION, "Bla"), new EnchantEntry(Enchantments.FIRE_PROTECTION, "Fpr"), new EnchantEntry(Enchantments.PROJECTILE_PROTECTION, "Ppr"), new EnchantEntry(Enchantments.UNBREAKING, "Unb"), new EnchantEntry(Enchantments.MENDING, "Men"), new EnchantEntry(Enchantments.AQUA_AFFINITY, "Aqu"), new EnchantEntry(Enchantments.RESPIRATION, "Res"), new EnchantEntry(Enchantments.FEATHER_FALLING, "Fea"), new EnchantEntry(Enchantments.DEPTH_STRIDER, "Dep"), 
          new EnchantEntry(Enchantments.FROST_WALKER, "Fro"), new EnchantEntry(Enchantments.THORNS, "Thr"), new EnchantEntry(Enchantments.SHARPNESS, "Sha"), new EnchantEntry(Enchantments.FIRE_ASPECT, "Fia"), new EnchantEntry(Enchantments.KNOCKBACK, "Knb"), new EnchantEntry(Enchantments.POWER, "Pow"), new EnchantEntry(Enchantments.BINDING_CURSE, "Bin"), new EnchantEntry(Enchantments.SMITE, "Smi"), new EnchantEntry(Enchantments.BANE_OF_ARTHROPODS, "Ban"), new EnchantEntry(Enchantments.LOOTING, "Loo"), 
          new EnchantEntry(Enchantments.SWEEPING, "Swe"), new EnchantEntry(Enchantments.EFFICIENCY, "Eff"), new EnchantEntry(Enchantments.SILK_TOUCH, "Sil"), new EnchantEntry(Enchantments.FORTUNE, "For"), new EnchantEntry(Enchantments.FLAME, "Fla"), new EnchantEntry(Enchantments.LUCK_OF_THE_SEA, "Luc"), new EnchantEntry(Enchantments.LURE, "Lur"), new EnchantEntry(Enchantments.PUNCH, "Pun"), new EnchantEntry(Enchantments.VANISHING_CURSE, "Van") };
      int lolok = 0;
      int lolok2 = 0;
      int lolok3 = 0;
      int lolok4 = 0;
      int lolok5 = 0;
      int lolok6 = 0;
      EnchantEntry[] array;
      for (int length = (array = enchants).length, lolokq = 0; lolokq < length; lolokq++) {
        EnchantEntry enchant = array[lolokq];
        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), helm);
        String levelDisplay = "" + level;
        if (level > 10)
          levelDisplay = "10+"; 
        if (level > 0) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 44), ((int)(float)this.y + 44 + lolok), 0.0D);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          if (enchant.getName().equals("Van")) {
            mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } 
          lolok += 5;
          GL11.glPopMatrix();
        } 
      } 
      for (int length2 = (array = enchants).length, lolok2q = 0; lolok2q < length2; lolok2q++) {
        EnchantEntry enchant = array[lolok2q];
        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), body);
        String levelDisplay = "" + level;
        if (level > 10)
          levelDisplay = "10+"; 
        if (level > 0) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 62), ((int)(float)this.y + 44 + lolok2), 0.0D);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          if (enchant.getName().equals("Van")) {
            mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } 
          lolok2 += 5;
          GL11.glPopMatrix();
        } 
      } 
      for (int length3 = (array = enchants).length, lolok3q = 0; lolok3q < length3; lolok3q++) {
        EnchantEntry enchant = array[lolok3q];
        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), leggings);
        String levelDisplay = "" + level;
        if (level > 10)
          levelDisplay = "10+"; 
        if (level > 0) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 80), ((int)(float)this.y + 44 + lolok3), 0.0D);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          if (enchant.getName().equals("Van")) {
            mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } 
          lolok3 += 5;
          GL11.glPopMatrix();
        } 
      } 
      for (int length4 = (array = enchants).length, lolok4q = 0; lolok4q < length4; lolok4q++) {
        EnchantEntry enchant = array[lolok4q];
        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), boots);
        String levelDisplay = "" + level;
        if (level > 10)
          levelDisplay = "10+"; 
        if (level > 0) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 98), ((int)(float)this.y + 44 + lolok4), 0.0D);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          if (enchant.getName().equals("Van")) {
            mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } 
          lolok4 += 5;
          GL11.glPopMatrix();
        } 
      } 
      for (int length5 = (array = enchants).length, lolok5q = 0; lolok5q < length5; lolok5q++) {
        EnchantEntry enchant = array[lolok5q];
        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), inHand);
        String levelDisplay = "" + level;
        if (level > 10)
          levelDisplay = "10+"; 
        if (level > 0) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 116), ((int)(float)this.y + 44 + lolok5), 0.0D);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          if (enchant.getName().equals("Van")) {
            mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } 
          lolok5 += 5;
          GL11.glPopMatrix();
        } 
      } 
      for (int length6 = (array = enchants).length, lolok6q = 0; lolok6q < length6; lolok6q++) {
        EnchantEntry enchant = array[lolok6q];
        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), offHand);
        String levelDisplay = "" + level;
        if (level > 10)
          levelDisplay = "10+"; 
        if (level > 0) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 134), ((int)(float)this.y + 44 + lolok6), 0.0D);
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          if (enchant.getName().equals("Van")) {
            mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } else {
            mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
          } 
          lolok6 += 5;
          GL11.glPopMatrix();
        } 
      } 
      GL11.glPushMatrix();
      GL11.glTranslated(((int)(float)this.x + 2), ((int)(float)this.y + 81), 0.0D);
      GL11.glScaled(0.9D, 0.9D, 0.9D);
      if (l_Player.isPotionActive(MobEffects.STRENGTH)) {
        DecimalFormat format1 = new DecimalFormat("0");
        DecimalFormat format2 = new DecimalFormat("00");
        int duration = l_Player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() / 20;
        int amplifier = l_Player.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier() + 1;
        double p1 = (duration % 60);
        double p2 = (duration / 60);
        double p3 = p2 % 60.0D;
        String minutes = format1.format(p3);
        String seconds = format2.format(p1);
        ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.RED + "Strength " + amplifier + TextFormatting.RESET + numbercf + " " + minutes + ":" + seconds, 0.0F, 0.0F, this.text.getRGB());
      } else {
        ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.RED + "Strength" + TextFormatting.RESET + numbercf + " None", 0.0F, 0.0F, this.text.getRGB());
      } 
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslated(((int)(float)this.x + 2), ((int)(float)this.y + 89), 0.0D);
      GL11.glScaled(0.9D, 0.9D, 0.9D);
      if (l_Player.isPotionActive(MobEffects.WEAKNESS)) {
        DecimalFormat format1 = new DecimalFormat("0");
        DecimalFormat format2 = new DecimalFormat("00");
        int duration = l_Player.getActivePotionEffect(MobEffects.WEAKNESS).getDuration() / 20;
        int amplifier = l_Player.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier() + 1;
        double p1 = (duration % 60);
        double p2 = (duration / 60);
        double p3 = p2 % 60.0D;
        String minutes = format1.format(p3);
        String seconds = format2.format(p1);
        ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.GRAY + "Weakness " + amplifier + TextFormatting.RESET + numbercf + " " + minutes + ":" + seconds, 0.0F, 0.0F, this.text.getRGB());
      } else {
        ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.GRAY + "Weakness" + TextFormatting.RESET + numbercf + " None", 0.0F, 0.0F, this.text.getRGB());
      } 
      GL11.glPopMatrix();
      if (Healt == 20) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141291);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 2, (int)this.x + 158, (int)this.y + 80, -1336541355);
      } 
      if (Healt == 19) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141291);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 6, (int)this.x + 158, (int)this.y + 80, -1336541355);
      } 
      if (Healt == 18) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -16733696);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 10, (int)this.x + 158, (int)this.y + 80, -1342133760);
      } 
      if (Healt == 17) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -16733696);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 14, (int)this.x + 158, (int)this.y + 80, -1342133760);
      } 
      if (Healt == 16) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 18, (int)this.x + 158, (int)this.y + 80, -1325400235);
      } 
      if (Healt == 15) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 22, (int)this.x + 158, (int)this.y + 80, -1325400235);
      } 
      if (Healt == 14) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 26, (int)this.x + 158, (int)this.y + 80, -1325400235);
      } 
      if (Healt == 13) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 30, (int)this.x + 158, (int)this.y + 80, -1325400235);
      } 
      if (Healt == 12) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 34, (int)this.x + 158, (int)this.y + 80, -1325422080);
      } 
      if (Healt == 11) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 38, (int)this.x + 158, (int)this.y + 80, -1325422080);
      } 
      if (Healt == 10) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 42, (int)this.x + 158, (int)this.y + 80, -1325422080);
      } 
      if (Healt == 9) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 46, (int)this.x + 158, (int)this.y + 80, -1325422080);
      } 
      if (Healt == 8) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -43691);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 50, (int)this.x + 158, (int)this.y + 80, -1325443755);
      } 
      if (Healt == 7) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -43691);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 54, (int)this.x + 158, (int)this.y + 80, -1325443755);
      } 
      if (Healt == 6) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -43691);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 58, (int)this.x + 158, (int)this.y + 80, -1325443755);
      } 
      if (Healt == 5) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 62, (int)this.x + 158, (int)this.y + 80, -1331036160);
      } 
      if (Healt == 4) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 66, (int)this.x + 158, (int)this.y + 80, -1331036160);
      } 
      if (Healt == 3) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 70, (int)this.x + 158, (int)this.y + 80, -1331036160);
      } 
      if (Healt == 2) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 74, (int)this.x + 158, (int)this.y + 80, -1331036160);
      } 
      if (Healt == 1) {
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
        GL11.glPopMatrix();
        Gui.drawRect((int)this.x + 150, (int)this.y + 78, (int)this.x + 158, (int)this.y + 80, -1331036160);
      } 
      if (Healt > 20) {
        Gui.drawRect((int)this.x + 150, (int)this.y + 2, (int)this.x + 158, (int)this.y + 80, -1336541355);
        if (Healt == 21) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 78, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 22) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 74, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 23) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 70, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 24) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 66, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 25) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 62, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 26) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 58, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 27) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 54, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 28) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 50, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 29) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 46, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 30) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 42, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 31) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 38, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 32) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 34, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 33) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 30, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 34) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 26, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 35) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 22, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
        if (Healt == 36) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 81), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 18, (int)this.x + 158, (int)this.y + 80, -1336541185);
        } 
      } 
    } 
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    doStuff();
    this.width = 160.0D;
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
      if (this.mod.rainbow.getValBoolean()) {
        this.c = Rainbow.getColor();
      } else {
        this.c = new Color((int)this.mod.red.getValDouble(), (int)this.mod.green.getValDouble(), (int)this.mod.blue.getValDouble());
      } 
      EntityPlayer l_Player = mc.world.loadedEntityList.stream().filter(p_Entity -> p_Entity instanceof EntityPlayer).filter(p_Entity -> (p_Entity != mc.player)).min(Comparator.comparing(p_Entity -> Float.valueOf(mc.player.getDistance(p_Entity)))).orElse(null);
      if (l_Player == null) {
        if (this.mod.bgrainbow.getValBoolean()) {
          Gui.drawRect((int)this.x, (int)this.y + 10, (int)this.x + 160, (int)this.y + 110, this.bgtext.getRGB());
        } else {
          Gui.drawRect((int)this.x, (int)this.y + 10, (int)this.x + 160, (int)this.y + 110, ColourConverter.rgbToInt(this.mod.bgred.getValInt(), this.mod.bggreen.getValInt(), this.mod.bgblue.getValInt(), this.mod.bgalpha.getValInt()));
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
        if (this.mod.bgrainbow.getValBoolean()) {
          Gui.drawRect((int)this.x, (int)this.y + 10, (int)this.x + 160, (int)this.y + 110, this.bgtext.getRGB());
        } else {
          Gui.drawRect((int)this.x, (int)this.y + 10, (int)this.x + 160, (int)this.y + 110, ColourConverter.rgbToInt(this.mod.bgred.getValInt(), this.mod.bggreen.getValInt(), this.mod.bgblue.getValInt(), this.mod.bgalpha.getValInt()));
        } 
        int p = -1;
        if (mc.getConnection() == null || mc.getConnection().getPlayerInfo(l_Player.getName()) == null) {
          p = -1;
        } else {
          p = mc.getConnection().getPlayerInfo(l_Player.getName()).getResponseTime();
        } 
        String Name = l_Player.getName();
        int Healt = Math.round(l_Player.getHealth() + l_Player.getAbsorptionAmount());
        String Health = "" + Healt;
        int Distanc = (int)mc.player.getDistance((Entity)l_Player);
        InventoryPlayer items = l_Player.inventory;
        ItemStack inHand = l_Player.getHeldItemMainhand();
        ItemStack boots = items.armorItemInSlot(0);
        ItemStack leggings = items.armorItemInSlot(1);
        ItemStack body = items.armorItemInSlot(2);
        ItemStack helm = items.armorItemInSlot(3);
        ItemStack offHand = l_Player.getHeldItemOffhand();
        String Distance = "" + Distanc;
        String Ping = "" + p;
        String helm2 = helm.getItem().getItemStackDisplayName(helm);
        String body2 = body.getItem().getItemStackDisplayName(body);
        String leggings2 = leggings.getItem().getItemStackDisplayName(leggings);
        String boots2 = boots.getItem().getItemStackDisplayName(boots);
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 45), ((int)(float)this.y + 30), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        if (Friends.isFriend(Name)) {
          ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.AQUA + "Friend" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
        } else if (helm2.equals("Diamond Helmet") && body2.equals("Diamond Chestplate") && leggings2.equals("Diamond Leggings") && boots2.equals("Diamond Boots")) {
          ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.RED + "Threat" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
        } else if (helm2.equals("Diamond Helmet") && body2.equals("Elytra") && leggings2.equals("Diamond Leggings") && boots2.equals("Diamond Boots")) {
          ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.YELLOW + "Wasp" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
        } else if (helm2.equals("Air") && body2.equals("Air") && leggings2.equals("Air") && boots2.equals("Air")) {
          ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.GREEN + "Naked" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
        } else {
          ImpactPlus.CustomFont2.drawStringWithShadow(ChatFormatting.LIGHT_PURPLE + "Newfag" + ChatFormatting.RESET + " | " + numbercf + Ping + "ms" + ChatFormatting.RESET + " | " + numbercf + Distance, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
        } 
        GL11.glPopMatrix();
        ImpactPlus.CustomFont2.drawStringWithShadow(Name, ((int)(float)this.x + 45), ((int)(float)this.y + 12), this.text.getRGB());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GuiInventory.drawEntityOnScreen((int)this.x + 21, (int)this.y + 72, 30, (float)((int)(this.x + 51.0D) - this.x + 50.0D), (float)((int)(this.y + 75.0D - 50.0D) - this.y + 22.0D), (EntityLivingBase)l_Player);
        int i = 0;
        List<ItemStack> armor = new ArrayList<>();
        for (ItemStack is : l_Player.getArmorInventoryList())
          armor.add(is); 
        Collections.reverse(armor);
        for (ItemStack is : armor) {
          int j = (int)(this.y + 32.0D);
          int k = (int)(this.x + i + 42.0D);
          RenderHelper.enableGUIStandardItemLighting();
          mc.getRenderItem().renderItemAndEffectIntoGUI(is, k, j);
          mc.getRenderItem().renderItemOverlays(mc.fontRenderer, is, k, j);
          RenderHelper.disableStandardItemLighting();
          i += 18;
        } 
        int yy = (int)(this.y + 32.0D);
        int xx = (int)(this.x + 114.0D);
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(inHand, xx, yy);
        mc.getRenderItem().renderItemOverlays(mc.fontRenderer, inHand, xx, yy);
        RenderHelper.disableStandardItemLighting();
        ItemStack inOffHand = l_Player.getHeldItemOffhand();
        int yyy = (int)(this.y + 32.0D);
        int xxx = (int)(this.x + 132.0D);
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(inOffHand, xxx, yyy);
        mc.getRenderItem().renderItemOverlays(mc.fontRenderer, inOffHand, xxx, yyy);
        RenderHelper.disableStandardItemLighting();
        EnchantEntry[] enchants = { 
            new EnchantEntry(Enchantments.PROTECTION, "Pro"), new EnchantEntry(Enchantments.BLAST_PROTECTION, "Bla"), new EnchantEntry(Enchantments.FIRE_PROTECTION, "Fpr"), new EnchantEntry(Enchantments.PROJECTILE_PROTECTION, "Ppr"), new EnchantEntry(Enchantments.UNBREAKING, "Unb"), new EnchantEntry(Enchantments.MENDING, "Men"), new EnchantEntry(Enchantments.AQUA_AFFINITY, "Aqu"), new EnchantEntry(Enchantments.RESPIRATION, "Res"), new EnchantEntry(Enchantments.FEATHER_FALLING, "Fea"), new EnchantEntry(Enchantments.DEPTH_STRIDER, "Dep"), 
            new EnchantEntry(Enchantments.FROST_WALKER, "Fro"), new EnchantEntry(Enchantments.THORNS, "Thr"), new EnchantEntry(Enchantments.SHARPNESS, "Sha"), new EnchantEntry(Enchantments.FIRE_ASPECT, "Fia"), new EnchantEntry(Enchantments.KNOCKBACK, "Knb"), new EnchantEntry(Enchantments.POWER, "Pow"), new EnchantEntry(Enchantments.BINDING_CURSE, "Bin"), new EnchantEntry(Enchantments.SMITE, "Smi"), new EnchantEntry(Enchantments.BANE_OF_ARTHROPODS, "Ban"), new EnchantEntry(Enchantments.LOOTING, "Loo"), 
            new EnchantEntry(Enchantments.SWEEPING, "Swe"), new EnchantEntry(Enchantments.EFFICIENCY, "Eff"), new EnchantEntry(Enchantments.SILK_TOUCH, "Sil"), new EnchantEntry(Enchantments.FORTUNE, "For"), new EnchantEntry(Enchantments.FLAME, "Fla"), new EnchantEntry(Enchantments.LUCK_OF_THE_SEA, "Luc"), new EnchantEntry(Enchantments.LURE, "Lur"), new EnchantEntry(Enchantments.PUNCH, "Pun"), new EnchantEntry(Enchantments.VANISHING_CURSE, "Van") };
        int lolok = 0;
        int lolok2 = 0;
        int lolok3 = 0;
        int lolok4 = 0;
        int lolok5 = 0;
        int lolok6 = 0;
        EnchantEntry[] array;
        for (int length = (array = enchants).length, lolokq = 0; lolokq < length; lolokq++) {
          EnchantEntry enchant = array[lolokq];
          int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), helm);
          String levelDisplay = "" + level;
          if (level > 10)
            levelDisplay = "10+"; 
          if (level > 0) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 44), ((int)(float)this.y + 54 + lolok), 0.0D);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            if (enchant.getName().equals("Van")) {
              mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } 
            lolok += 5;
            GL11.glPopMatrix();
          } 
        } 
        for (int length2 = (array = enchants).length, lolok2q = 0; lolok2q < length2; lolok2q++) {
          EnchantEntry enchant = array[lolok2q];
          int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), body);
          String levelDisplay = "" + level;
          if (level > 10)
            levelDisplay = "10+"; 
          if (level > 0) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 62), ((int)(float)this.y + 54 + lolok2), 0.0D);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            if (enchant.getName().equals("Van")) {
              mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } 
            lolok2 += 5;
            GL11.glPopMatrix();
          } 
        } 
        for (int length3 = (array = enchants).length, lolok3q = 0; lolok3q < length3; lolok3q++) {
          EnchantEntry enchant = array[lolok3q];
          int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), leggings);
          String levelDisplay = "" + level;
          if (level > 10)
            levelDisplay = "10+"; 
          if (level > 0) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 80), ((int)(float)this.y + 54 + lolok3), 0.0D);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            if (enchant.getName().equals("Van")) {
              mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } 
            lolok3 += 5;
            GL11.glPopMatrix();
          } 
        } 
        for (int length4 = (array = enchants).length, lolok4q = 0; lolok4q < length4; lolok4q++) {
          EnchantEntry enchant = array[lolok4q];
          int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), boots);
          String levelDisplay = "" + level;
          if (level > 10)
            levelDisplay = "10+"; 
          if (level > 0) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 98), ((int)(float)this.y + 54 + lolok4), 0.0D);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            if (enchant.getName().equals("Van")) {
              mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } 
            lolok4 += 5;
            GL11.glPopMatrix();
          } 
        } 
        for (int length5 = (array = enchants).length, lolok5q = 0; lolok5q < length5; lolok5q++) {
          EnchantEntry enchant = array[lolok5q];
          int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), inHand);
          String levelDisplay = "" + level;
          if (level > 10)
            levelDisplay = "10+"; 
          if (level > 0) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 116), ((int)(float)this.y + 54 + lolok5), 0.0D);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            if (enchant.getName().equals("Van")) {
              mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } 
            lolok5 += 5;
            GL11.glPopMatrix();
          } 
        } 
        for (int length6 = (array = enchants).length, lolok6q = 0; lolok6q < length6; lolok6q++) {
          EnchantEntry enchant = array[lolok6q];
          int level = EnchantmentHelper.getEnchantmentLevel(enchant.getEnchant(), offHand);
          String levelDisplay = "" + level;
          if (level > 10)
            levelDisplay = "10+"; 
          if (level > 0) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 134), ((int)(float)this.y + 54 + lolok6), 0.0D);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            if (enchant.getName().equals("Van")) {
              mc.fontRenderer.drawStringWithShadow(ChatFormatting.RED + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } else {
              mc.fontRenderer.drawStringWithShadow(numbercf + enchant.getName() + " " + levelDisplay, 0.0F, -(Minecraft.getMinecraft()).fontRenderer.FONT_HEIGHT, this.text.getRGB());
            } 
            lolok6 += 5;
            GL11.glPopMatrix();
          } 
        } 
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 2), ((int)(float)this.y + 91), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        if (l_Player.isPotionActive(MobEffects.STRENGTH)) {
          DecimalFormat format1 = new DecimalFormat("0");
          DecimalFormat format2 = new DecimalFormat("00");
          int duration = l_Player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() / 20;
          int amplifier = l_Player.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier() + 1;
          double p1 = (duration % 60);
          double p2 = (duration / 60);
          double p3 = p2 % 60.0D;
          String minutes = format1.format(p3);
          String seconds = format2.format(p1);
          ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.RED + "Strength " + amplifier + TextFormatting.RESET + numbercf + " " + minutes + ":" + seconds, 0.0F, 0.0F, this.text.getRGB());
        } else {
          ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.RED + "Strength" + TextFormatting.RESET + numbercf + " None", 0.0F, 0.0F, this.text.getRGB());
        } 
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(((int)(float)this.x + 2), ((int)(float)this.y + 99), 0.0D);
        GL11.glScaled(0.9D, 0.9D, 0.9D);
        if (l_Player.isPotionActive(MobEffects.WEAKNESS)) {
          DecimalFormat format1 = new DecimalFormat("0");
          DecimalFormat format2 = new DecimalFormat("00");
          int duration = l_Player.getActivePotionEffect(MobEffects.WEAKNESS).getDuration() / 20;
          int amplifier = l_Player.getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier() + 1;
          double p1 = (duration % 60);
          double p2 = (duration / 60);
          double p3 = p2 % 60.0D;
          String minutes = format1.format(p3);
          String seconds = format2.format(p1);
          ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.GRAY + "Weakness " + amplifier + TextFormatting.RESET + numbercf + " " + minutes + ":" + seconds, 0.0F, 0.0F, this.text.getRGB());
        } else {
          ImpactPlus.CustomFont2.drawStringWithShadow(TextFormatting.GRAY + "Weakness" + TextFormatting.RESET + numbercf + " None", 0.0F, 0.0F, this.text.getRGB());
        } 
        GL11.glPopMatrix();
        if (Healt == 20) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141291);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 12, (int)this.x + 158, (int)this.y + 90, -1336541355);
        } 
        if (Healt == 19) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141291);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 16, (int)this.x + 158, (int)this.y + 90, -1336541355);
        } 
        if (Healt == 18) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -16733696);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 20, (int)this.x + 158, (int)this.y + 90, -1342133760);
        } 
        if (Healt == 17) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -16733696);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 24, (int)this.x + 158, (int)this.y + 90, -1342133760);
        } 
        if (Healt == 16) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 28, (int)this.x + 158, (int)this.y + 90, -1325400235);
        } 
        if (Healt == 15) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 32, (int)this.x + 158, (int)this.y + 90, -1325400235);
        } 
        if (Healt == 14) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 36, (int)this.x + 158, (int)this.y + 90, -1325400235);
        } 
        if (Healt == 13) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -171);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 40, (int)this.x + 158, (int)this.y + 90, -1325400235);
        } 
        if (Healt == 12) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 44, (int)this.x + 158, (int)this.y + 90, -1325422080);
        } 
        if (Healt == 11) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 48, (int)this.x + 158, (int)this.y + 90, -1325422080);
        } 
        if (Healt == 10) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 52, (int)this.x + 158, (int)this.y + 90, -1325422080);
        } 
        if (Healt == 9) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -22016);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 56, (int)this.x + 158, (int)this.y + 90, -1325422080);
        } 
        if (Healt == 8) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -43691);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 60, (int)this.x + 158, (int)this.y + 90, -1325443755);
        } 
        if (Healt == 7) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -43691);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 64, (int)this.x + 158, (int)this.y + 90, -1325443755);
        } 
        if (Healt == 6) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -43691);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 68, (int)this.x + 158, (int)this.y + 90, -1325443755);
        } 
        if (Healt == 5) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 72, (int)this.x + 158, (int)this.y + 90, -1331036160);
        } 
        if (Healt == 4) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 76, (int)this.x + 158, (int)this.y + 90, -1331036160);
        } 
        if (Healt == 3) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 80, (int)this.x + 158, (int)this.y + 90, -1331036160);
        } 
        if (Healt == 2) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 84, (int)this.x + 158, (int)this.y + 90, -1331036160);
        } 
        if (Healt == 1) {
          GL11.glPushMatrix();
          GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
          GL11.glScaled(0.9D, 0.9D, 0.9D);
          ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -5636096);
          GL11.glPopMatrix();
          Gui.drawRect((int)this.x + 150, (int)this.y + 88, (int)this.x + 158, (int)this.y + 90, -1331036160);
        } 
        if (Healt > 20) {
          Gui.drawRect((int)this.x + 150, (int)this.y + 12, (int)this.x + 158, (int)this.y + 90, -1336541355);
          if (Healt == 21) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 88, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 22) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 84, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 23) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 80, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 24) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 76, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 25) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 72, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 26) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 68, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 27) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 64, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 28) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 60, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 29) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 56, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 30) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 52, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 31) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 48, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 32) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 44, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 33) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 40, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 34) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 36, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 35) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 32, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
          if (Healt == 36) {
            GL11.glPushMatrix();
            GL11.glTranslated(((int)(float)this.x + 158), ((int)(float)this.y + 91), 0.0D);
            GL11.glScaled(0.9D, 0.9D, 0.9D);
            ImpactPlus.CustomFont2.drawStringWithShadow(Health, -ImpactPlus.CustomFont2.getStringWidth(Health), 0.0F, -11141121);
            GL11.glPopMatrix();
            Gui.drawRect((int)this.x + 150, (int)this.y + 28, (int)this.x + 158, (int)this.y + 90, -1336541185);
          } 
        } 
      } 
    } 
  }
  
  private void doStuff() {
    this.color = new Color(this.mod.red.getValInt(), this.mod.green.getValInt(), this.mod.blue.getValInt());
    this.text = this.mod.rainbow.getValBoolean() ? Rainbow.getColor() : this.color;
    this.bgcolor = new Color(this.mod.bgred.getValInt(), this.mod.bggreen.getValInt(), this.mod.bgblue.getValInt());
    this.bgtext = this.mod.bgrainbow.getValBoolean() ? Rainbow.getColorWithOpacity(this.mod.bgalpha.getValInt()) : this.bgcolor;
  }
  
  public static class ColourConverter {
    static int rgbToInt(int r, int g, int b, int a) {
      return r << 16 | g << 8 | b | a << 24;
    }
  }
  
  private void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
    GlStateManager.enableColorMaterial();
    GlStateManager.pushMatrix();
    GlStateManager.translate(posX, posY, 50.0F);
    GlStateManager.scale(-scale, scale, scale);
    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
    float f = ent.renderYawOffset;
    float f1 = ent.rotationYaw;
    float f2 = ent.rotationPitch;
    float f3 = ent.prevRotationYawHead;
    float f4 = ent.rotationYawHead;
    RenderHelper.enableStandardItemLighting();
    GlStateManager.translate(0.0F, 0.0F, 0.0F);
    RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
    rendermanager.setPlayerViewY(180.0F);
    rendermanager.setRenderShadow(false);
    rendermanager.renderEntity((Entity)ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
    rendermanager.setRenderShadow(true);
    GlStateManager.popMatrix();
    RenderHelper.disableStandardItemLighting();
    GlStateManager.disableRescaleNormal();
    GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
    GlStateManager.disableTexture2D();
    GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
  }
  
  public static class EnchantEntry {
    private Enchantment enchant;
    
    private String name;
    
    public EnchantEntry(Enchantment enchant, String name) {
      this.enchant = enchant;
      this.name = name;
    }
    
    public Enchantment getEnchant() {
      return this.enchant;
    }
    
    public String getName() {
      return this.name;
    }
  }
}
