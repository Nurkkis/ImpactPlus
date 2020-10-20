package me.axua.impactplus.module.modules.components1;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class PvpInfo extends Module {
  public Setting offRainbow;
  
  public Setting offR;
  
  public Setting offG;
  
  public Setting offB;
  
  public Setting onRainbow;
  
  public Setting onR;
  
  public Setting onG;
  
  public Setting onB;
  
  public Setting customFont;
  
  public PvpInfo() {
    super("PvpInfo", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.offRainbow = new Setting("OffRainbow", this, false, "PvpInfoOffRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.offR = new Setting("OffR", this, 255.0D, 0.0D, 255.0D, true, "PvpInfoOffRed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.offG = new Setting("OffG", this, 0.0D, 0.0D, 255.0D, true, "PvpInfoOffGreen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.offB = new Setting("OffB", this, 0.0D, 0.0D, 255.0D, true, "PvpInfoOffBlue"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.onRainbow = new Setting("OnRainbow", this, false, "PvpInfoOnRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.onR = new Setting("OnR", this, 0.0D, 0.0D, 255.0D, true, "PvpInfoOnRed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.onG = new Setting("OnG", this, 255.0D, 0.0D, 255.0D, true, "PvpInfoOnGreen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.onB = new Setting("OnB", this, 0.0D, 0.0D, 255.0D, true, "PvpInfoOnBlue"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "PvpInfoCustomFont"));
  }
  
  public void onEnable() {
    disable();
  }
}
