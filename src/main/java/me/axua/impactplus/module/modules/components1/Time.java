package me.axua.impactplus.module.modules.components1;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Time extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting right;
  
  public Time() {
    super("Time", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "TimeRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "TimeGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "TimeBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "TimeRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "TimeCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "TimeRight"));
  }
  
  public void onEnable() {
    disable();
  }
}
