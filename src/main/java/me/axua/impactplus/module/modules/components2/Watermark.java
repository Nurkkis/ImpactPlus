package me.axua.impactplus.module.modules.components2;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Watermark extends Module {
  public static Watermark INSTANCE;
  
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting customFont;
  
  public Setting version;
  
  public Setting watermarkmode;
  
  public Setting versionmode;
  
  public Setting right;
  
  public Setting numbercolour;
  
  public Setting size;
  
  public Watermark() {
    super("Watermark", Module.Category.COMPONENTS2);
    setDrawn(true);
    INSTANCE = this;
  }
  
  public void setup() {
    ArrayList<String> watermarkmodes = new ArrayList<>();
    watermarkmodes.add("Impact+");
    watermarkmodes.add("SalHack");
    watermarkmodes.add("WeepCraft");
    ArrayList<String> versionmodes = new ArrayList<>();
    versionmodes.add("v1.0");
    versionmodes.add("v2.0");
    versionmodes.add("v2.4");
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
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "GuiWatermarkRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "GuiWatermarkGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "GuiWatermarkBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    this.rainbow = new Setting("Rainbow", this, true, "GuiWatermarkRainbow");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "GuiWatermarkCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "WatermarkRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "WatermarkNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.watermarkmode = new Setting("Watermark", this, "Impact+", watermarkmodes, "WatermarkMode"));
    rSetting(this.version = new Setting("Version", this, true, "GuiWatermarkVersionBoolean"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.versionmode = new Setting("Version", this, "v2.4", versionmodes, "VersionMode"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.size = new Setting("Size", this, 1.0D, 0.0D, 2.0D, false, "VersionSize"));
  }
  
  public void onEnable() {
    disable();
  }
}
