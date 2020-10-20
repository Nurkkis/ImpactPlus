package me.axua.impactplus.module.modules.components1;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class TheDate extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting right;
  
  public TheDate() {
    super("TheDate", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "TheDateRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "TheDateGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "TheDateBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "TheDateRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "TheDateCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "TheDateRight"));
  }
  
  public void onEnable() {
    disable();
  }
}
