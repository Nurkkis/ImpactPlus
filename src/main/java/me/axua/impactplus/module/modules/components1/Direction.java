package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Direction extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting directionmode;
  
  public Setting customFont;
  
  public Setting right;
  
  public Setting numbercolour;
  
  public Setting bracketcolour;
  
  public Direction() {
    super("Direction", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    ArrayList<String> directionmodes = new ArrayList<>();
    directionmodes.add("XZ");
    directionmodes.add("NSWE");
    directionmodes.add("Both");
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
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "DirectionRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "DirectionGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "DirectionBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "DirectionRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "DirectionCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, true, "DirectionRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "DirectionNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bracketcolour = new Setting("Bracket Colour", this, "None", bracketcolours, "DirectionBracketColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.directionmode = new Setting("Mode", this, "Both", directionmodes, "DirectionMode"));
  }
}
