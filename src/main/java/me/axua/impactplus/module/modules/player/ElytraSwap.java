//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.player;

import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;

public class ElytraSwap extends Module {
  public ElytraSwap() {
    super("ElytraSwap", Module.Category.PLAYER);
  }
  
  public void onEnable() {
    if (mc.player != null) {
      InventoryPlayer items = mc.player.inventory;
      ItemStack body = items.armorItemInSlot(2);
      String body2 = body.getItem().getItemStackDisplayName(body);
      if (body2.equals("Air")) {
        int t = 0;
        int c = 0;
        int i;
        for (i = 9; i < 45; i++) {
          if (mc.player.inventory.getStackInSlot(i).getItem() == Items.ELYTRA) {
            t = i;
            break;
          } 
        } 
        if (t != 0) {
          Command.sendClientMessage("Equipping Elytra");
          mc.playerController.windowClick(0, t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          mc.playerController.windowClick(0, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        } 
        if (t == 0) {
          for (i = 9; i < 45; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == Items.DIAMOND_CHESTPLATE) {
              c = i;
              break;
            } 
          } 
          if (c != 0) {
            Command.sendClientMessage("Equipping Chestplate");
            mc.playerController.windowClick(0, c, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
            mc.playerController.windowClick(0, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          } 
        } 
        if (c == 0 && t == 0)
          Command.sendClientMessage("You do not have an Elytra or a Chestplate in your inventory. Doing nothing"); 
        disable();
      } 
      if (body2.equals("Elytra")) {
        int t = 0;
        for (int i = 9; i < 45; i++) {
          if (mc.player.inventory.getStackInSlot(i).getItem() == Items.DIAMOND_CHESTPLATE) {
            t = i;
            break;
          } 
        } 
        if (t != 0) {
          int l = 0;
          Command.sendClientMessage("Equipping Chestplate");
          mc.playerController.windowClick(0, t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          mc.playerController.windowClick(0, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          for (int j = 9; j < 45; j++) {
            if (mc.player.inventory.getStackInSlot(j).getItem() == Items.AIR) {
              l = j;
              break;
            } 
          } 
          mc.playerController.windowClick(0, l, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        } 
        if (t == 0)
          Command.sendClientMessage("You do not have a Chestplate in your inventory. Keeping Elytra equipped"); 
        disable();
      } 
      if (body2.equals("Diamond Chestplate")) {
        int t = 0;
        for (int i = 9; i < 45; i++) {
          if (mc.player.inventory.getStackInSlot(i).getItem() == Items.ELYTRA) {
            t = i;
            break;
          } 
        } 
        if (t != 0) {
          int u = 0;
          Command.sendClientMessage("Equipping Elytra");
          mc.playerController.windowClick(0, t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          mc.playerController.windowClick(0, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
          for (int j = 9; j < 45; j++) {
            if (mc.player.inventory.getStackInSlot(j).getItem() == Items.AIR) {
              u = j;
              break;
            } 
          } 
          mc.playerController.windowClick(0, u, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
        } 
        if (t == 0)
          Command.sendClientMessage("You do not have a Elytra in your inventory. Keeping Chestplate equipped"); 
        disable();
      } 
    } 
  }
}
