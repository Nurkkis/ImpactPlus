package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class TextRadar extends Module {
  public static TextRadar INSTANCE;
  
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting distance;
  
  public Setting healthcolour;
  
  public Setting showfands;
  
  public Setting right;
  
  public Setting distancecolour;
  
  public Setting numbercolour;
  
  public TextRadar() {
    super("TextRadar", Module.Category.COMPONENTS1);
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
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "TextRadarRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "TextRadarGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "TextRadarBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, true, "TextRadarRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "TextRadarCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, true, "TextRadarRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "Dark Gray", numbercolours, "TextRadarNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.healthcolour = new Setting("Health Colour", this, true, "TextRadarHealthColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.distance = new Setting("Distance", this, true, "TextRadarDistance"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.distancecolour = new Setting("Distance Colour", this, false, "TextRadarDistanceColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.showfands = new Setting("Show [F] [S]", this, true, "TextRadarShowStrengthAndFriends"));
  }
  
  public void onEnable() {
    disable();
  }
}
