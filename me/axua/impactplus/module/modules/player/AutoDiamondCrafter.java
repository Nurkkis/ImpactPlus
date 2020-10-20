//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.player;

import java.util.Timer;
import java.util.TimerTask;
import me.axua.impactplus.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class AutoDiamondCrafter extends Module {
  public AutoDiamondCrafter() {
    super("AutoDiamondCrafter", Module.Category.PLAYER, "Automatically craft diamonds from diamond blocks and throw them on the floor ");
  }
  
  public void onEnable() {
    if (mc.player != null) {
      Timer m_Timerrr = new Timer();
      m_Timerrr.cancel();
      m_Timerrr = new Timer();
      int t = 0;
      for (int i = 9; i < 45; i++) {
        if (mc.player.inventory.getStackInSlot(i).getItem() == Item.getByNameOrId(String.valueOf(57))) {
          t = i;
          break;
        } 
      } 
      mc.playerController.windowClick(0, t, 0, ClickType.PICKUP, (EntityPlayer)mc.player);
      m_Timerrr.schedule(new TimerTask() {
            public void run() {
              AutoDiamondCrafter.mc.playerController.windowClick(0, 1, 0, ClickType.PICKUP, (EntityPlayer)AutoDiamondCrafter.mc.player);
            }
          }100L);
      m_Timerrr.schedule(new TimerTask() {
            public void run() {
              AutoDiamondCrafter.mc.playerController.windowClick(0, 0, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoDiamondCrafter.mc.player);
            }
          }200L);
      m_Timerrr.schedule(new TimerTask() {
            public void run() {
              Timer mTimer = new Timer();
              mTimer.cancel();
              mTimer = new Timer();
              int delay2 = 400;
              for (int i = 9; i < 45; i++) {
                if (AutoDiamondCrafter.mc.player.inventory.getStackInSlot(i).getItem() == Item.getByNameOrId(String.valueOf(264)))
                  delay2 += 110; 
                final int finalI = i;
                mTimer.schedule(new TimerTask() {
                      public void run() {
                        if (AutoDiamondCrafter.mc.player.inventory.getStackInSlot(finalI).getItem() == Item.getByNameOrId(String.valueOf(264)))
                          for (int y = 1; y < 65; y++)
                            AutoDiamondCrafter.mc.playerController.windowClick(0, finalI, 0, ClickType.THROW, (EntityPlayer)AutoDiamondCrafter.mc.player);  
                      }
                    }delay2);
              } 
            }
          }300L);
    } 
    disable();
  }
}
