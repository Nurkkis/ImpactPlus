//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class FovModule extends Module {
  Setting fov;
  
  public FovModule() {
    super("FOV", Module.Category.RENDER, "Changes your fov");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.fov = new Setting("Value", this, 90.0D, 0.0D, 180.0D, true, "FovModValue"));
    setDrawn(false);
  }
  
  public void onUpdate() {
    mc.gameSettings.fovSetting = this.fov.getValInt();
  }
}
