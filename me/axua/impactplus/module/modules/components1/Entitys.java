package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Entitys extends Module {
  public static Entitys INSTANCE;
  
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting right;
  
  public Setting numbercolour;
  
  public Setting bracketcolour;
  
  public Entitys() {
    super("Entitys", Module.Category.COMPONENTS1);
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
    ArrayList<String> bracketcolours = new ArrayList<>();
    bracketcolours.add("None");
    bracketcolours.add("White");
    bracketcolours.add("Black");
    bracketcolours.add("Gray");
    bracketcolours.add("Dark Gray");
    bracketcolours.add("Aqua");
    bracketcolours.add("Dark Aqua");
    bracketcolours.add("Blue");
    bracketcolours.add("Dark Blue");
    bracketcolours.add("Green");
    bracketcolours.add("Dark Green");
    bracketcolours.add("Light Purple");
    bracketcolours.add("Dark Purple");
    bracketcolours.add("Red");
    bracketcolours.add("Dark Red");
    bracketcolours.add("Yellow");
    bracketcolours.add("Gold");
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "EntitysRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "EntitysGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "EntitysBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "EntitysRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "EntitysCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "EntitysRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "EntitysNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bracketcolour = new Setting("Bracket Colour", this, "None", bracketcolours, "EntitysBracketColour"));
  }
  
  public void onEnable() {
    disable();
  }
}
