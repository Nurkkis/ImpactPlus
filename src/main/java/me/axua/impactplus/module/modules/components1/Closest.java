package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Closest extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting bgred;
  
  public Setting bggreen;
  
  public Setting bgblue;
  
  public Setting bgalpha;
  
  public Setting bgrainbow;
  
  public Setting numbercolour;
  
  public Closest() {
    super("Closest", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    ArrayList<String> numbercolours = new ArrayList<>();
    numbercolours.add("None");
    numbercolours.add("White");
    numbercolours.add("Black");
    numbercolours.add("Gray");
    numbercolours.add("Dark Gray");
    numbercolours.add("Aqua");
    numbercolours.add("Dark Aqua");
    numbercolours.add("Blue");
    numbercolours.add("Dark Blue");
    numbercolours.add("Green");
    numbercolours.add("Dark Green");
    numbercolours.add("Light Purple");
    numbercolours.add("Dark Purple");
    numbercolours.add("Red");
    numbercolours.add("Dark Red");
    numbercolours.add("Yellow");
    numbercolours.add("Gold");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "ClosestRed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "ClosestGreen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "ClosestBlue"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "ClosestRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "ClosestNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bgred = new Setting("BGRed", this, 9.0D, 0.0D, 255.0D, true, "ClosestBGRed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bggreen = new Setting("BGGreen", this, 9.0D, 0.0D, 255.0D, true, "ClosestBGGreen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bgblue = new Setting("BGBlue", this, 9.0D, 0.0D, 255.0D, true, "ClosestBGBlue"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bgalpha = new Setting("BGAlpha", this, 125.0D, 0.0D, 255.0D, true, "ClosestBGAlpha"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bgrainbow = new Setting("BGRainbow", this, false, "ClosestBGRainbow"));
  }
  
  public void onEnable() {
    disable();
  }
}
