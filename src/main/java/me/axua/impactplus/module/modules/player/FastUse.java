//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.player;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.init.Items;

public class FastUse extends Module {
  Setting xp;
  
  Setting crystals;
  
  Setting all;
  
  Setting breakS;
  
  public FastUse() {
    super("FastUse", Module.Category.PLAYER, "Sets right click / block break delay to 0");
  }
  
  public void setup() {
    this.xp = new Setting("EXP", this, true, "FastUseEXP");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.xp);
    this.crystals = new Setting("Crystals", this, true, "FastUseCrystals");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.crystals);
    this.all = new Setting("Everything", this, false, "FastUseEverything");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.all);
    this.breakS = new Setting("FastBreak", this, true, "FastUseFastBreak");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.breakS);
  }
  
  public void onUpdate() {
    if (this.xp.getValBoolean() && 
      mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE))
      mc.rightClickDelayTimer = 0; 
    if (this.crystals.getValBoolean() && 
      mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL))
      mc.rightClickDelayTimer = 0; 
    if (this.all.getValBoolean())
      mc.rightClickDelayTimer = 0; 
    if (this.breakS.getValBoolean())
      mc.playerController.blockHitDelay = 0; 
  }
}
