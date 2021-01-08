package me.axua.impactplus.module.modules.components2;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class WelcomerGui extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting rainbow;
  
  public Setting message;
  
  public Setting customFont;
  
  public Setting right;
  
  public Setting numbercolour;
  
  ArrayList<String> messages;
  
  public WelcomerGui() {
    super("Welcome", Module.Category.COMPONENTS2);
    setDrawn(true);
  }
  
  public void setup() {
    this.messages = new ArrayList<>();
    this.messages.add("Welcome1");
    this.messages.add("Welcome2");
    this.messages.add("Hello1");
    this.messages.add("Hello2");
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
    this.red = new Setting("Red", this, 80.0D, 0.0D, 255.0D, true, "GuiWelcomeRed");
    this.green = new Setting("Green", this, 80.0D, 0.0D, 255.0D, true, "GuiWelcomeGreen");
    this.blue = new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "GuiWelcomeBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    this.rainbow = new Setting("Rainbow", this, true, "GuiWelcomeRainbow");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.customFont = new Setting("Custom Font", this, true, "GuiWelcomeCustomFont"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.right = new Setting("AlignRight", this, false, "GuiWelcomeRight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.numbercolour = new Setting("Number Colour", this, "White", numbercolours, "GuiWelcomeNumberColour"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.message = new Setting("Message", this, "Welcome1", this.messages, "GuiWelcomeMessageMode"));
  }
  
  public void onEnable() {
    disable();
  }
}
