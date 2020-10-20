package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class CurrentHole extends Module {
  public Setting mode;
  
  public Setting customFont;
  
  public CurrentHole() {
    super("Hole", Module.Category.COMPONENTS1);
    ArrayList<String> modes = new ArrayList<>();
    modes.add("Texture");
    modes.add("Block");
    modes.add("Safe/Unsafe");
    rSetting(this.mode = new Setting("Mode", this, "Block", modes, "CurrentHoleComponentMode"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "CurrentHoleCustomFont"));
  }
}
