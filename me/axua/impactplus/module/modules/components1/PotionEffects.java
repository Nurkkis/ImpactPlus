package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class PotionEffects extends Module {
  public static PotionEffects INSTANCE;
  
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting normalcolour;
  
  public Setting right;
  
  public Setting sortUp;
  
  public Setting numbercolour;
  
  public Setting customFont;
  
  public PotionEffects() {
    super("PotionEffects", Module.Category.COMPONENTS1);
    setDrawn(true);
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
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "PotionEffectsRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "PotionEffectsGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "PotionEffectsBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    this.rainbow = new Setting("Rainbow", this, true, "PotionEffectsRainbow");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "PotionEffectsCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, true, "PotionEffectsRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.sortUp = new Setting("SortDown", this, false, "PotionEffectsSortUp"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.normalcolour = new Setting("Normal Colour", this, false, "PotionEffectsNormalColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "PotionEffectsNumberColour"));
  }
  
  public void onEnable() {
    disable();
  }
}
