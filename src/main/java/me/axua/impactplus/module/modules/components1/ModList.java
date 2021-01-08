package me.axua.impactplus.module.modules.components1;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class ModList extends Module {
  public static ModList INSTANCE;
  
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting right;
  
  public Setting sortUp;
  
  public Setting rects;
  
  public ModList() {
    super("ModList", Module.Category.COMPONENTS1);
    setDrawn(true);
  }
  
  public void setup() {
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "ModListRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "ModListGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "ModListBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    this.rainbow = new Setting("Rainbow", this, true, "ModListRainbow");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "ModListCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "ModListRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.sortUp = new Setting("SortDown", this, true, "ModListSortUp"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rects = new Setting("Rects", this, false, "ModListRects"));
  }
  
  public void onEnable() {
    disable();
  }
}
