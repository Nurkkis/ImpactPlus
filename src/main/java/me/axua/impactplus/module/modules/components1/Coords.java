package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Coords extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting decimal;
  
  public Setting right;
  
  public Setting style;
  
  public Setting numbercolour;
  
  public Setting bracketcolour;
  
  public Coords() {
    super("Coords", Module.Category.COMPONENTS1);
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
    ArrayList<String> modes = new ArrayList<>();
    modes.add("0");
    modes.add("0.0");
    modes.add("0.00");
    modes.add("0.0#");
    ArrayList<String> styles = new ArrayList<>();
    styles.add("Line");
    styles.add("Future");
    styles.add("WeepCraft");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "CoordinatesidkRed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "CoordinatesidkGreen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "CoordinatesidkBlue"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "CoordinatesidkRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "CoordinatesidkCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, true, "CoordinatesidkRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.decimal = new Setting("DecimalFormat", this, "0", modes, "CoordinatesidkDecimalFormat"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "CoordinatesidkNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bracketcolour = new Setting("Bracket Colour", this, "None", bracketcolours, "CoordinatesidkBracketColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.style = new Setting("Style", this, "Future", styles, "CoordinatesidkStyle"));
  }
  
  public void onEnable() {
    disable();
  }
}
