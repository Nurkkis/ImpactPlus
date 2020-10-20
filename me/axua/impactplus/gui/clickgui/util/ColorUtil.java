package me.axua.impactplus.gui.clickgui.util;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.util.Rainbow;

public class ColorUtil {
  public static Color getClickGUIColor() {
    if ((ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiRainbow").getValBoolean())
      return Rainbow.getColor(); 
    return new Color((int)(ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiRed").getValDouble(), (int)(ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiGreen").getValDouble(), (int)(ImpactPlus.getInstance()).settingsManager.getSettingByID("ClickGuiBlue").getValDouble());
  }
}
