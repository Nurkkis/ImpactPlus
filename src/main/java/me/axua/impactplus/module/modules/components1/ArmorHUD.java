package me.axua.impactplus.module.modules.components1;

import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class ArmorHUD extends Module {
  public Setting vertical;
  
  public Setting reverse;
  
  public ArmorHUD() {
    super("ArmorHUD", Module.Category.COMPONENTS1);
    rSetting(this.vertical = new Setting("Vertical", this, false, "ArmorHudVertical"));
    rSetting(this.reverse = new Setting("Reverse", this, true, "ArmorHudReverse"));
  }
  
  public void onEnable() {
    disable();
  }
}
