//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class SmartOffhand extends Module {
  public int totems;
  
  int crystals;
  
  boolean moving;
  
  boolean returnI;
  
  Item item;
  
  Setting health;
  
  Setting crystalCheck;
  
  Setting itemSetting;
  
  public SmartOffhand() {
    super("SmartOffhand", Module.Category.COMBAT);
    this.moving = false;
    this.returnI = false;
  }
  
  public void setup() {
    ArrayList<String> items = new ArrayList<>();
    items.add("Crystal");
    items.add("Gapple");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.health = new Setting("Health", this, 8.0D, 1.0D, 40.0D, true, "SmartOffhandHealth"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.crystalCheck = new Setting("CrystalCheck", this, false, "SmartOffhandCrystalCheck"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.itemSetting = new Setting("Item", this, "Gapple", items, "SmartOffhandItem"));
  }
  
  public void onEnable() {
    if (mc.player != null)
      Command.sendClientMessage("[SmartOffhand] Use at your own risk, this might fail sometimes. Pro tip: always have an empty inventory slot."); 
  }
  
  public void onUpdate() {
    if (this.itemSetting.getValString().equalsIgnoreCase("Gapple")) {
      this.item = Items.GOLDEN_APPLE;
    } else {
      this.item = Items.END_CRYSTAL;
    } 
    if (mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiContainer)
      return; 
    if (this.returnI) {
      int t = -1;
      for (int i = 0; i < 45; i++) {
        if (mc.player.inventory.getStackInSlot(i).isEmpty()) {
          t = i;
          break;
        } 
      } 
      if (t == -1)
        return; 
      mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
      this.returnI = false;
    } 
    this.totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.TOTEM_OF_UNDYING)).mapToInt(ItemStack::getCount).sum();
    this.crystals = mc.player.inventory.mainInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.END_CRYSTAL)).mapToInt(ItemStack::getCount).sum();
    if (shouldTotem() && mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
      this.totems++;
    } else if (!shouldTotem() && mc.player.getHeldItemOffhand().getItem() == this.item) {
      this.crystals += mc.player.getHeldItemOffhand().getCount();
    } else {
      if (this.moving) {
        mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        this.moving = false;
        this.returnI = true;
        return;
      } 
      if (mc.player.inventory.getItemStack().isEmpty()) {
        if (!shouldTotem() && mc.player.getHeldItemOffhand().getItem() == this.item)
          return; 
        if (shouldTotem() && mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)
          return; 
        if (!shouldTotem()) {
          if (this.crystals == 0)
            return; 
          int t = -1;
          for (int i = 0; i < 45; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == this.item) {
              t = i;
              break;
            } 
          } 
          if (t == -1)
            return; 
          mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          this.moving = true;
        } else {
          if (this.totems == 0)
            return; 
          int t = -1;
          for (int i = 0; i < 45; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == Items.TOTEM_OF_UNDYING) {
              t = i;
              break;
            } 
          } 
          if (t == -1)
            return; 
          mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          this.moving = true;
        } 
      } else {
        int t = -1;
        for (int i = 0; i < 45; i++) {
          if (mc.player.inventory.getStackInSlot(i).isEmpty()) {
            t = i;
            break;
          } 
        } 
        if (t == -1)
          return; 
        mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
      } 
    } 
  }
  
  private boolean shouldTotem() {
    boolean hp = (mc.player.getHealth() + mc.player.getAbsorptionAmount() <= this.health.getValInt());
    boolean endcrystal = !isCrystalsAABBEmpty();
    if (this.crystalCheck.getValBoolean())
      return (hp || endcrystal); 
    return hp;
  }
  
  private boolean isEmpty(BlockPos pos) {
    List<Entity> crystalsInAABB = (List<Entity>)mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos)).stream().filter(e -> e instanceof net.minecraft.entity.item.EntityEnderCrystal).collect(Collectors.toList());
    return crystalsInAABB.isEmpty();
  }
  
  private boolean isCrystalsAABBEmpty() {
    return (isEmpty(mc.player.getPosition().add(1, 0, 0)) && 
      isEmpty(mc.player.getPosition().add(-1, 0, 0)) && 
      isEmpty(mc.player.getPosition().add(0, 0, 1)) && 
      isEmpty(mc.player.getPosition().add(0, 0, -1)) && 
      isEmpty(mc.player.getPosition()));
  }
}
