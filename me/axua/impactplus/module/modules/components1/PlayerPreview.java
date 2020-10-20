package me.axua.impactplus.module.modules.components1;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class PlayerPreview extends Module {
  public Setting Size;
  
  public PlayerPreview() {
    super("PlayerPreview", Module.Category.COMPONENTS1);
    setDrawn(true);
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.Size = new Setting("Size", this, 25.0D, 1.0D, 500.0D, true, "PlayerPreviewSize"));
  }
  
  public void onEnable() {
    disable();
  }
}
