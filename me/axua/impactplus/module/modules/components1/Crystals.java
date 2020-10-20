package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Crystals extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting mode;
  
  public Setting right;
  
  public Setting numbercolour;
  
  public Crystals() {
    super("Crystals", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    ArrayList<String> modes = new ArrayList<>();
    modes.add("Short");
    modes.add("Full");
    modes.add("Item");
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
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "CrystalsRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "CrystalsGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "CrystalsBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "CrystalsRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "CrystalsCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "CrystalsRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "CrystalsNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.mode = new Setting("Mode", this, "Full", modes, "CrystalsMode"));
  }
  
  public void onEnable() {
    disable();
  }
}
